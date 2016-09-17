package evidence.clientserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * The server is responsible for keeping track of any users connected to the server
 * and sending / receiving packets to and from them.  For example, if a player moves 
 * in their client, we must send a packet to the server containing the player's new 
 * location, and propagate that packet to the other user's so they can update that
 * player's location on their own client.
 * 
 * The server uses 4 different threads to handle different functionality that
 * the server may provide.  This is mainly because receive() will sit and wait
 * until it receives something through the socket.  If we did not have a separate 
 * thread for this, the entire server would sit and wait and this is not desirable.
 * 
 * * In a Model-View-Controller sense, Server acts as the controller.
 * 
 * @author Tyler Jones
 */
public class Server implements Runnable{
	
	// Port number this server is running on
	private int port;
	
	// A socket for the server to send and receive packets through
	private DatagramSocket socket;
	
	// A boolean to keep track of whether the server is running
	private boolean running;
	
	// Threads to handle different aspects of the server
	private Thread run, send, receive, manage;
	
	// An ArrayList containing all the clients connected to the server
	private ArrayList<ServerClient> clients = new ArrayList<ServerClient>();
	
	/**
	 * Constructor for a server instance
	 * 
	 * @param port - The port the server will be running on
	 */
	public Server(int port){
		this.port = port;
		
		// Try to create a socket for the port given in the command line arguments
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		// If successful, create a new Thread for the server and start the thread
		run = new Thread(this, "Server");
		run.start();
	}

	/**
	 * Called when the run thread is started
	 * Puts the program in the running state and starts
	 * the manage / receive threads.
	 */
	@Override
	public void run() {
		running = true;
		//System.out.println("Server started on port: " + port);
		manageClients();
		receive();
	}

	/**
	 * Starts the manage thread.  Will loop repeatedly while the program
	 * is running and manage the client list.  Managing clients is basically
	 * handling disconnections, whether expected or not, from the server.
	 */
	private void manageClients() {
		manage = new Thread("Manager") {
			public void run(){
				while(running){
					// Managing...
				}
			}
		};
		manage.start();
	}

	/**
	 * Starts the receive thread.  Will loop repeatedly while the program is running
	 * and wait to receive messages from the socket.
	 * 
	 * When it receives a packet, it will call the process method to handle
	 * the packet
	 */
	private void receive() {
		receive = new Thread("Receiver") {
			public void run(){
				while(running){
					byte[] data = new byte[1024];
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
	 * Upon receiving a packet, this method is called.
	 * Responsible for reading the header of the packet and
	 * performing the appropriate action based on it.
	 * 
	 * HEADERS:
	 * "/c/" - A connection packet, used upon initial connection
	 * "/m/" - A message packet, used when users send messages to the server
	 * 
	 * @param packet - The packet to process
	 */
	private void process(DatagramPacket packet) {
		String string = new String(packet.getData() );
		
		if(string.startsWith("/c/") ){
			//Assign a unique identifier for this client
			int id = UniqueIdentifier.getIdentifier();
			clients.add(new ServerClient(string.split("/c/|/e/")[1], packet.getAddress(),
					packet.getPort(), id) );
			System.out.println("Added : " + string.split("/c/|/e/")[1] + " with ID: " + id);
			
			// Form a connection confirmation packet, and send it,
			// to the client to confirm a connection was successful
			String confirm = "/c/" + id;
			send(confirm, packet.getAddress(), packet.getPort() );
		}
		
		else if(string.startsWith("/m/")){
			sendToAll(string);
		}
		else{
			
		}
	}
	
	/**
	 * A method to send an array of bytes to a specific address and port,
	 * through our socket.
	 * 
	 * @param data - the bytes to send
	 * @param address - the address to send to
	 * @param port - the port to send to
	 */
	private void send(final byte[] data, InetAddress address, int port){
		// Create a new thread to send the data on and then start the thread
		send = new Thread("ServerSender"){
			public void run(){
				// Create a packet from data, that is the length of data
				// to the IP Address established by our connection, through the specified port
				DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
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
	 * Appends an end character to our message
	 * 
	 * @param message
	 * @param address
	 * @param port
	 */
	private void send(String message, InetAddress address, int port){
		message += "/e/";
		send(message.getBytes(), address, port);
	}
	
	/**
	 * A simple method that iterates over all the connected clients
	 * in our list, and sends the message to them.
	 * 
	 * @param message - the message to send
	 */
	private void sendToAll(String message){
		for(int i = 0; i < clients.size(); i++){
			ServerClient client = clients.get(i);
			send(message.getBytes(), client.address, client.port);
		}
	}
}
