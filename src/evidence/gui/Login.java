package evidence.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The login class is purely here to serve as preparation for
 * the client stage of the application.  It will ask the user for:
 * A name, an Address and a Port.
 * 
 * A ClientWindow is then created and these three variables are passed to it.
 * When the ClientWindow is created, which is what the user interacts with to send
 * messages, the ClientWindow will create a Client object.  The client is responsible
 * for all Network interactions with the Server.  
 * 
 * So ClientWindow is the GUI, Client is the back-end Network interaction.
 * 
 * @author Tyler Jones
 */
public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField addressField;
	private JTextField textField;
	private JTextField portField;
	private JLabel lblName;
	private JLabel lblIpAddress;
	private JLabel lblPort;

	/**
	 * Create the frame.
	 */
	public Login() {
		// Set the lookAndFeel to be native to the system
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(79, 158, 135, 20);
		contentPane.add(addressField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(79, 103, 135, 20);
		contentPane.add(textField);
		
		portField = new JTextField();
		portField.setColumns(10);
		portField.setBounds(79, 214, 135, 20);
		contentPane.add(portField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve the user input from our 3 textField's and call the login() method
				String name = textField.getText();
				String address = addressField.getText();
				int port = Integer.parseInt(portField.getText() );
				login(name, address, port);
			}
		});
		
		btnNewButton.setBounds(102, 271, 89, 23);
		contentPane.add(btnNewButton);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(79, 88, 46, 14);
		contentPane.add(lblName);
		
		lblIpAddress = new JLabel("IP Address:");
		lblIpAddress.setBounds(79, 141, 56, 14);
		contentPane.add(lblIpAddress);
		
		lblPort = new JLabel("Server Port:");
		lblPort.setBounds(79, 200, 70, 14);
		contentPane.add(lblPort);
	}
	
	/**
	 * Called when the user clicks the login button
	 */
	private void login(String name, String address, int port) {
		dispose();
		//ClientWindow client = new ClientWindow(name, address, port);
		System.out.println(name + " " + address + " " + port);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
