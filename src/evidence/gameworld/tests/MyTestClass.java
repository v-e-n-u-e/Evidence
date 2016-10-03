package evidence.gameworld.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import evidence.gameworld.Player;
import evidence.gameworld.Room;
import evidence.gameworld.Room.Name;
import evidence.gameworld.Wall.Direction;
import evidence.gameworld.actions.Action;
import evidence.gameworld.actions.Enter;
import evidence.gameworld.actions.Lock;
import evidence.gameworld.actions.Unlock;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;

public class MyTestClass {

	@Test
	public void createNewRoom() {
		Room room = new Room(Name.BATHROOM);
		assertEquals("BATHROOM", room.toString());
		assertEquals(Direction.NORTH, room.getWalls()[0].getDirection());
		assertEquals(Direction.SOUTH, room.getWalls()[1].getDirection());
		assertEquals(Direction.EAST, room.getWalls()[2].getDirection());
		assertEquals(Direction.WEST, room.getWalls()[3].getDirection());
	}

	@Test
	public void player() {
		Room room = new Room(Name.BATHROOM);
		Player player = createPlayer(room);
		assertEquals(Direction.NORTH, player.getCurrentDirection());
		assertEquals(room, player.getCurrentRoom());
		assertEquals(room.getWalls()[0], player.getWall());
		assertEquals(0, player.getInventory().size());
		Item item = createItem();
		player.addItem(item);
		assertEquals(1, player.getInventory().size());
		player.removeItem(item);
		assertEquals(0, player.getInventory().size());
	}

	@Test
	public void rotateViewL() {
		Room room = new Room(Name.BATHROOM);
		Player player = createPlayer(room);
		player.rotateView("L");
		assertEquals(Direction.WEST, player.getCurrentDirection());
		player.rotateView("L");
		assertEquals(Direction.SOUTH, player.getCurrentDirection());
		player.rotateView("L");
		assertEquals(Direction.EAST, player.getCurrentDirection());
		player.rotateView("L");
		assertEquals(Direction.NORTH, player.getCurrentDirection());
	}
	
	@Test
	public void rotateViewR() {
		Room room = new Room(Name.BATHROOM);
		Player player = createPlayer(room);
		player.rotateView("R");
		assertEquals(Direction.EAST, player.getCurrentDirection());
		player.rotateView("R");
		assertEquals(Direction.SOUTH, player.getCurrentDirection());
		player.rotateView("R");
		assertEquals(Direction.WEST, player.getCurrentDirection());
		player.rotateView("R");
		assertEquals(Direction.NORTH, player.getCurrentDirection());
	}
	
	@Test
	public void enterAction(){
		Room bathroom = new Room(Name.BATHROOM);
		Room kitchen = new Room(Name.KITCHEN);
		Door door = createDoor(bathroom, kitchen);
		Player player = createPlayer(bathroom);
		assertEquals(bathroom, player.getCurrentRoom());
		assertEquals("Enter", door.getActions().get(2).toString());
		door.getActions().get(2).apply(door, player);
		assertEquals(kitchen, player.getCurrentRoom());
	}
	
//	@Test
//	public void removeItemIncorrect(){
//		Room room = new Room(Name.BATHROOM);
//		Player player = createPlayer(room);
//		assertEquals(Direction.NORTH, player.getCurrentDirection());
//		assertEquals(room, player.getCurrentRoom());
//		assertEquals(room.getWalls()[0], player.getWall());
//		assertEquals(0, player.getInventory().size());
//		Item item = createItem();
//		Item item2 = createItem();
//		player.addItem(item);
//		player.removeItem(item2);
//		
//	}

	// ----------------------------------------------------
	// Helper Methods
	// ----------------------------------------------------

	public Item createItem() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new Unlock());
		HashMap<String, String> images = new HashMap<String, String>();
		images.put("cbbox.png", "Clean cardboard box");
		Item item = new Container("Cardbord Box", "A cardboard box", actions, images, 7);
		item.setCurrentImage("cbbox.png");
		item.setXPos(30);
		item.setYPos(100);
		return item;
	}
	
	public Door createDoor(Room door1, Room door2) {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new Unlock());
		actions.add(new Lock());
		actions.add(new Enter());
		HashMap<String, String> images = new HashMap<String, String>();
		images.put("door.png", "A door between the Bathroom and the Kitchen");
		Door door = new Door("Cardbord Box", "A cardboard box", actions, images, door1, door2, false, 7);
		door.setCurrentImage("door.png");
		door.setXPos(30);
		door.setYPos(100);
		return door;
	}

	public Player createPlayer(Room r) {
		Player player = new Player();
		player.setDirection(Direction.NORTH);
		player.setRoom(r);
		return player;

	}
}
