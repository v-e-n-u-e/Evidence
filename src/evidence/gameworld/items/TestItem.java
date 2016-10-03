package evidence.gameworld.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.Player;
import evidence.gameworld.actions.PickUp;
/**
 * Class for a container object.
 * Container object its one that can hold another item
 * @author Georgina Murphy
 *
 */
@XmlRootElement
public class TestItem extends Item {
	
	private int capacity; 
	
	public TestItem(String name, String description, List<String> actions, List<String> images, int capacity) {
		super(name, description, actions, images);
		this.capacity = capacity;
	}
	
	public TestItem(){
		
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
	public void setActions(List<String> actions){
		super.setActions(actions);
	}
	
	public void setImages(List<String> images){
		super.setImages(images);
	}
	
	public List<String> getImages(){
		return super.getImages();
	}
	
}
