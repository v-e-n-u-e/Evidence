package evidence.gameworld.actions;

import evidence.gameworld.items.Item;

/**
 * Pick up action
 * allows player to pickup up an item and put it in their inventory
 * 
 * @author Georgina Murphy
 */
public class PickUp implements Action {

	private String name = "Pick up";
	private String description = "Pick up this item and put it in your inventory";
	
	
	/**
	 * Method to apply the pickup action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @return item - a new item with an updated state
	 */
	@Override
	public Item apply(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
