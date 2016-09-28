package evidence.gameworld.items;


import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name = "Item")
public abstract class Item {
	private String name;
	private String description;
	private List<Action> actions;
	private Map<String, String> images; //Map of image names to their descriptions
	private ImageIcon currentImage;
	private int xPos;
	private int yPos;
	
	public Item(String name, String description, List<Action> actions, Map<String, String> images){
		this.name = name;
		this.description = description;
		this.actions = actions;
		this.images = images;
	}
	
	public Item(){
		
	}
	
	public List<Action> getActions(){
		return actions;
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
	@XmlElement
	public int getYPos(){ 
		return this.yPos;
	}
	@XmlElement
	public int getXPos(){
		return this.xPos;
	}
	
	public String toString(){
		return name;
	}
	

	public void setName(String name){
		this.name = name;
	}
	

	public void setDescription(String description){
		this.description = description;
	}
	

	public void setActions(List<Action> actions){
		this.actions = actions;
	}
	
	@XmlElement
	public void setImages(Map<String, String> images){
		this.images = images;
	}
	
	@XmlElement
	public Map<String,String> getImages(){
		return this.images;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
}
