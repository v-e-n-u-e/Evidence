package evidence.gameworld.items;


import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.actions.Action;

/**
 * Abstract class for item.
 * 
 * Each item will have a name, description, 
 * 
 * @author Georgina Murphy
 *
 */
public abstract class Item {
	private String name;
	private String description;
	private List<Action> actions;
	private Map<String, String> images; //Map of image names to their descriptionsi
	private ImageIcon currentImage;
	private int xPos;
	private int yPos;
	
	public Item(String name, String description, List<Action> actions, Map<String, String> images){
		this.name = name;
		this.description = description;
		this.actions = actions;
		this.images = images;
	}
		
	@XmlElement
	public void setCurrentImage(String fileName){
		currentImage = new ImageIcon(fileName, images.get(fileName));
	}
	
	@XmlElement
	public void setXPos(int xPos){
		this.xPos = xPos;
	}
	
	@XmlElement
	public void setYPos(int yPos){
		this.yPos = yPos;
	}
	
	public String toString(){
		return name;
	}
}
