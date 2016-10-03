package evidence.gameworld.items;

import java.util.List;
import java.util.Map;

import evidence.gameworld.Room;
import evidence.gameworld.actions.Action;

/**
 * Class for a door object.
 * 
 * A door object links two room objects
 * 
 * @author Georgina Murphy
 *
 */
public class Door extends Item {

	private Room roomOne;
	private Room roomTwo;
	private boolean locked;
	private int keyCode;

	public Door(String name, String description, List<Action> actions, List<String> images, Room roomOne,
			Room roomTwo, boolean locked, int keyCode) {
		super(name, description, actions, images);
		this.roomOne = roomOne;
		this.roomTwo = roomTwo;
		this.locked = locked;
		this.keyCode = keyCode;
	}

	/**
	 * A method to check the provided key matches the door, unlock if correct
	 * 
	 * @param key
	 *            - the key that is being used to unlock the door
	 * @return Status update
	 */
	public String unlock(Key key) {
		if (checkKey(key)) {
			locked = false;
			return "Door is unlocked";
		} else
			return "Incorrect key. Door remains locked";
	}

	/**
	 * A method to check the provided key matches the door, lock if correct
	 * 
	 * @param key
	 *            - the key that is being used to lock the door
	 * @return Status update
	 */
	public String lock(Key key) {
		if (checkKey(key)) {
			locked = true;
			return "Door is locked";
		} else
			return "Incorrect key. Door remains unlocked";
	}

	/**
	 * Method to check the key against the doors code
	 * 
	 * @param key
	 *            - the key to check against this door
	 * @return boolean T if correct Key, F if incorrect
	 */
	public boolean checkKey(Key key) {
		if (key.getCode() == keyCode)
			return true;

		else
			return false;
	}

	public Room getRoomOne() {
		return roomOne;
	}
	
	public Room getRoomTwo() {
		return roomTwo;
	}
	
	public boolean getLocked(){
		return locked;
	}

}
