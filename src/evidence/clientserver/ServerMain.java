package evidence.clientserver;

/**
 * The ServerMain is responsible for instantiating a new instance
 * of a Server for the Game.  It requires one argument, a port
 * number that we will use to run the server on.  Anyone connecting
 * to a server will need to have this port number.
 * 
 * @author Tyler Jones
 */
public class ServerMain {
	
	// The port the server will run on, and the instance of the Server object
	private int port;
	private Server server;
	
	/**
	 * A constructor for a ServerMain object, saves the port
	 * number and creates the actual Server object that clients
	 * will connect to.
	 * 
	 * @param port - The port for the server to run on
	 */
	public ServerMain(int port){
		this.port = port;
		this.server = new Server(port);
	}
	
	/**
	 * The main method that the user should run to start the server.
	 * Must provide a port number via the command line arguments or
	 * run configurations in the user's IDE.  
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		int port;
		// Return and print a message if the user did not provide
		// the correct command line arguments
		if(args.length != 1){
			System.out.println("Usage: java -jar [jarName].jar [port]");
			return;
		}
		
		// Access the provided port number and call the constructor for ServerMain
		port = Integer.parseInt(args[0]);
		new ServerMain(port);
	}
}
