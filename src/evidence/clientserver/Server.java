package evidence.clientserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import evidence.gameworld.Game;
import evidence.gameworld.Player;
import evidence.gameworld.Timer;
import evidence.gameworld.Wall.Direction;
import evidence.gui.ServerGUI;
import evidence.testobjects.TestRoom;
import evidence.testobjects.TestWall;

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
	
	// An ArrayList containing the ID's of all clients who have responded to our ping packets
	private ArrayList<Integer> clientResponse = new ArrayList<Integer>();
	
	// Variables to help with managing the clients
	private final int MAX_ATTEMPTS = 5; // How many times we will ping a client unsuccessfully before we disconnect them
	private final long MANAGE_DURATION = 2000; // How long the Manage thread will sleep after every iteration, to save resources.
	
	// A simple Window that servers as a formal log for the server
	private ServerGUI gui;
	
	// A Timer for the Server
	private Timer timer;
	
	// Number of players to start the game at
	private int numPlayers;
	
	// Boolean that keeps track of when our specified number of players have connected
	private boolean allPlayersConnected;
	
	private Game game; // The game instance
	
	/**
	 * Constructor for a server instance
	 * 
	 * @param port - The port the server will be running on
	 * @param gui - The GUI object for the server
	 */
	public Server(int port, ServerGUI gui, int numPlayers){
		this.port = port;
		this.gui = gui;
		this.numPlayers = numPlayers;
		
		// Try to create a socket for the port given in the command line arguments
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		// If successful, create a new Thread for the server and start the thread
		// Make a record of the server starting successfully
		run = new Thread(this, "Server");
		run.start();
	}
	
	/**
	 * Returns the GUI object for the server
	 * 
	 * @return - The GUI for the server
	 */
	public ServerGUI getGUI(){
		return this.gui;
	}

	/**
	 * Called when the run thread is started
	 * Puts the program in the running state and starts
	 * the manage / receive threads.
	 */
	@Override
	public void run() {
		running = true;
		gui.writeToLog("Server successfully started on port: " + port);
		game = new Game();
		//room = new TestRoom();
		//room.setupRoom();
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
					// Sleep the manage thread for a couple seconds 
					// to not be so resource intensive
					try {
						Thread.sleep(MANAGE_DURATION);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(clients.isEmpty() ){continue;}
					sendToAll("/ping/areYouThere?");
					checkForResponse();
					
				}
			}
		};
		manage.start();
	}
	
	/**
	 * Checks our clients list against our clientReponse list
	 * to see who has responded to our ping packets and who hasn't.
	 * Will disconnect a client if they have not responded after
	 * a certain number of attempts to ping them.
	 */
	private void checkForResponse(){
		// Iterate over every ServerClient
		for(int i = 0; i < clients.size(); i++){
			ServerClient sc = clients.get(i);
			
			// Check our clientResponse doesn't contain their ID
			if(!clientResponse.contains(sc.ID) ){
				
				// If the ServerClient has reached max_attempts,
				// disconnect them via timeOut
				if(sc.attempt >= MAX_ATTEMPTS){
					disconnect(sc.ID, false);
				}
				
				// If they havne't reached MAX_ATTEMPTS, increment their counter
				else{
					sc.attempt++;
					gui.writeToLog(sc.ID + " " + "attempted  " + sc.attempt);
				}
			}
			
			// The server has responded, so we need to to remove their
			// ID from our clientResponse and reset their attempt to 0
			else{
				clientResponse.remove(new Integer(sc.ID) );
				sc.attempt = 0;
			}
			
		}
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
	 * Responsible for reading the bytes into and object
	 * and delegating the work to the appropriate method
	 * depending on the object.
	 * 
	 * @param packet - The packet to process
	 */
	private void process(DatagramPacket packet) {
		// Record the server processing a packet
		gui.writeToLog("Processing packet from: " + packet.getAddress() + ":" + packet.getPort() );
		Object o = null;
		try {
			o = getObject(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// If we received a String in the form of bytes, process the String
		if(o instanceof String){processString((String) o, packet);}
	}
	
	/**
	 * Processes a String received over the network
	 * 
	 * @param string - The String to process
	 * @param packet - The packet the String arrived in
	 */
	private void processString(String string, DatagramPacket packet){
		// Is this packet a connection packet?
		if(string.startsWith("/c/") ){
			if(allPlayersConnected){
				String refusal = "/m/A game is already running, you have been refused connnection/e/";
				send(refusal, packet.getAddress(), packet.getPort() );
				return;
			}
			
			//Assign a unique identifier for this client
			int id = UniqueIdentifier.getIdentifier();
			clients.add(new ServerClient(string.split("/c/|/e/")[1], packet.getAddress(),
					packet.getPort(), id) );
			
			Player toAdd = new Player();
			toAdd.setID(id);
			toAdd.setDirection(Direction.NORTH);
			game.addPlayer(toAdd);
					
			// Record who we connected to the server
			gui.writeToLog("Added to clients: " + string.split("/c/|/e/")[1] + " with ID " + id);
			sendToAll("/m/" + string.split("/c/|/e/")[1] + " connected to the Server!");
					
			// Form a connection confirmation packet, and send it,
			// to the client to confirm a connection was successful
			String confirm = "/c/" + id;
			send(confirm, packet.getAddress(), packet.getPort() );
			
			// Record we sent a connect packet and to who
			gui.writeToLog("Sent confirm packet to: " + packet.getAddress() + ":" + packet.getPort() );
			
			// Check if we are still waiting for all the players to connect, if we are still waiting
			// and we just added the last player, start the timer / game.
			if(!allPlayersConnected && clients.size() == numPlayers){
				startTimer();
				allPlayersConnected = true;
			}
			
			// INTEGRATION DAY
			//try {
			//	Player p = game.getPlayers().get(0);
			//	byte[] data = getBytes(room.getFacingWall(p) );
			//	send(data, packet.getAddress(), packet.getPort() );
			//} catch (IOException e) {
			//	e.printStackTrace();
			//}
		}
				
		// Is this packet a disconnection packet?
		else if(string.startsWith("/dc/") ){
			String ID = string.split("/dc/|/e/")[1];
			disconnect(Integer.parseInt(ID), true);
		}
				
		// Is this packet a message packet?
		else if(string.startsWith("/m/") ){
			sendToAll(string);
		}
				
		// Is this packet responding to a ping from the server?
		else if(string.startsWith("/ping/")){
			clientResponse.add(Integer.parseInt(string.split("/ping/|/e/")[1]) );
		}
		
		// Is a client trying to rotate it's view left?
		else if(string.startsWith("/rotLeft/") ){
			Integer ID = Integer.parseInt(string.split("/rotLeft/|/e/")[1]);
			//if(rotatePlayerViewLeft(ID) ){
			//	try {
			//		Player p = game.getPlayers().get(0);
			//		byte[] data = getBytes(room.getFacingWall(p) );
			//		send(data, packet.getAddress(), packet.getPort() );
			//	} catch (IOException e) {
			//		e.printStackTrace();
			//	}
			//}
		}
		
		// Is a client trying to rotate it's view left?
		else if(string.startsWith("/rotRight/") ){
			Integer ID = Integer.parseInt(string.split("/rotRight/|/e/")[1]);
			//if(rotatePlayerViewRight(ID) ){
			//	try {
			//		Player p = game.getPlayers().get(0);
			//		byte[] data = getBytes(room.getFacingWall(p) );
			//		send(data, packet.getAddress(), packet.getPort() );
			//	} catch (IOException e) {
			//		e.printStackTrace();
			//	}
			//}
		}
				
		// If we could not categorize the packet, print to the server log
		else{
			gui.writeToLog("Could not categorize packet from " + packet.getAddress() + ":" + packet.getPort());
		}
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
		//System.out.println(o.getClass());
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
	 * A method to send an array of bytes to a specific address and port,
	 * through our socket.
	 * 
	 * @param data - the bytes to send
	 * @param address - the address to send to
	 * @param port - the port to send to
	 */
	private synchronized void send(final byte[] data, InetAddress address, int port){
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
	 * Appends an end character to our message and delegates
	 * work to send(Bytes, Address, Port).
	 * 
	 * @param message - The message to send
	 * @param address - The address to send the packet to
	 * @param port - The port to send the packet to
	 */
	private void send(String message, InetAddress address, int port){
		message += "/e/";
		try {
			send(getBytes(message), address, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A simple method that iterates over all the connected clients
	 * in our list, and sends the message to them.
	 * 
	 * @param message - the message to send
	 */
	public void sendToAll(String message){
		// Record we sent a packet and to everyone on the server
		gui.writeToLog("Sent packet to all clients");
		for(int i = 0; i < clients.size(); i++){
			ServerClient client = clients.get(i);
			send(message, client.address, client.port);
		}
	}
	
	/**
	 * Removes a client from our list and reports
	 * the appropriate message to the server log.
	 * 
	 * @param id - ID of the client to disconnect
	 * @param intentional - was the disconnection intentional?
	 */
	private void disconnect(int id, boolean intentional){
		// Find the client with the given id and remove them from our list
		ServerClient sc = null;
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).ID == id){
				sc = clients.get(i);
				clients.remove(i);
				break;
			}
		}
		
		if(sc == null){return;}
		
		// Build an appropriate message for the server log
		String message = "";
		if(intentional){
			message = "Client " + sc.name + "(" + sc.ID + 
					") @" + sc.address + ":" + sc.port + " disconnected";
			
		}
		else{
			message = "Client " + sc.name + "(" + sc.ID + 
					") @" + sc.address + ":" + sc.port + " timed out";
		}
		
		// Record the user being disconnected
		gui.writeToLog(message);
	}
	
	/**
	 * Starts the timer for our game
	 */
	private void startTimer(){
		Timer timer = new Timer(300, this);
	}
	
	private boolean rotatePlayerViewLeft(Integer ID){
		for(int i = 0; i < game.getPlayers().size(); i++){
			Player p = game.getPlayers().get(i);
			if(p.getID().equals(ID) ){
				game.rotateLeft(p);
				gui.writeToLog(ID + " now facing: " + p.getCurrentDirection() );
				return true;
			}
		}
		return false;
	}
	
	private boolean rotatePlayerViewRight(Integer ID){
		gui.writeToLog("Attempting to rotate: " + ID);
		for(int i = 0; i < game.getPlayers().size(); i++){
			Player p = game.getPlayers().get(i);
			gui.writeToLog("Player ID: " + p.getID() );
			if(p.getID().equals(ID) ){
				game.rotateRight(p);
				gui.writeToLog(ID + " now facing: " + p.getCurrentDirection() );
				return true;
			}
		}
		return false;
	}
}
