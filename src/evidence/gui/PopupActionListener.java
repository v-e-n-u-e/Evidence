package evidence.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A listener for the PopUp Menu that appears when the user
 * clicks on an item in the render view
 * 
 * @author Tyler Jones
 *
 */
class PopupActionListener implements ActionListener {
	
	public void actionPerformed(ActionEvent actionEvent) {
		System.out.println("Selected: " + actionEvent.getActionCommand() );
	}
}