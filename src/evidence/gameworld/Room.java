package evidence.gameworld;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Wall.Direction;
import evidence.gameworld.items.Item;

/**
 * Abstract class for a room
 * 
 * Each room contains 4 walls
 * 
 * @author Georgina Murphy
 *
 */
@XmlRootElement
public class Room implements Serializable{
	private static final long serialVersionUID = -813837991125057316L;

	/**
	 * A enum class that represents the name of this room
	 *
	 */
	@XmlEnum(String.class)
	public enum Name{
		KITCHEN,
		BATHROOM,
		BEDROOM,
		OFFICE,
		GARAGE,
		LOUNGE;
	}
	
	private Wall[] walls = new Wall[4];
	private Name name;
	private String w1;
	private String w2;
	private String w3;
	private String w4;
	
	/**
	 * Constructor for a room
	 * Creates 4 walls - North[0], South[1], East[2] and West[3]
	 */
	public Room(Name name, String w1, String w2, String w3, String w4) {
		this.name = name;
		this.w1 = w1;
		this.w2 = w2;
		this.w3 = w3;
		this.w4 = w4;
		this.walls[0] = new Wall(Direction.NORTH, w1, 0, 0);
		this.walls[1] = new Wall(Direction.EAST, w2, 0, 0);
		this.walls[2] = new Wall(Direction.SOUTH, w3, 0, 0);
		this.walls[3] = new Wall(Direction.WEST, w4, 0, 0);
	}
	
	public Room(){
		
	}
	
	@XmlElement
	public Wall[] getWalls(){
		return walls;
	}
	public void setWalls(Wall[] w){
		this.walls = w;
	}
	@XmlElement
	public Name getName(){
		return this.name;
	}
	public void setName(Name n){
		this.name = n;
	}
	
	public void removeItem(Direction dir, Item item){
		for(Wall wall : walls){
			if(wall.getDirection().equals(dir)){
				wall.removeItem(item);
			}
		}
	}
	
	public String toString(){
		return name.toString();
	}
}
