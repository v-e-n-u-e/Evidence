package evidence.clientserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import evidence.clientserver.infoholders.RenderPackage;
import evidence.gameworld.Wall;
import evidence.gui.ClientWindow;

/**
 * The Client is responsible for mediating the interaction between
 * the Server and the GUI (ClientWindow).  It serves as the 'back-end'
 * of the GUI.
 *
 * In a Model-View-Controller sense, ClientPipe serves as the controller.
 *
 * @author Tyler Jones
 */
public class ClientPipe{
	// The size of byte array's for receiving packets
	private final int BYTE_ARRAY_LENGTH = 2048 * 6;

	// Information for our connection
	private String name; // The name of the user connecting
	private String address; // The address of the server we are connecting to, as a String
	private int port; // The port for the server we are connecting to
	private int ID = -1; // A unique integer to identify this ClientPipe from other ClientPipes

	private DatagramSocket socket; // A socket to the server, for the UDP Connection protocol.
	private InetAddress ip; // The address of the server we are connecting to, as an InetAddress

	// Threads to handle different aspects of the program
	private Thread send, receive;

	// Boolean that contains the state of the program
	private boolean running;

	// The ClientWindow object to call to when we need to visually update something to the user
	private ClientWindow gui;

	/**
	 * A constructor for a ClientPipe
	 *
	 * @param name - The name of the user this ClientPipe is for
	 * @param address - The address of the server we are connecting to
	 * @param port - The port of the server we are connecting to
	 */
	public ClientPipe(String name, String address, int port, ClientWindow gui){
		this.name = name;
		this.address = address;
		this.port = port;
		this.gui = gui;
	}

	/**
	 * Getter for the name
	 * @return - the name of the user for this ClientPipe
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Getter for the address
	 * @return - the address of the server we are connecting to
	 */
	public String getAddress(){
		return this.address;
	}

	/**
	 * Getter for the port
	 * @return - the port of the server we are connecting to
	 */
	public int getPort(){
		return this.port;
	}

	/**
	 * Getter for the ID
	 * @return - the unique Integer which identifies this ClientPipe
	 */
	public int getId() {
		return this.ID;
	}

	/**
	 * Setter for the ID
	 */
	public void setId(int ID) {
		this.ID = ID;
	}

	/**
	 * Attempt a connection to the given address and report.
	 * Returns true if the connection succeeds, false otherwise.
	 *
	 * @param address - the address we are connecting to
	 * @return - True if successful, false otherwise
	 */
	public boolean openConnection(String address){
		try {
			// Create a new DatagramSocket and attempt to get the InetAdress for our user specified address
			socket = new DatagramSocket();
			ip = InetAddress.getByName(address);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		running = true;
		return true;
	}

	/**
	 * This method will receive a DatagramPacket that has been sent
	 * to the ClientPipe from the server.  The program will sit at
	 * the socket.receive(packet) line until the server send us something.
	 * Because we have a separate Thread running this method however,
	 * it does not hang up our entire program.
	 *
	 * @return - A string representation of a packet that has been received
	 */
	public void receive(){
		// Start a new Thread for receiving
		receive = new Thread("Receiver") {
			public void run(){
				while(running){
					// Construct a byte array, and give it to a DatagramPacket object.
					// At this stage it is currently empty
					byte[] data = new byte[BYTE_ARRAY_LENGTH];
					DatagramPacket packet = new DatagramPacket(data, data.length);
					//
					try {
						socket.receive(packet);
					} catch (IOException e) {
						e.printStackTrace();
					}
					process(packet);
				}
			}
		};
		receive.start();
	}

	/**
	 * When a packet is received, the packet is extracted
	 * and sent to this method. An object is created from the
	 * array of bytes contained within the packet and work 
	 * is delegated to the appropriate method depending on
	 * what the object was.
	 *
	 * @param packet - The packet to process
	 */
	public void process(DatagramPacket packet){
		// Extract the serialized data into an Object
		Object o = null;
		try {
			o = getObject(packet);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// Was the serialized object a String?
		if(o instanceof String){processString((String) o);}
		
		// Was the serialized object a wall?
		else if(o instanceof RenderPackage){processRenderPackage((RenderPackage) o);}
	}
	
	/**
	 * Processes a String and performs the appropriate action, 
	 * based on the header at the start of the String.
	 * 
	 * HEADERS:
	 *  /c/ - A connection response
	 *  /m/ - A chat room message
	 *  /ping/ - The server pinging the client to make sure they are still connected
	 *  /timer/ - An update for our timer
	 *  /refusal/ - A connection refusal 
	 * 
	 * @param message - The String to process
	 */
	private void processString(String message){
		// Did the server confirm our connection?
		if(message.startsWith("/c/") ){
			setId(Integer.parseInt(message.split("/c/|/e/")[1]) );
			gui.writeToChatLog("Successful connection! ID: " + ID);
		}

		// Is the message from another Client?
		else if(message.startsWith("/m/") ){
			message = message.split("/m/|/e/")[1];
			gui.writeToChatLog(message);
		}

		// Is the server pinging us to make sure we are connected?
		else if(message.startsWith("/ping/")){
			String reply = "/ping/" + ID + "/e/";
			send(reply);
		}
		
		// Is the server sending us an update to the timer?
		else if(message.startsWith("/timer/")){
			String timeUpdate = message.split("/timer/|/e/")[1];
			gui.updateTime(timeUpdate);
		}
		
		// Is the server refusing our connection?
		else if(message.startsWith("/refusal/") ){
			String refusal = message.split("/refusal/|/e/")[1];
			gui.writeToChatLog(refusal);
			gui.doNotAllowMessaging();
		}
	}
	
	/**
	 * Calls the appropriate methods for processing a wall
	 * upon receiving the wall from the server
	 * 
	 * @param rPackage - The RednerPackage we need to render
	 */
	private void processRenderPackage(RenderPackage rPackage){
		gui.rPackage = rPackage;
		gui.reRenderWall();
	}
	
	/**
	 * Given an object, will serialize the object
	 * using ByteArrayOutputStream and ObjectOutputStream
	 * 
	 * @param o - The object to serialize
	 * @return - The Array of bytes representing our serialized object
	 * @throws IOException
	 */
	public byte[] getBytes(Object o) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.flush();
		oos.writeObject(o);
		oos.flush();
		return bos.toByteArray();
	}
	
	/**
	 * Given a packet, will return the Object that the packet.getData()
	 * represents.  deserializes using ByteArrayInputStream and 
	 * ObjectInputStream.
	 * 
	 * @param packet - The packet containing the data
	 * @return - The object the data represented
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Object getObject(DatagramPacket packet) throws IOException, ClassNotFoundException{
		byte[] data = packet.getData();
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object o = ois.readObject();
		return o;
	}

	/**
	 * This method creates a packet given an object, and sends
	 * it through the socket, to the server.  A separate thread
	 * is created to do this.
	 *
	 * @param toSend - The object to serialize and put into the packet
	 */
	public void send(Object toSend){
		// Create a new thread to send the data on and then start the thread
		send = new Thread("Sender"){
			public void run(){
				// Create a packet from data, that is the length of data
				// to the IP Address established by our connection, through the specified port
				byte[] data = null;
				
				// Construct the byte array using our output streams in getBytes();
				try {
					data = getBytes(toSend);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		send.start();
	}

	/**
	 * Closes the socket's connection, called when disconnecting
	 * intentionally.
	 */
	public void close(){
		Thread close = new Thread() {
			public void run(){
				synchronized(socket){
					socket.close();
				}
			}
		};
		close.start();
	}
}
