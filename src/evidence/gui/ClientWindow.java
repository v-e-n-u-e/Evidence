package evidence.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import evidence.clientserver.ClientPipe;

/**
 * The ClientWindow is responsible for providing a method of interaction
 * between the User and the program.  It is the visual representation of
 * the Chat to the user.  Any network interaction that needs to occur
 * is handled with a Client object that this ClientWindow has access to.
 * 
 * In a Model-View-Controller sense, ClientWindow serves as the view.
 * 
 * @author Tyler
 */
public class ClientWindow extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtMessage;
	private JTextArea history;
	
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
		writeToHistory("Attempting a connection to address: " + address + ", port: " + port + ", user: " + name);
		
		// Open the connection with the server, through the client
		if(!client.openConnection(address) ){
			writeToHistory("Connection failed!");
		}
		
		// Send a connection packet to the server via our client
		String connection = "/c/" + name + "/e/";
		client.send(connection.getBytes() );
		
		running = true;
		run = new Thread(this, "Running");
		run.start();
	}
	
	/**
	 * Append a message to our 'history' window
	 * 
	 * @param message - the message to append
	 */
	public void writeToHistory(String message){
		history.append(message + "\n");
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
		txtMessage.setText("");
	}
	
	public void listen(){
		listen = new Thread("Listen"){
			public void run(){
				while(running){
					String message = client.receive();
					if(message.startsWith("/c/") ){
						client.setId(Integer.parseInt(message.split("/c/|/e/")[1]) );
						writeToHistory("Successful connection! ID: " + client.getId() );
					}
					else if(message.startsWith("/m/") ){
						message = message.split("/m/|/e/")[1];
						writeToHistory(message);
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
		System.out.println("DISPOSED");
	}
	
	@Override
	public void run() {
		listen();
	}
	
	// =======================================================================
	//                        GUI VISUAL STUFF
	// =======================================================================
	
	private void createWindow(){
		// Set the lookAndFeel to be native to the system
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Initial setup of the client window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setTitle("Cherno Chat Client");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Set our layout to a GirdBagLayout
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{35, 730, 30, 5}; // Sum of these must be 800
		gbl_contentPane.rowHeights = new int[]{50, 410, 40}; // Sum of these must be 500
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		history = new JTextArea();
		history.setEditable(false);
		
		// When history is written to, snap the scroll pane to the new message
		//caret = (DefaultCaret) history.getCaret();
		//caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JScrollPane scroll = new JScrollPane(history); // Add scroll pane to the history
		GridBagConstraints gbc_scrollConstraints = new GridBagConstraints();
		
		// Deal with padding the history text area and making it look nice in the frame
		gbc_scrollConstraints.insets = new Insets(5, 5, 5, 5);
		gbc_scrollConstraints.fill = GridBagConstraints.BOTH;
		gbc_scrollConstraints.gridx = 0;
		gbc_scrollConstraints.gridy = 0;
		gbc_scrollConstraints.gridwidth = 3;
		gbc_scrollConstraints.gridheight = 2;
		
		// Add the scroll object(which contains our history text area, to the content pane
		contentPane.add(scroll, gbc_scrollConstraints);
		
		// Set up the message text area
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					send(txtMessage.getText() );
				}
			}
		});
		
		// Pad the txtMessage area
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		// Set up the send button
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(txtMessage.getText() );
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		contentPane.add(btnSend, gbc_btnSend);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				String disconnect = "/dc/" + client.getId() + "/e/";
				client.send(disconnect.getBytes() );
				close();
			}
		});
		
		// Make client window visible
		setVisible(true);
		txtMessage.requestFocusInWindow();
	}
}
