package evidence.gameworld.items;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
/**
 * Class for a container object.
 * Container object its one that can hold another item
 * @author Georgina Murphy
 *
 */
@XmlRootElement(name = "Container")
public class Container extends Item {

	private ArrayList<Item> containedItems = new ArrayList<Item>();
	private boolean locked;
	private int capacity;

	public Container(String name, String description, List<String> actions, boolean locked, int capacity) {
		super(name, description, actions);
		this.locked = locked;
		this.capacity = capacity;
	}

	public Container(){

	}
	/**
	 * Method to put an item into this container
	 * @param item - item to place in container
	 * @return feedback string about what has happened
	 */
	public String putItem(MovableItem item, Player player){
		if(containedItems.size() < capacity){
			containedItems.add(item);
			capacity-= item.getSize();
			player.removeItem(item);
			return item.toString() + " successfully placed in " + this.toString();
		}
		else{
			return item.toString() + " is too big for " + this.toString() + ", try removing an item";
		}
	}

	public String getItem(MovableItem item, Player player){
		if(containedItems.contains(item)){
			containedItems.remove(item);
			capacity+= item.getSize();
			player.addItem(item);
			return item.toString() + " was successfully removed from " + this.toString() + ". It has been added to your inventory";
		}else{
			return item.toString() + " not inside " + this.toString();
		}
	}
	
	
	public List<Item> getContainedItems(){
		return containedItems;
	}

	@XmlElement
	public int getCapacity(){
		return this.capacity;
	}

	public void setCapacity(int c){
		this.capacity=c;
	}

	public boolean getLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}


}
