package evidence.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import evidence.clientserver.infoholders.Event;
import evidence.gameworld.items.Item;

/**
 * A listener for the PopUp Menu that appears when the user
 * clicks on an item in the render view
 * 
 * @author Tyler Jones
 */
class PopupActionListener implements ActionListener {
	
	// The item in the game window the user has clicked on to
	// create this PopupActionListener
	private Item item;
	
	public PopupActionListener(Item item){
		this.item = item;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		Event event = new Event(ClientWindow.getSelected(), item, actionEvent.getActionCommand(), ClientWindow.getID());
		ClientWindow.sendEvent(event);
	}
}