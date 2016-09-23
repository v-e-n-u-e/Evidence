package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;

import evidence.gameworld.Wall.Direction;
import evidence.gameworld.items.Item;

/**
 * Player Class
 * @author Georgina Murphy
 *
 */
public class Player {

	private List<Item> inventory;
	private Room currentRoom;
	private Direction currentDirection;

	public void setRoom(Room room){
		currentRoom = room;
	}

	public void setDirection(Direction direction) {
		currentDirection = direction;
	}
	
	public void setInventory(ArrayList<Item> items){
		inventory = items;
	}

	
	/**
	 * Getter for this players current wall
	 * @return this players current wall
	 */
	public Wall getWall(){
		Wall wall = null;
		for (Wall w : currentRoom.getWalls()) {
			if (wall.getDirection().equals(currentDirection)) {
				wall = w;
			}
		}
		return wall;
	}
	
	/**
	 * Add the provided item to this players inventory
	 * @param item - the item to add to the inventory
	 */
	public void addItem(Item item){
		inventory.add(item);
	}
	
	/**
	 * Remove the provided item from this players inventory
	 * @param item - the item to remove from the inventory
	 */
	public void removeItem(Item item){
		inventory.remove(item);
	}
	
}
