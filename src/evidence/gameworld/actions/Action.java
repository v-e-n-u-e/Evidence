package evidence.gameworld.actions;

import evidence.gameworld.items.Item;

/**
 * Abstract Class for action
 * 
 * @author Georgina Murphy
 */
public abstract class Action {
	
	private String name;
	private String description;
	
	
	/**
	 * Method to apply this action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @return item - a new item with an updated state
	 */
	public abstract Item apply(Item item);
	
	/**
	 * Gets the name of this action
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the description of this action
	 * @return description
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Sets the name of this action
	 * @return name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Sets the description of this action
	 * @param description - description to set
	 */
	public void setDescription(String description){
		this.description = description;
	}
}
