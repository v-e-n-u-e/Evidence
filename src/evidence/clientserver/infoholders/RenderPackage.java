package evidence.clientserver.infoholders;

import java.io.Serializable;
import java.util.List;

import evidence.gameworld.Wall;
import evidence.gameworld.items.Item;

/**
 * A RenderPackage is a class that holds the objects a client needs to render 
 * the game view to the user.  Currently, a RenderPackage holda a wall, and a list
 * of items.  The wall represents the Wall in the game the user is currently looking at,
 * and the list of items represents the player's inventory.
 * 
 * These are put into a RenderPackage to make serialization of all render components easier,
 * and easily accessible through one instance of a RenderPackage.
 * 
 * @author Tyler Jones
 *
 */
public class RenderPackage implements Serializable{
	private static final long serialVersionUID = -2017422499501784164L;
	
	// The wall the player is facing
	private Wall wall;
	
	// The player's inventory of items
	private List<Item> inventory;
	
	/**
	 * A constructor for a RenderPackage
	 * 
	 * @param wall - The wall to render
	 * @param inventory - The inventory to render
	 */
	public RenderPackage(Wall wall, List<Item> inventory){
		this.wall = wall;
		this.inventory = inventory;
	}
	
	/**
	 * Getter for the wall field
	 * 
	 * @return - The wall to render
	 */
	public Wall getWall(){
		return this.wall;
	}
	
	/**
	 * Getter for the inventory field
	 * 
	 * @return - The inventory to render
	 */
	public List<Item> getInventory(){
		return this.inventory;
	}

}
