package evidence.gameworld.items;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import evidence.gameworld.Room;

/**
 * Class for a door object.
 * 
 * A door object links two room objects
 * 
 * @author Georgina Murphy
 *
 */
@XmlRootElement
public class Door extends Item{
	
	private Room room;
	private boolean locked;
	private int keyCode;
	private Door door;

	public Door(String name, String description, List<String> actions, Room room,
			 boolean locked, int keyCode, boolean bloodied) {
		super(name, description, actions, bloodied);
		this.room = room;
		this.locked = locked;
		this.keyCode = keyCode;
	}
	
	public Door(){
		
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
	
	@XmlTransient
	public Room getRoom() {
		return room;
	}
	
	@XmlElement
	public boolean getLocked(){
		return locked;
	}
	
	@XmlElement
	public int getKeyCode(){
		return keyCode;
	}
	
	public void setLocked(boolean l){
		this.locked = l;
	}
	
	public void setRoom(Room r){
		this.room = r;
	}

	public void setOtherDoor(Door door) {
		this.door = door;
	}
	
	public Door getDoor(){
		return door;
	}
}