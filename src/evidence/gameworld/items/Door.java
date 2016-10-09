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
	
	private Room roomOne;
	private Room roomTwo;
	private boolean locked;
	private int keyCode;

	public Door(String name, String description, List<String> actions, Room roomOne,
			Room roomTwo, boolean locked, int keyCode) {
		super(name, description, actions);
		this.roomOne = roomOne;
		this.roomTwo = roomTwo;
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
	public Room getRoomOne() {
		return roomOne;
	}
	
	@XmlTransient
	public Room getRoomTwo() {
		return roomTwo;
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
	
	public void setRoomOne(Room r){
		this.roomOne = r;
	}
	
	public void setRoomTwo(Room r2){
		this.roomTwo = r2;
	}
	
}