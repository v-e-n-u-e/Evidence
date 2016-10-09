package evidence.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import evidence.clientserver.ClientPipe;
import evidence.clientserver.infoholders.Event;
import evidence.clientserver.infoholders.RenderPackage;
import evidence.gameworld.Wall;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The client window is the main window that houses the Scene, the Interaction and Chat Panels.
 * It is what the user interacts with to play the game and chat with other uses.  It is
 * the visual component of the program.
 *
 * It uses a ClientPipe to 'middle-man' any interaction between itself and the Server
 *
 * In a Model-View-Controller sense, ClientWindow is the view.
 *
 * @author Tyler Jones
 */
public class ClientWindow extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;

	// Swing components
	private JPanel contentPane;
	private JPanel invPanel;
	private JTextField messageField;
	private JTextArea chatLog;
	private JTextArea timeLeftArea;
	private DefaultCaret caret;

	// The ClientPipe that gives us a "pipe" to the server
	private static ClientPipe pipe;

	// A thread to run on
	private Thread run;
	private RenderCanvas canvas;

	public static RenderPackage rPackage;
	//private static JButton[][] invButtons;
	private static JButton[] invButtons;
	public static Item currentlySelected;

	/**
	 * Create the frame and attempt to open the connection
	 *
	 * @param name - name of client
	 * @param address - address we are connecting to
	 * @param port - port we are connecting to
	 */
	public ClientWindow(String name, String address, int port) {
		pipe = new ClientPipe(name, address, port, this);

		// Setup the window
		createWindow();
		writeToChatLog("Attempting a connection to address: " + address + ", port: " + port + ", user: " + name);

		// Open the connection with the server, through the client
		if(!pipe.openConnection(address) ){
			writeToChatLog("Connection failed!");
		}

		// Send a connection packet to the server via our client
		String connection = "/c/" + name + "/e/";
		pipe.send(connection);

		// Put the program in running state
		run = new Thread(this, "Running");
		run.start();
	}

	/**
	 * Append a message to our chatLog textArea
	 *
	 * @param message - the message to append
	 */
	public void writeToChatLog(String message){
		chatLog.append(message.trim() + "\n");
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
	private void sendMessage(String message){
		// Do not send empty messages to the server
		if(message.equals("") ){return;}

		// Append the user's name to their message, writeToHistory and clear the txtMessage Area
		message = pipe.getName() + ": " + message;
		message = "/m/" + message;

		// Send message to server
		pipe.send(message);
		messageField.setText("");
	}

	/**
	 * A user has created an Event, and now we need to communicate that event
	 * to the Server for processing.
	 *
	 * @param event - The event to send to the server
	 */
	public static void sendEvent(Event event){
		pipe.send(event);
	}

	/**
	 * Called when disconnecting intentionally. Will close the socket
	 * in client.
	 */
	public void close(){
		// Close the connection
		pipe.close();

		// Close the window
		dispose();
	}

	/**
	 * Called after a successful connection has been made,
	 * starts another thread which will sit and listen for
	 * packets from the server through the socket
	 */
	@Override
	public void run() {
		pipe.receive();
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


		/**
		 * This was used as a tester for adjusting game window based on screen size.
		 * Unused - can delete
		 */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension(screenSize.width-200,screenSize.height-200);
	    // setBounds(0,0,screenSize.width, screenSize.height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//1400 by 700
		setBounds(100, 100, 1400, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setBounds(768, 11, 306, 639);
		//infoPanel.setBounds(frameSize.width/2, 11, (frameSize.width/4)-10, frameSize.height-11);
		//infoPanel.setBounds(screenSize.width/2, 11, (3/14)*screenSize.width, screenSize.height-211);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);

		/**
		 * Sets the count down timer in the appropriate position
		 */
		timeLeftArea = new JTextArea();
		timeLeftArea.setBackground(UIManager.getColor("Button.background"));
		timeLeftArea.setEditable(false);
		timeLeftArea.setBounds(10, 21, 286, 22);
		timeLeftArea.setText(" ");
		infoPanel.add(timeLeftArea);

		/**
		 * Used to set up the inventory portion of the UI. inventoryRefresh can be called any time you need to refresh a players inventory
		 * e.g. when an item is picked up/dropped
		 */
		invPanel = new JPanel();
		invPanel.setBorder(new TitledBorder(null, "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		invPanel.setToolTipText("Inventory");
		invPanel.setBounds(10, 60, 284, 291);
		infoPanel.add(invPanel);



		/**
		 * Button used for turning right in the room
		 */
		JButton rightButton = new JButton("Turn Right");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateRight();
			}
		});
		rightButton.setBounds(167, 361, 129, 43);
		infoPanel.add(rightButton);

		/**
		 * Button used for turning left in the room
		 */
		JButton leftButton = new JButton("Turn Left");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rotateLeft();
			}
		});
		leftButton.setBounds(10, 361, 129, 43);
		infoPanel.add(leftButton);

		/**
		 * Used to display text containing information on the room/wall the player is in
		 */
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Room Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 414, 282, 200);
		infoPanel.add(panel);
		panel.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setBounds(12, 21, 258, 169);
		panel.add(textArea);


		//Set up for multiplayer chat

		JScrollPane chatPane = new JScrollPane();
		chatPane.setBounds(1084, 11, 300, 615);
		//chatPane.setBounds(frameSize.width-(frameSize.width/4), 11, (frameSize.width/4)-11, frameSize.height-11);
		chatPane.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(chatPane);
		chatLog = new JTextArea();
		chatLog.setBackground(SystemColor.info);
		chatLog.setLineWrap(true);
		chatLog.setEditable(false);
		caret = (DefaultCaret) chatLog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		chatPane.setViewportView(chatLog);

		messageField = new JTextField();
		messageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String message = messageField.getText();
					sendMessage(message);
				}
			}
		});
		messageField.setBounds(1084, 629, 300, 20);
		contentPane.add(messageField);
		messageField.setColumns(10);

		canvas = new RenderCanvas();
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Create our PopUp Menu object
				PopupMenu options = new PopupMenu("Options");
				canvas.add(options);

				// Get the item we clicked on, null if we clicked on nothing
				Item item = getItemClickedOn(e.getX(), e.getY() );

				// If we clicked on something, add all of the items available options to the pop up menu
				if(item != null){
					for(String action : item.getActions() ){
						options.add(new MenuItem(action) );
					}

					// Show the PopUp Menu
					options.show(canvas, e.getX(), e.getY() );
					options.addActionListener(new PopupListener(item) );
				}
				else if(currentlySelected != null){
					options.add(new MenuItem("Drop") );
					options.show(canvas, e.getX(), e.getY() );
					options.addActionListener(new PopupListener(null) );
				}
			}
		});
		canvas.setBounds(20, 18, 720, 630);
		contentPane.add(canvas);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				String disconnect = "/dc/" + pipe.getId() + "/e/";
				pipe.send(disconnect);
				close();
			}
		});

		setVisible(true);
		render();
	}

	/**
	 * Re-renders the contentPane components
	 */
	private void render() {
		for(Component c : contentPane.getComponents() ){
			c.repaint();
		}
	}

	public static JButton[] retButtons(){
		return invButtons;
	}

	//This is used when you need to show a player's inventory has changed in some way.
		//This method will remake the buttons/icons based on what the player is holding
		private void inventoryRefresh(){
			ImageIcon[] invIcons = new ImageIcon[9];
/*			rPackage.getInventory().add(new Door("lol","loL",null,null,null,false,123));
		    rPackage.getInventory().get(0).setCurrentImage("img/baxe.png");*/
		    for(int i = 0; i < rPackage.getInventory().size(); i++){
		    	invIcons[i]=(new ImageIcon(rPackage.getInventory().get(i).getImageName()));
		    }
			invPanel.removeAll();
			invButtons = new JButton[9];
			InvListen iListen = new InvListen();
			for(int i = 0; i < 9; i++){
				JButton button = new JButton();
				button.setIcon(invIcons[i]);
				button.setPreferredSize(new Dimension(80,80));
				button.addActionListener(iListen);
				invButtons[i]=button;
				invPanel.add(invButtons[i]);
			}
			iListen.resetSelected();
		}



	/**
	 * Updates the time left displayed in the information panel
	 *
	 * @param time - A string representing time left
	 */
	public void updateTime(String time){
		timeLeftArea.setText(time);
		String[] splitted = time.split(" ");
		int minutes = Integer.parseInt(splitted[2]); // The minutes left
		if(minutes >= 3){timeLeftArea.setBackground(Color.green);}
		else if(minutes >= 1){timeLeftArea.setBackground(Color.yellow);}
		else if(minutes < 1){timeLeftArea.setBackground(Color.red);}
	}

	/**
	 * Used when a client is refused connection, stops them
	 * from being able to send messages to the server.
	 */
	public void doNotAllowMessaging(){
		messageField.setEditable(false);
	}


	//Used for the server/client side interactions
	public void rotateLeft(){
		String rotateLeft = "/rotLeft/" + pipe.getId() + "/e/";
		pipe.send(rotateLeft);
	}

	//Used for the server/client side interactionso
	public void rotateRight(){
		String rotateRight = "/rotRight/" + pipe.getId() + "/e/";
		pipe.send(rotateRight);
	}

	/**
	 * Iterates over each item in the current wall's list and checks
	 * the mouse click against the bounding box for that item.  If the click
	 * was within the bounding box, it returns the item.
	 * Returns null if no item's image was successfully clicked on.
	 *
	 * @param clickX - xPos of mouse click
	 * @param clickY - yPos of mouse click
	 * @return - The item clicked on, null otherwise
	 */
	public Item getItemClickedOn(int clickX, int clickY){
		for(Item i : this.rPackage.getWall().getItems() ){
			Image itemImage = new ImageIcon(i.getImageName() ).getImage();
			int width = itemImage.getWidth(null);
			int height = itemImage.getHeight(null);
			int x = i.getXPos();
			int y = i.getYPos();

			if(clickX >= x && clickX <= x + width){
				if(clickY >= y && clickY <= y + height){
					return i;
				}
			}
		}

		return null;
	}

	public static Item getSelected(){
		return currentlySelected;
	}

	public static void setSelected(Item selected){
		currentlySelected = selected;
	}

	public static Integer getID(){
		return pipe.getId();
	}

	public void reRenderWall(){
		canvas.rPackage = this.rPackage;
		inventoryRefresh();
		canvas.repaint();
	}
}
