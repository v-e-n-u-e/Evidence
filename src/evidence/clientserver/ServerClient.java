package evidence.clientserver;

import java.net.InetAddress;

/**
 * Stores information about a client connected to the server
 * 
 * @author Tyler Jones
 */
public class ServerClient {
	
	public String name;
	public InetAddress address;
	public int port;
	public final int ID;
	public int attempt = 0;
	
	public ServerClient(String name, InetAddress address, int port, final int ID){
		this.name = name;
		this.address = address;
		this.port = port;
		this.ID = ID;
	}

}
