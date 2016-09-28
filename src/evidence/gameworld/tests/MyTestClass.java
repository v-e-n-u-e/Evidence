package evidence.gameworld.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import evidence.gameworld.Player;
import evidence.gameworld.Room;
import evidence.gameworld.Room.Name;
import evidence.gameworld.Wall.Direction;

public class MyTestClass{
	
	@Test
	public void createNewRoom(){
		Room room = new Room(Name.BATHROOM);
		assertEquals("BATHROOM", room.toString());
		assertEquals(Direction.NORTH, room.getWalls()[0].getDirection());
		assertEquals(Direction.SOUTH, room.getWalls()[1].getDirection());
		assertEquals(Direction.EAST, room.getWalls()[2].getDirection());
		assertEquals(Direction.WEST, room.getWalls()[3].getDirection());
	}
	
	@Test
	public void createPlayer(){
		Player player = new Player();
		Room room = new Room(Name.BATHROOM);
		player.setDirection(Direction.NORTH);
		player.setRoom(room);
		assertEquals(Direction.NORTH, player.getCurrentDirection());
		assertEquals(room, player.getCurrentRoom());
		assertEquals(room.getWalls()[0], player.getWall());
		assertEquals(0, player.getInventory().size());
		
	}

}
