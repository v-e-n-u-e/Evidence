package evidence.gameworld.actions;

import evidence.gameworld.items.Item;
/**
 * Enter action
 * Allows players to go through a door into the next room
 * 
 * @author Georgina Murphy
 */
public class Enter extends Action {
	
	public Enter(){
		setName("Enter");
		setDescription("Go into this item");
	}
	
	/**
	 * Method to apply the enter action to the provided item
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
