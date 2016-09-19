package evidence.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.SystemColor;

/**
 * ServerGUI acts as a formal log for the server to write to.
 * Is simply a JFrame with a JTextArea in it to display information to 
 * whoever is hosting the server
 * 
 * @author Tyler Jones
 */
public class ServerGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Swing components
	private JPanel contentPane;
	private JTextArea logArea;
	private DefaultCaret caret;
	
	/**
	 * Appends the given message to the ServerGUI's JTextArea
	 * 
	 * @param message - The message we wish to log
	 */
	public void writeToLog(String message){
		logArea.append(message + "\n");
	}

	/**
	 * Create the frame.
	 */
	public ServerGUI() {
		setResizable(false);
		// Set the lookAndFeel to be native to the system
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Server Log", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		logArea = new JTextArea();
		logArea.setBackground(SystemColor.info);
		logArea.setLineWrap(true);
		logArea.setEditable(false);
		caret = (DefaultCaret) logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(logArea);
		
		setVisible(true);
	}
}
