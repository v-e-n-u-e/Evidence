package evidence.gameworld.items;


import java.io.Serializable;
import java.util.List;
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
public abstract class Item implements Serializable{
	
	private String name;
	private String description;
	private List<Action> actions;
	private List<String> images; //Map of image names to their descriptions
	private String currentImage;
	private int xPos;
	private int yPos;
	
	public Item(String name, String description, List<Action> actions, List<String> images){
		this.name = name;
		this.description = description;
		this.actions = actions;
		this.images = images;
	}
	
	public Item(){
		
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
	
	public String toString(){
		return name;
	}
	
	@XmlElement
	public void setName(String name){
		this.name = name;
	}
	
	@XmlElement
	public void setDescription(String description){
		this.description = description;
	}
	
	@XmlElement
	public void setActions(List<Action> actions){
		this.actions = actions;
	}
	
	@XmlElement
	public void setImages(List<String> images){
		this.images = images;
	}
	
	public List<String> getImages(){
		return this.images;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	@XmlElement
	public String getImageName(){
		return this.currentImage;
	}
	
	@XmlElement
	public int getYPos(){ 
		return this.yPos;
	}
	@XmlElement
	public int getXPos(){
		return this.xPos;
	}
	
	public List<Action> getActions(){
		return actions;
	}
}
