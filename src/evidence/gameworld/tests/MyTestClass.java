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
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;
import evidence.gameworld.items.MovableItem;

public class MyTestClass {

	@Test
	public void createNewRoom() {
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		assertEquals("BATHROOM", room.toString());
		assertEquals(Direction.NORTH, room.getWalls()[0].getDirection());
		assertEquals(Direction.EAST, room.getWalls()[1].getDirection());
		assertEquals(Direction.SOUTH, room.getWalls()[2].getDirection());
		assertEquals(Direction.WEST, room.getWalls()[3].getDirection());
	}

	@Test
	public void player() {
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
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
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
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
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
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
		Room bathroom = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Room kitchen = new Room(Name.KITCHEN, "kitchen.png", "kitchen.png", "kitchen.png", "kitchen.png");
		Door door = createDoor(bathroom, kitchen, false);
		Player player = createPlayer(bathroom);
		assertEquals(bathroom, player.getCurrentRoom());
		assertEquals("Enter", door.getActions().get(2).toString());
		door.getAction(door.getActions().get(2)).apply(door, null, player);
		assertEquals(kitchen, player.getCurrentRoom());
		
		door.getAction(door.getActions().get(2)).apply(door, null, player);
		assertEquals(bathroom, player.getCurrentRoom());
	}
	
	@Test
	public void pickUpAction(){
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		MovableItem hammer = createMItem();
		Player player = createPlayer(room);
		assertEquals("PickUp", hammer.getActions().get(0));
		hammer.getAction(hammer.getActions().get(0)).apply(hammer, null, player);
		assertEquals(1, player.getInventory().size());
		assertEquals("Hammer", player.getInventory().get(0).toString());
		
		String s = hammer.getAction(hammer.getActions().get(0)).apply(createContainer(), null, player);
		assertEquals("Cannot perform Pick up on Cardboard Box", s);
	}
	
	@Test
	public void putItemInContainer(){
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		Container container = createContainer();
		String s = container.putItem(createMItem(), player);
		assertEquals("Hammer successfully placed in Cardboard Box", s);
		s = container.putItem(createMItem(), player);
		assertEquals("Hammer is too big for Cardboard Box, try removing an item", s);
	}
	
	@Test
	public void removeItemFromContainer(){
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		Container container = createContainer();
		MovableItem item = createMItem();
		String s = container.putItem(item, player);
		assertEquals("Hammer successfully placed in Cardboard Box", s);
		s = container.getItem(item, player);
		assertEquals("Hammer was successfully removed from Cardboard Box. It has been added to your inventory", s);
		s = container.getItem(item, player);
		assertEquals("Hammer not inside Cardboard Box", s);
	}
	
	
	@Test
	public void lockDoor(){
		Door door = createDoor(null, null, false);
		String s = door.lock(createKey(123));
		assertEquals("Door is locked", s);
		door = createDoor(null, null, false);
		s = door.lock(createKey(23));
		assertEquals("Incorrect key. Door remains unlocked", s);
		assertEquals(false, door.getLocked());
	}
	
	@Test
	public void lockAction(){
		Door door = createDoor(null, null, false);
		Player player = createPlayer(null);
		assertEquals("Lock", door.getActions().get(1));
		door.getAction(door.getActions().get(1)).apply(door, createKey(123), player);
		assertEquals(true, door.getLocked());
		door = createDoor(null, null, false);
		String s = door.getAction(door.getActions().get(1)).apply(door, createMItem(), player);
		assertEquals("Cannot perform Lock using Hammer", s);
		s = door.getAction(door.getActions().get(1)).apply(createMItem(), createKey(123), player);
		assertEquals("Cannot perform Lock on Hammer", s);
		assertEquals(false, door.getLocked());
	}
	
	@Test
	public void unlockDoor(){
		Door door = createDoor(null, null, true);
		String s = door.unlock(createKey(123));
		assertEquals("Door is unlocked", s);
		s = door.unlock(createKey(23));
		assertEquals("Incorrect key. Door remains locked", s);
	}
	
	@Test
	public void unlockAction(){
		Door door = createDoor(null, null, true);
		Player player = createPlayer(null);
		assertEquals("Unlock", door.getActions().get(0));
		door.getAction(door.getActions().get(0)).apply(door, createKey(123), player);
		assertEquals(false, door.getLocked());
		String s = door.getAction(door.getActions().get(0)).apply(door, createMItem(), player);
		assertEquals("Cannot perform Unlock using Hammer", s);
		s = door.getAction(door.getActions().get(0)).apply(createMItem(), createKey(123), player);
		assertEquals("Cannot perform Unlock on Hammer", s);
	}
	
	@Test
	public void cutUpAction(){
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("CutUp");
		Item body = new Evidence("Body", "The body", actions, 10);
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		MovableItem weapon = new MovableItem(null, null, null, 0);
		body.getAction(body.getActions().get(0)).apply(body, weapon, player);
		assertEquals(false, player.getWall().getItems().contains(body));
		Item blood = new MovableItem("Blood", null, actions, 10);
		String s = body.getAction(body.getActions().get(0)).apply(blood, weapon, player);
		assertEquals("Cannot perform Cut Up on Blood", s);
		 s = body.getAction(body.getActions().get(0)).apply(body, (MovableItem)blood, player);
		assertEquals("Cannot perform Cut Up using Blood", s);
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
		Key item = new Key("Key", "Key", actions, 2, code);
		return item;
	}

	public Container createContainer() {
		List<String> actions = new ArrayList<String>();
		actions.add("Unlock");
		Container item = new Container("Cardboard Box", "A cardboard box", actions, true, 2);
		return item;
	}
	
	public Door createDoor(Room room1, Room room2, boolean locked) {
		List<String> actions = new ArrayList<String>();
		actions.add("Unlock");
		actions.add("Lock");
		actions.add("Enter");
		Door door = new Door("Cardbord Box", "A cardboard box", actions, room1, room2, locked, 123);
		return door;
	}

	public Player createPlayer(Room r) {
		Player player = new Player();
		player.setDirection(Direction.NORTH);
		player.setRoom(r);
		return player;
	}
	
	private MovableItem createMItem() {
		List<String> actions = new ArrayList<String>();
		actions.add("PickUp");
		MovableItem item = new MovableItem("Hammer", "A Hammer", actions, 2);
		return item;
	}
}
