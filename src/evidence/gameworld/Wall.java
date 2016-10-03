package evidence.gameworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import evidence.gameworld.items.Item;
 
/**
 * A class for the wall object.
 * Each wall object contains a list of objects that will 
 * be in the players view if they are facing that wall
 * 
 * @author Georgina Murphy
 *
 */
public class Wall implements Serializable{
	private static final long serialVersionUID = 7834346011040118322L;

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
	private String currentImage;
	private int xPos;
	private int yPos;
	
	public Wall(Direction direction, String fileName, int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.direction = direction;
		currentImage = fileName;
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
	
	public String getImageName(){
		return currentImage;
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
}
