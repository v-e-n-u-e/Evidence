package evidence.gameworld.items;


import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.actions.Action;
import evidence.gameworld.actions.CutUp;
import evidence.gameworld.actions.Enter;
import evidence.gameworld.actions.Lock;
import evidence.gameworld.actions.PickUp;
import evidence.gameworld.actions.Unlock;

/**
 * Abstract class for item.
 * 
 * Each item will have a name, description, 
 * 
 * @author Georgina Murphy
 *
 */
@XmlRootElement(name = "Item")
public abstract class Item implements Serializable{
	
	private String name;
	private String description;
	private List<String> actions;
	private List<String> images; //Map of image names to their descriptions
	private String currentImage;
	private int xPos;
	private int yPos;
	
	public Item(String name, String description, List<String> actions, List<String> images){
		this.name = name;
		this.description = description;
		this.actions = actions;
		this.images = images;
	}
	
	public Item(){
		
	}
	
	public List<String> getActions(){
		return actions;
	}
	
	
	public Action getAction(String actionString){
		Action action = null;
		switch (actionString){
		case "CutUp":
			action = new CutUp();
			break;
		case "Enter":
			action = new Enter();
			break;
		case "Lock":
			action = new Lock();
			break;
		case "Unlock":
			action = new Unlock();
			break;
		case "PickUp":
			action = new PickUp();
			break;
		}
		return action;
	}
	
	@XmlElement
	public void setCurrentImage(String fileName){
		//for(String image : images){
		//	if(image.equals(fileName)){
		//		currentImage = image;
		//	}
		this.currentImage = fileName;	
		//}
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
	
	public String getImageName(){
		return this.currentImage;
	}
	

	public void setName(String name){
		this.name = name;
	}
	

	public void setDescription(String description){
		this.description = description;
	}
	

	public void setActions(List<String> actions){
		this.actions = actions;
	}
	
	@XmlElement
	public void setImages(List<String> images){
		this.images = images;
	}
	
	@XmlElement
	public List<String> getImages(){
		return this.images;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
}
