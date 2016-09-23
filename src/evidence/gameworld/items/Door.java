package evidence.gameworld.items;

import java.util.List;
import java.util.Map;

import evidence.gameworld.Room;
import evidence.gameworld.actions.Action;

/**
 * Class for a door object.
 * 
 * A door object links two room objects 
 * @author Georgina Murphy
 *
 */
public class Door extends Item {

	private Room roomOne;
	private Room roomTwo;
	private boolean locked;
	private int keyCode;

	public Door(String name, String description, List<Action> actions, Map<String, String> images,
			Room roomOne, Room roomTwo, boolean locked, int keyCode) {
		super(name, description, actions, images);
		this.roomOne = roomOne;
		this.roomTwo = roomTwo;
		this.locked = locked;
		this.keyCode = keyCode;
	}
	
	public String unlock(Key key){
		if(checkKey(key)){
			locked = false;
			return "Door is now unlocked";
		}
		else
			return "Incorrect key. Door remains locked";
	}
	
	public boolean checkKey(Key key){
		if(key.getCode() == keyCode)
			return true;
		
		else 
			return false;
	}
	
}
