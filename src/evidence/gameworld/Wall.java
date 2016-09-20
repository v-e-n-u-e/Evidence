package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;
 
/**
 * A class for the wall object.
 * Each wall object contains a list of objects that will 
 * be in the players view if they are facing that wall
 * 
 * @author Georgina Murphy
 *
 */
public class Wall {
	
	/**
	 * A enum class that represents the direction of this wall
	 *
	 */
	public enum Direction{
		NORTH,
		SOUTH,
		EAST,
		WEST;
	}
	private Direction direction;
	private List<Item> items;
	
	public Wall(Direction direction){
		this.direction = direction;
		items = new ArrayList<Item>();
	}
	
	/**
	 * Adds an item to this wall
	 * @param item - the item to add
	 */
	public void addItem(Item item){
		items.add(item);
	}
	
	/**
	 * Removes an item from this wall
	 * @param item - the item to remove
	 */
	public void removeItem(Item item){
		items.remove(item);
	}

	/**
	 * @return the direction of this wall
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @return the items on this wall
	 */
	public List<Item> getItems() {
		return items;
	}
}
