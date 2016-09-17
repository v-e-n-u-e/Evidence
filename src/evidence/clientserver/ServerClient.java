package evidence.clientserver;

import java.net.InetAddress;

/**
 * Stores information about a client connected to the server.
 * 
 * @author Tyler Jones
 */
public class ServerClient {
	
	// Information about a Client that has connected to the server,
	// including their: Name, Address, Port and ID.
	public String name;
	public InetAddress address;
	public int port;
	public final int ID;
	
	public int attempt = 0;
	
	/**
	 * A constructor for a ServerClient
	 * 
	 * @param name - Name of the Client
	 * @param address - Address of the Client
	 * @param port - Port number for the Client
	 * @param ID - The Unique Identifier Integer for the Client 
	 */
	public ServerClient(String name, InetAddress address, int port, final int ID){
		this.name = name;
		this.address = address;
		this.port = port;
		this.ID = ID;
	}

}
