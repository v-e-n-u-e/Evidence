package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;
/**
 * Lock action
 * Allows a player to lock an item
 * @author Georgina Murphy
 *
 */
@XmlRootElement
public class Lock extends Action {

	public Lock() {
		super("Lock", "Lock this item");
	}
	
	/**
	 * Method to apply the Lock action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @return string - updated state
	 */
	public String apply(Item gameItem, Item inventoryItem, Player player) {
		
		
		if(gameItem instanceof Door){
			Door door = (Door)gameItem;
			// TODO get item from inventory
			// TODO call lock method in the door class
		}
		
		return "Cannot perform " + this.toString() + " on " + gameItem.toString();
	}

}
