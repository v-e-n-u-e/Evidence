package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;
import evidence.gameworld.items.MovableItem;

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
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";

		if (inventoryItem instanceof Key) {
			Key key = (Key) inventoryItem;
			if (gameItem instanceof Door) {
				Door door = (Door) gameItem;
				if (checkKey(key, door.getKeyCode())) {
					door.setLocked(false);
					feedback = "Door is unlocked";
				}else{
					feedback =  "Incorrect key. Door remains locked";
				}
			}else if (gameItem.toString().equals("Safe")) {
				Container safe = (Container) gameItem;
				if (checkKey(key, 555)) {
					safe.setLocked(false);
					feedback =  "Safe is unlocked";
				} else{
					feedback =  "Incorrect key. Safe remains locked";
				}
			} else {
				feedback = "Cannot perform " + this.toString() + " on " + gameItem.toString();
			}
		} else {
			feedback = "Cannot perform " + this.toString() + " using " + inventoryItem.toString();
		}

		return feedback;
	}

	/**
	 * Method to check the key against the doors code
	 *
	 * @param key
	 *            - the key to check against this door
	 * @return boolean T if correct Key, F if incorrect
	 */
	public boolean checkKey(Key key, int keyCode) {
		if (key.getCode() == keyCode)
			return true;

		else
			return false;
	}
}
