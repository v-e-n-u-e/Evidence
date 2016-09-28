package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;

import evidence.gameworld.Wall.Direction;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

/**
 * Player Class
 * 
 * @author Georgina Murphy
 *
 */
public class Player {

	private List<Item> inventory;
	private Room currentRoom;
	private Direction currentDirection;
	private Integer ID; // Used to identify clients with a player object

	public void setRoom(Room room) {
		currentRoom = room;
	}

	public void setDirection(Direction direction) {
		currentDirection = direction;
	}

	public void setInventory(ArrayList<Item> items) {
		inventory = items;
	}
	
	public void setID(Integer ID){
		this.ID = ID;
	}
	
	public Integer getID(){
		return this.ID;
	}

	/**
	 * Getter for this players current wall
	 * 
	 * @return this players current wall
	 */
	public Wall getWall() {
		Wall wall = null;
		for (Wall w : currentRoom.getWalls()) {
			if (wall.getDirection().equals(currentDirection)) {
				wall = w;
			}
		}
		return wall;
	}
	
	public Room getCurrentRoom(){
		return this.currentRoom;
	}
	
	/**
	 * Add the provided item to this players inventory
	 * 
	 * @param item
	 *            - the item to add to the inventory
	 */
	public void addItem(MovableItem item) {
		inventory.add(item);
	}

	/**
	 * Remove the provided item from this players inventory
	 * 
	 * @param item
	 *            - the item to remove from the inventory
	 */
	public void removeItem(MovableItem item) {
		inventory.remove(item);
	}

	/**
	 * A method to rotate the view of this current player by changing the wall
	 * they are looking at
	 * 
	 * @param direction
	 *            - L for left or R for right
	 */
	public void rotateView(String direction) {
		switch (currentDirection) {
		case NORTH:
			if (direction.equals("L")){
				currentDirection = Direction.WEST;
				break;
			}
			else{
				currentDirection = Direction.EAST;
				break;
			}
		case EAST:
			if (direction.equals("L")){
				currentDirection = Direction.NORTH;
				break;
			}
			else{
				currentDirection = Direction.SOUTH;
				break;
			}
		case SOUTH:
			if (direction.equals("L")){
				currentDirection = Direction.EAST;
				break;
			}
			else{
				currentDirection = Direction.WEST;
				break;
			}
		case WEST:
			if (direction.equals("L")){
				currentDirection = Direction.SOUTH;
				break;
			}
			else{
				currentDirection = Direction.NORTH;
				break;
			}
		}
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}
}
