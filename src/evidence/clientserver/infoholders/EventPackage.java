package evidence.clientserver.infoholders;

import java.io.Serializable;

import evidence.gameworld.items.Item;

/**
 * An EventPackage object is used to communicate to the server information about an
 * interaction in the game.  Whether this is a player performing an action on a single item,
 * or the user making two items interact with each other. 
 * 
 * If the interaction
 * @author Tyler Jones
 *
 */
public class EventPackage implements Serializable{
	private static final long serialVersionUID = -2938561407292199922L;
	
	// Information about the event that the user is trying to perform
	private Item performing;
	private Item on;
	private String action;
	private Integer ID;
	
	/**
	 * Constructor for an Event object.
	 * 
	 * @param performing - The item performing the action
	 * @param on - The item the action is being performed on
	 * @param action - A string representing the action being performed
	 * @param ID - The ID of the player performing the action
	 */
	public EventPackage(Item performing, Item on, String action, Integer ID){
		this.performing = performing;
		this.on = on;
		this.action = action;
		this.ID = ID;
	}
	
	public Item getPerforming(){
		return this.performing;
	}
	
	public Item getPerformedOn(){
		return this.on;
	}
	
	public String getAction(){
		return this.action;
	}
	
	public Integer getID(){
		return this.ID;
	}
	
	public String toString(){
		return performing + " performed " + action + " on " + on + " using ID " + ID;
	}
}
