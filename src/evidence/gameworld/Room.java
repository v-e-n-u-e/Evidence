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
	
	/**
	 * Constructor for a room
	 * Creates 4 walls - North[0], South[1], East[2] and West[3]
	 */
	public Room(Name name) {
		this.name = name;
		this.walls[0] = new Wall(Direction.NORTH, "", 0, 0);
		this.walls[1] = new Wall(Direction.SOUTH, "", 0, 0);
		this.walls[2] = new Wall(Direction.EAST, "", 0, 0);
		this.walls[3] = new Wall(Direction.WEST, "", 0, 0);
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
