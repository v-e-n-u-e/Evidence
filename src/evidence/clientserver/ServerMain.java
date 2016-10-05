package evidence.clientserver;

import evidence.gui.ServerGUI;

/**
 * The ServerMain is responsible for instantiating a new instance
 * of a Server for the Game.  It requires one argument, a port
 * number that we will use to run the server on.  Anyone connecting
 * to a server will need to have this port number.
 * 
 * @author Tyler Jones
 */
public class ServerMain {
	
	// The port the server will run on, and the instance of the Server / ServerGUI objects
	// NumPlayers is an argument needed to run ServerMain, it represents the number of players
	// that the Server will start the game at when that many players have connected
	private int port;
	private int numPlayers;
	private Server server;
	private ServerGUI serverGUI;
	
	/**
	 * A constructor for a ServerMain object, saves the port
	 * number and creates the actual Server object that clients
	 * will connect to.
	 * 
	 * @param port - The port for the server to run on
	 */
	public ServerMain(int port, int numPlayers){
		this.port = port;
		this.numPlayers = numPlayers;
		this.serverGUI = new ServerGUI();
		this.server = new Server(port, serverGUI, numPlayers);
	}
	
	/**
	 * The main method that the user should run to start the server.
	 * Must provide a port number via the command line arguments orr
	 * run configurations in the user's IDE, as well as the numOfPlayers
	 * to start the game at.  
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		// Return and print a message if the user did not provide
		// the correct command line arguments
		if(args.length != 2){
			System.out.println("Usage: java -jar [jarName].jar [port] [numPlayers]");
			return;
		}
		
		// Access the provided port number and call the constructor for ServerMain
		new ServerMain(Integer.parseInt(args[0]), Integer.parseInt(args[1]) );
	}
}
