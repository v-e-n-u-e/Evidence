package evidence.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import evidence.clientserver.ClientPipe;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientWindow extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField messageField;
	private JTextArea chatLog;
	
	private ClientPipe client;
	private Thread listen, run;
	
	private boolean running;
	
	/**
	 * Create the frame and attempt to open the connection
	 * 
	 * @param name - name of client
	 * @param address - address we are connecting to
	 * @param port - port we are connecting to
	 */
	public ClientWindow(String name, String address, int port) {
		client = new ClientPipe(name, address, port);
		
		// Setup the window
		createWindow();
		writeToChatLog("Attempting a connection to address: " + address + ", port: " + port + ", user: " + name);
		
		// Open the connection with the server, through the client
		if(!client.openConnection(address) ){
			writeToChatLog("Connection failed!");
		}
		
		// Send a connection packet to the server via our client
		String connection = "/c/" + name + "/e/";
		client.send(connection.getBytes() );
		
		running = true;
		run = new Thread(this, "Running");
		run.start();
	}
	
	/**
	 * Append a message to our chatLog textArea
	 * 
	 * @param message - the message to append
	 */
	public void writeToChatLog(String message){
		chatLog.append(message + "\n");
	}
	
	/**
	 * Append the given message to the history, and send it to
	 * the server via client, so that it can propagate to all
	 * the clients connected to the server.
	 * 
	 * We append "/m/" to the message so the server knows what type
	 * of packet it is receiving.
	 * 
	 * @param message - the message to send to the client
	 */
	private void send(String message){
		// Do not send empty messages to the server
		if(message.equals("") ){return;}
		
		// Append the user's name to their message, writeToHistory and clear the txtMessage Area
		message = client.getName() + ": " + message;
		message = "/m/" + message;
		
		// Send message to server
		client.send(message.getBytes() );
		messageField.setText("");
	}
	
	public void listen(){
		listen = new Thread("Listen"){
			public void run(){
				while(running){
					String message = client.receive();
					if(message.startsWith("/c/") ){
						client.setId(Integer.parseInt(message.split("/c/|/e/")[1]) );
						writeToChatLog("Successful connection! ID: " + client.getId() );
					}
					else if(message.startsWith("/m/") ){
						message = message.split("/m/|/e/")[1];
						writeToChatLog(message);
					}
				}
			}
		};
		listen.start();
	}
	
	/**
	 * Called when disconnecting intentionally. Will close the socket
	 * in client.
	 */
	public void close(){
		// Close the connection
		client.close();
		
		// Close the window
		running = false;
		dispose();
	}
	
	@Override
	public void run() {
		listen();
	}
	
	
	
	// =================================================================================================================================================================
	//                                                             GUI VISUAL STUFF AND LISTENERS
	// =================================================================================================================================================================
	
	/**
	 * Create the frame.
	 */
	public void createWindow() {
		setResizable(false);
		// Set the lookAndFeel to be native to the system
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel renderPanel = new JPanel();
		renderPanel.setBorder(new TitledBorder(null, "Scene", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		renderPanel.setBounds(10, 11, 748, 639);
		contentPane.add(renderPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setBounds(768, 11, 306, 639);
		contentPane.add(infoPanel);
		
		JScrollPane chatPane = new JScrollPane();
		chatPane.setBounds(1084, 11, 300, 615);
		chatPane.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(chatPane);
		
		chatLog = new JTextArea();
		chatLog.setBackground(SystemColor.info);
		chatLog.setLineWrap(true);
		chatLog.setEditable(false);
		chatPane.setViewportView(chatLog);
		
		messageField = new JTextField();
		messageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String message = messageField.getText();
					send(message);
				}
			}
		});
		messageField.setBounds(1084, 629, 300, 20);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				String disconnect = "/dc/" + client.getId() + "/e/";
				client.send(disconnect.getBytes() );
				close();
			}
		});
		
		setVisible(true);
	}
}
