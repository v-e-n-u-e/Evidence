package evidence.gameworld.actions;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;

/**
 * Abstract Class for action
 * 
 * @author Georgina Murphy
 */


@XmlRootElement


public abstract class Action implements Serializable{
	private static final long serialVersionUID = 2467451372271352711L;

	
	private String name;
	private String description;
	
	public Action(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public Action(){
		
	}
	
	
	/**
	 * Method to apply this action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @param player - the player performing this action
	 * @return string - updated state
	 */
	public abstract String apply(Item gameItem, Item inventoryItem, Player player);
	
	/**
	 * Gets the name of this action
	 * @return name
	 */
	@XmlElement
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the description of this action
	 * @return description
	 */
	@XmlElement
	public String getDescription(){
		return description;
	}
	
	public String toString(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}

