package evidence.gameworld.actions;

import evidence.gameworld.items.Item;

/**
 * Unlock Action
 * Allows a player to unlock and item
 * @author Georgina Murphy
 *
 */
public class Unlock extends Action {

	public Unlock() {
		setName("Unlock");
		setDescription("Unlock this item");
	}

	
	/**
	 * Method to apply the Unlock action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @return item - a new item with an updated state
	 */
	@Override
	public Item apply(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

}
