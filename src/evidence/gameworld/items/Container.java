package evidence.gameworld.items;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.actions.Action;
/**
 * Class for a container object.
 * Container object its one that can hold another item
 * @author Georgina Murphy
 *
 */
@XmlRootElement(name = "Container")
public class Container extends Item {

	private ArrayList<MovableItem> containedItems = new ArrayList<MovableItem>();
	private boolean locked;
	private int capacity;

	public Container(String name, String description, List<String> actions, boolean locked, int capacity, boolean bloodied) {
		super(name, description, actions, bloodied);
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
		int size = 0;
		for(MovableItem i: containedItems){ 
			size += i.getSize();
		}
		
		size+= item.getSize();
		
		if(size <= capacity){
			containedItems.add(item);
			capacity-= item.getSize();
			player.removeItem(item);
			this.addAction("remove " + item.toString());
			if(item.getBloody()){
				this.makeBloody();
			}
			return item.toString() + " successfully placed in " + this.toString();
		}
		else{
			return "There is not enough room in " + this.toString() + " for " + item.toString();
		}
	}

	public String removeItem(MovableItem item, Player player){
		if(containedItems.contains(item)){
			containedItems.remove(item);
			capacity+= item.getSize();
			player.addItem(item);
			return item.toString() + " was successfully removed from " + this.toString() + ". It has been added to your inventory";
		}else{
			return item.toString() + " not inside " + this.toString();
		}
	}
	
	
	
	public List<MovableItem> getContainedItems(){
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
