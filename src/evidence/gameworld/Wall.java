package evidence.gameworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.items.Item;
 
/**
 * A class for the wall object.
 * Each wall object contains a list of objects that will 
 * be in the players view if they are facing that wall
 * 
 * @author Georgina Murphy
 *
 */
@XmlRootElement
public class Wall implements Serializable{
	private static final long serialVersionUID = 7834346011040118322L;

	/**
	 * A enum class that represents the direction of this wall
	 *
	 */
	@XmlEnum(String.class)
	public enum Direction{
		NORTH,
		SOUTH,
		EAST,
		WEST;
	}
	private Direction direction;
	private List<Item> items;
	private String currentImage;
	private int xPos;
	private int yPos;
	
	public Wall(Direction direction, String fileName, int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.direction = direction;
		currentImage = fileName;
		items = new ArrayList<Item>();
	}
	public Wall(){
		items = new ArrayList<Item>();
	}
	
	/**
	 * Adds an item to this wall
	 * @param item - the item to add
	 */
	public void addItem(Item item){
		items.add(item);
	}
	
	/**
	 * Removes an item from this wall
	 * @param item - the item to remove
	 */
	public void removeItem(Item item){
		items.remove(item);
	}

	/**
	 * @return the direction of this wall
	 */
	@XmlElement
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction d){
		this.direction = d;
	}

	/**
	 * @return the items on this wall
	 */
	@XmlElement
	public List<Item> getItems() {
		return items;
	}
	public void setitems(List<Item> i){
		this.items = i;
	}
	
	@XmlElement(name = "currentImage")
	public String getImageName(){
		return currentImage;
	}
	public void setImageName(String c){
		this.currentImage = c;
	}
	@XmlElement
	public int getX(){
		return xPos;
	}
	@XmlElement
	public int getY(){
		return yPos;
	}
	public void setX(int x){
		this.xPos=x;
	}
	public void setY(int y){
		this.yPos = y;
	}
	
	public String toString(){
		return direction.toString();
	}
}
