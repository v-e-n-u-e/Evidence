package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;

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
		String feedback = "";
		if (inventoryItem instanceof Key) {
			Key key = (Key) inventoryItem;
			if (gameItem instanceof Door) {
				Door door = (Door) gameItem;
				door.unlock(key);
			} else {
				feedback = "Cannot perform " + this.toString() + " on " + gameItem.toString();
			}
		} else {
			feedback = "Cannot perform " + this.toString() + " using " + inventoryItem.toString();
		}

		return feedback;
	}
}
