package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
/**
 * Enter action
 * Allows players to go through a door into the next room
 * 
 * @author Georgina Murphy
 */
public class Enter extends Action {
	
	public Enter(){
		super("Enter", "Go into this item");
	}
	
	/**
	 * Method to apply the enter action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @return string - updated state
	 */
	@Override
	public String apply(Item gameItem, Item inventoryItem,  Player player) {
		String feedback = "";
		if(gameItem instanceof Door){
			Door door = (Door)gameItem;
			if(door.getRoomOne().equals(player.getCurrentRoom())){
				player.setRoom(door.getRoomTwo());
			}else{
				player.setRoom(door.getRoomOne());
			}
			feedback = "You are now in the" + player.getCurrentRoom().toString();
		}
		
		return "Cannot perform " + this.toString() + " on " + gameItem.toString();
	}

	public String toString(){
		return super.getName();
	}

	
}
