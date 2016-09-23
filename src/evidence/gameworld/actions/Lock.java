package evidence.gameworld.actions;

import evidence.gameworld.items.Item;
/**
 * Lock action
 * Allows a player to lock an item
 * @author Georgina Murphy
 *
 */
public class Lock extends Action {

	public Lock() {
		setName("Lock");
		setDescription("Lock this item");
	}
	
	
	/**
	 * Method to apply the Lock action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @return item - a new item with an updated state
	 */
	public Item apply(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

}
