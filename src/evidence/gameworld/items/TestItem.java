package evidence.gameworld.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.Player;
import evidence.gameworld.actions.Action;
import evidence.gameworld.actions.PickUp;
/**
 * Class for a container object.
 * Container object its one that can hold another item
 * @author Georgina Murphy
 *
 */
public class TestItem extends Item {
	
	private int capacity; 
	
	public TestItem(String name, String description, List<Action> actions, Map<String, String> images, int capacity) {
		super(name, description, actions, images);
		this.capacity = capacity;
	}
	
	@XmlElement
	public void setName(String name){
		super.setName(name);
	}
	
	@XmlElement
	public void setDescription(String description){
		super.setDescription(description);
	}
	
	@XmlElement 
	public void setActions(List<Action> actions){
		super.setActions(actions);
	}
	
	@XmlElement
	public void setImages(Map<String, String> images){
		super.setImages(images);
	}
	
}
