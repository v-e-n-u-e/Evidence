package evidence.gameworld;
/**
 * Interface for action
 * 
 * @author Georgina Murphy
 */
public interface Action {
	/**
	 * Method to apply this action to the provided item
	 * 
	 * @param item - the item the action is being applied to
	 * @return item - a new item with an updated state
	 */
	public Item apply(Item item);
	
	/**
	 * Gets the name of this action
	 * @return name
	 */
	public String getName();
	
	/**
	 * Gets the description of this action
	 * @return description
	 */
	public String getDescription();
}
