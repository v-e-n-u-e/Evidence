package evidence.gameworld;

import evidence.gameworld.Wall.Direction;

/**
 * Abstract class for a room
 * 
 * Each room contains 4 walls
 * 
 * @author Georgina Murphy
 *
 */
public abstract class Room {
	private Wall[] walls = new Wall[4];
	
	/**
	 * Constructor for a room
	 * Creates 4 walls - North[0], South[1], East[2] and West[3]
	 */
	public Room() {
		this.walls[0] = new Wall(Direction.NORTH);
		this.walls[1] = new Wall(Direction.SOUTH);
		this.walls[2] = new Wall(Direction.EAST);
		this.walls[3] = new Wall(Direction.WEST);

	}

}
