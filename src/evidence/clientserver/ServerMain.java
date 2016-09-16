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
	
	private int port;
	private Server server;
	
	public ServerMain(int port){
		this.port = port;
		this.server = new Server(port);
	}
	
	public static void main(String[] args){
		int port;
		// Return and print a message if the user did not provide
		// the correct command line arguments
		if(args.length != 1){
			System.out.println("Usage: java -jar [jarName].jar [port]");
			return;
		}
		port = Integer.parseInt(args[0]);
		new ServerMain(port);
	}
}
