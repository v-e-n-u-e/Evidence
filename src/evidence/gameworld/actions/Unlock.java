package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;

/**
 * Unlock Action Allows a player to unlock and item
 * 
 * @author Georgina Murphy
 *
 */ 
@XmlRootElement
public class Unlock extends Action {

	public Unlock() {
		super("Unlock", "Unlock this item");
	}


	/**
	 * Method to apply the Unlock action to the provided item
	 * 
	 * @param item
	 *            - the item the action is being applied to
	 * @return string - updated state
	 */
	@Override
	public String apply(Item gameItem, Item inventoryItem, Player player) {
		if (gameItem instanceof Door) {
			Door door = (Door) gameItem;
			// TODO get item from inventory
			// TODO call unlock method in the door class
		}

		return "Cannot perform " + this.toString() + " on " + gameItem.toString();
	}
}
