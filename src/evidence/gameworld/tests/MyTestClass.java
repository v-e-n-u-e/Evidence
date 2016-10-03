package evidence.gameworld.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import evidence.gameworld.Player;
import evidence.gameworld.Room;
import evidence.gameworld.Room.Name;
import evidence.gameworld.Wall.Direction;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;
import evidence.gameworld.items.MovableItem;

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
		Item item = createContainer();
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
		door.getAction(door.getActions().get(2)).apply(door, player);
		assertEquals(kitchen, player.getCurrentRoom());
	}
	
	@Test
	public void pickUpAction(){
		Room room = new Room(Name.BATHROOM);
		MovableItem hammer = createMItem(room);
		Player player = createPlayer(room);
		assertEquals("Pick up", hammer.getActions().get(0).toString());
		hammer.getAction(hammer.getActions().get(0)).apply(hammer, player);
		assertEquals(1, player.getInventory().size());
		assertEquals("Hammer", player.getInventory().get(0).toString());
	}
	
	@Test
	public void putItemInContainer(){
		Room room = new Room(Name.BATHROOM);
		Player player = createPlayer(room);
		Container container = createContainer();
		String s = container.putItem(createMItem(room), player);
		assertEquals("Hammer successfully placed in Cardboard Box", s);
		s = container.putItem(createMItem(room), player);
		assertEquals("Hammer is too big for Cardboard Box, try removing an item", s);
	}
	
	@Test
	public void removeItemFromContainer(){
		Room room = new Room(Name.BATHROOM);
		Player player = createPlayer(room);
		Container container = createContainer();
		MovableItem item = createMItem(room);
		String s = container.putItem(item, player);
		assertEquals("Hammer successfully placed in Cardboard Box", s);
		s = container.getItem(item, player);
		assertEquals("Hammer was successfully removed from Cardboard Box. It has been added to your inventory", s);
		s = container.getItem(item, player);
		assertEquals("Hammer not inside Cardboard Box", s);
	}
	
	
	@Test
	public void lockDoor(){
		Door door = createDoor(null, null);
		String s = door.lock(createKey(123));
		assertEquals("Door is locked", s);
		s = door.lock(createKey(23));
		assertEquals("Incorrect key. Door remains unlocked", s);
		
	}
	
	@Test
	public void unlockDoor(){
		Door door = createDoor(null, null);
		String s = door.unlock(createKey(123));
		assertEquals("Door is unlocked", s);
		s = door.unlock(createKey(23));
		assertEquals("Incorrect key. Door remains locked", s);
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

	private Key createKey(int code) {
		List<String> actions = new ArrayList<String>();
		actions.add("Unlock");
		List<String> images = new ArrayList<String>();
		images.add("key.png");
		Key item = new Key("Key", "Key", actions, images, 2, code);
		return item;
	}

	public Container createContainer() {
		List<String> actions = new ArrayList<String>();
		actions.add("Unlock");
		List<String> images = new ArrayList<String>();
		images.add("cbbox.png");
		Container item = new Container("Cardboard Box", "A cardboard box", actions, images, 2);
		return item;
	}
	
	public Door createDoor(Room door1, Room door2) {
		List<String> actions = new ArrayList<String>();
		actions.add("Unlock");
		actions.add("Lock");
		actions.add("Enter");
		List<String> images = new ArrayList<String>();
		images.add("door.png");
		Door door = new Door("Cardbord Box", "A cardboard box", actions, images, door1, door2, false, 123);
		return door;
	}

	public Player createPlayer(Room r) {
		Player player = new Player();
		player.setDirection(Direction.NORTH);
		player.setRoom(r);
		return player;
	}
	
	private MovableItem createMItem(Room door) {
		List<String> actions = new ArrayList<String>();
		actions.add("PickUp");
		List<String> images = new ArrayList<String>();
		images.add("hammer.png");
		images.add("bhammer.png");
		MovableItem item = new MovableItem("Hammer", "A Hammer", actions, images, 2);
		return item;
	}
}
