package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;

/**
 * Unlock Action Allows a player to unlock and item
 * 
 * @author Georgina Murphy
 *
 */
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
	public String apply(Item item, Player player) {
		if (item instanceof Door) {
			Door door = (Door) item;
			// TODO get item from inventory
			// TODO call unlock method in the door class
		}

		return "Cannot perform " + this.toString() + " on " + item.toString();
	}
}
