package evidence.gameworld.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import evidence.gameworld.Player;
import evidence.gameworld.Room;
import evidence.gameworld.Room.Name;
import evidence.gameworld.Wall.Direction;
import evidence.gameworld.actions.CutUp;
import evidence.gameworld.actions.Drop;
import evidence.gameworld.actions.Enter;
import evidence.gameworld.actions.Fill;
import evidence.gameworld.actions.Flush;
import evidence.gameworld.actions.Inspect;
import evidence.gameworld.actions.Kick;
import evidence.gameworld.actions.Lock;
import evidence.gameworld.actions.PickUp;
import evidence.gameworld.actions.PlaceItem;
import evidence.gameworld.actions.RemoveItem;
import evidence.gameworld.actions.Unlock;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Furniture;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;
import evidence.gameworld.items.MovableItem;

public class GameLogicTestClass {

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
		Door door = createDoor(kitchen, false);
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
		assertEquals("Pick up", hammer.getActions().get(0));
		hammer.getAction(hammer.getActions().get(0)).apply(hammer, null, player);
		assertEquals(1, player.getInventory().size());
		assertEquals("Hammer", player.getInventory().get(0).toString());
		hammer.getAction(hammer.getActions().get(0)).apply(hammer, null, player);
		String s = hammer.getAction(hammer.getActions().get(0)).apply(createContainer(), null, player);
		assertEquals("Cannot perform Pick up on Cardboard Box", s);
	}
	
	@Test
	public void pickUpTooManyItems(){
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		MovableItem hammer = createMItem();
		Player player = createPlayer(room);
		player.addItem(hammer);
		assertEquals(1, player.getInventory().size());
		player.addItem(hammer);
		assertEquals(2, player.getInventory().size());
		player.addItem(hammer);
		assertEquals(3, player.getInventory().size());
		player.addItem(hammer);
		assertEquals(4, player.getInventory().size());
		player.addItem(hammer);
		assertEquals(5, player.getInventory().size());
		player.addItem(hammer);
		assertEquals(6, player.getInventory().size());
		player.addItem(hammer);
		assertEquals(7, player.getInventory().size());
		assertEquals("Hammer has been added to your inventory",hammer.getAction(hammer.getActions().get(0)).apply(hammer, null, player));
		assertEquals(8, player.getInventory().size());
		assertEquals("Your inventory is full. You can't pick this item up.",hammer.getAction(hammer.getActions().get(0)).apply(hammer, null, player));
	}
	
	@Test
	public void putItemInContainer(){
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		Container container = createContainer();
		String s = container.putItem(createMItem(), player);
		assertEquals("Hammer successfully placed in Cardboard Box", s);
		s = container.putItem(createMItem(), player);
		assertEquals("There is not enough room in Cardboard Box for Hammer", s);
	}
	
	@Test
	public void removeItemFromContainer(){
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		Container container = createContainer();
		MovableItem item = createMItem();
		String s = container.putItem(item, player);
		assertEquals("Hammer successfully placed in Cardboard Box", s);
		s = container.removeItem(item, player);
		assertEquals("Hammer was successfully removed from Cardboard Box. It has been added to your inventory", s);
		s = container.removeItem(item, player);
		assertEquals("Hammer not inside Cardboard Box", s);
	}
	
	@Test
	public void lockDoor(){
		Door door = createDoor(null, false);
		String s = door.lock(createKey(123));
		assertEquals("Door is locked", s);
		door = createDoor(null, false);
		s = door.lock(createKey(23));
		assertEquals("Incorrect key. Door remains unlocked", s);
		assertEquals(false, door.getLocked());
	}
	
	@Test
	public void lockAction(){
		Door door = createDoor(null, false);
		Player player = createPlayer(null);
		assertEquals("Lock", door.getActions().get(1));
		door.getAction(door.getActions().get(1)).apply(door, createKey(123), player);
		assertEquals(true, door.getLocked());
		String s = new Lock().apply(door, createKey(23), player);
		assertEquals("Incorrect key.", s);
		door = createDoor(null, false);
		s = door.getAction(door.getActions().get(1)).apply(door, createMItem(), player);
		assertEquals("Cannot perform Lock using Hammer", s);
		s = door.getAction(door.getActions().get(1)).apply(createMItem(), createKey(123), player);
		assertEquals("Cannot perform Lock on Hammer", s);
		assertEquals(false, door.getLocked());
	}
	
	@Test
	public void unlockDoor(){
		Door door = createDoor(null, true);
		String s = door.unlock(createKey(123));
		assertEquals("Door is unlocked", s);
		s = door.unlock(createKey(23));
		assertEquals("Incorrect key. Door remains locked", s);
	}
	
	@Test
	public void unlockAction(){
		Door door = createDoor(null, true);
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
	public void unlockSafeAction(){
		Container safe = new Container("Safe", null, new ArrayList<String>(), true, 5, false);
		Player player = createPlayer(null);
		new Unlock().apply(safe, createKey(5), player);
		assertEquals(true, safe.getLocked());
		new Unlock().apply(safe, createKey(555), player);
		assertEquals(false, safe.getLocked());
		assertEquals(false, safe.getActions().contains(new Unlock().toString()));
		assertEquals(true, safe.getActions().contains(new Lock().toString()));

		assertEquals(true, safe.getActions().contains(new PlaceItem().toString()));
		String s = new Unlock().apply(safe, createMItem(), player);
		assertEquals("Cannot perform Unlock using Hammer", s);
		s = new Unlock().apply(createMItem(), createKey(123), player);
		assertEquals("Cannot perform Unlock on Hammer", s);
	}
	
	@Test
	public void flushAction(){
		Container toilet = new Container("Toilet", null, new ArrayList<>(Arrays.asList( "flush")), false, 2, false);
		MovableItem item = new MovableItem("Hammer", null, null, 2, false);
		toilet.putItem(item, new Player());
		new Flush().apply(toilet, item, null);
		assertEquals(0, toilet.getContainedItems().size());
		assertEquals(2, toilet.getCapacity());
	}
	
	@Test
	public void nullParameters(){
		Room r = new Room(Name.BATHROOM, null, null, null, null);
		Player player = createPlayer(r);
		MovableItem item = createMItem();
		new CutUp().apply(null, null, player);
		new CutUp().apply(null, item, player);
		new CutUp().apply(item, null, player);
		
		new Drop().apply(null, null, player);
		new Drop().apply(null, item, player);
		new Drop().apply(item, null, player);
		
		new Enter().apply(null, null, player);
		new Enter().apply(null, item, player);
		new Enter().apply(item, null, player);

		new Fill().apply(null, null, player);
		new Fill().apply(null, item, player);
		new Fill().apply(item, null, player);
		
		new Flush().apply(null, null, player);
		new Flush().apply(null, item, player);
		new Flush().apply(item, null, player);

		new Inspect().apply(null, null, player);
		new Inspect().apply(null, item, player);
		new Inspect().apply(item, null, player);

		new Kick().apply(null, null, player);
		new Kick().apply(null, item, player);
		new Kick().apply(item, null, player);

		new Lock().apply(null, null, player);
		new Lock().apply(null, item, player);
		new Lock().apply(item, null, player);

		new PickUp().apply(null, null, player);
		new PickUp().apply(null, item, player);
		new PickUp().apply(item, null, player);

		new PlaceItem().apply(null, null, player);
		new PlaceItem().apply(null, item, player);
		new PlaceItem().apply(item, null, player);

		new RemoveItem(item.toString()).apply(null, null, player);
		new RemoveItem(item.toString()).apply(null, item, player);
		new RemoveItem(item.toString()).apply(item, null, player);

		new Unlock().apply(null, null, player);
		new Unlock().apply(null, item, player);
		new Unlock().apply(item, null, player);
	}
	
	@Test
	public void cutUpAction(){
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("CutUp");
		Item body = new Evidence("Body", "The body", actions, 10, false);
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		MovableItem weapon = new MovableItem("Knife", null, null, 0, false);
		new CutUp().apply(body, weapon, player);
		weapon = new MovableItem("Saw", null, null, 0, false);
		new CutUp().apply(body, weapon, player);
		assertEquals(false, player.getWall().getItems().contains(body));
		Item blood = new MovableItem("Blood", null, actions, 10, false);
		String s = body.getAction(body.getActions().get(0)).apply(blood, weapon, player);
		assertEquals("Cannot perform Cut Up on Blood", s);
		 s = body.getAction(body.getActions().get(0)).apply(body, (MovableItem)blood, player);
		assertEquals("Cannot perform Cut Up using Blood", s);
	}
	
	@Test
	public void placeItemAction(){
		Container trashCan = new Container("Trash Can", null, new ArrayList<>(Arrays.asList("placeitem")), false, 5, false);
		MovableItem item = new MovableItem("Item", null, null, 3, false);
		Player player = new Player();
		player.addItem(item);
		assertEquals(1, player.getInventory().size());
		new PlaceItem().apply(trashCan, item, player);
		assertEquals(1, trashCan.getContainedItems().size());
		assertEquals(2, trashCan.getActions().size());
		assertEquals(0, player.getInventory().size());

		Furniture table = new Furniture("Table", null, new ArrayList<>(Arrays.asList("placeitem")), false);
		
		assertEquals("Cannot perform Place Item on Table", new PlaceItem().apply(table, item, player));
	}
	
	@Test
	public void kickAction(){
		Container trashCan = new Container("Trash Can", null, new ArrayList<String>(), false, 5, false);
		MovableItem item = new MovableItem("Item", null, new ArrayList<String>(), 0, false);
		item.setXPos(0);
		item.setYPos(0);
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		new PlaceItem().apply(trashCan, item, player);
		new Kick().apply(trashCan, null, player);
		assertEquals(0, trashCan.getContainedItems().size());
		assertEquals(false, trashCan.getActions().contains(new Kick().toString()));
		assertEquals(false, trashCan.getActions().contains(new PlaceItem().toString()));
	}
	
	@Test
	public void fillBathAction(){
		Container bath = new Container("Bath", null, new ArrayList<>(Arrays.asList("placeitem", "fill")), false, 5, false);
		MovableItem item = new MovableItem("Item", null, new ArrayList<String>(), 3, false);
		Player player = createPlayer(null);
		new PlaceItem().apply(bath, item, player);
		String s = new Fill().apply(bath, null, player);
		assertEquals("Bath must be empty to fill", s);
		assertEquals(true, bath.getActions().contains(new Fill().toString()));
		assertEquals(true, bath.getActions().contains(new RemoveItem("Item").toString()));
		bath = new Container("Bath", null, new ArrayList<>(Arrays.asList("placeitem", "fill")), false, 5, false);
		assertEquals(true, bath.getActions().contains(new PlaceItem().toString()));
		new Fill().apply(bath, null, player);
		assertEquals(false, bath.getActions().contains(new PlaceItem().toString()));
		assertEquals(false, bath.getActions().contains(new Fill().toString()));
	}
	
	@Test
	public void InspectAction(){
		Furniture item = new Furniture("Name", "Description", null, false);
		String s = new Inspect().apply(item, null, null);
		assertEquals("\n" + item.getDescription(), s);
		Door door = new Door("", "Locked door", null, null, true, 0, false);
		s = new Inspect().apply(door, null, null);
		assertEquals("\n" + door.getDescription() + ". It is locked", s);
		door.setLocked(false);
		s = new Inspect().apply(door, null, null);
		assertEquals("\n" + door.getDescription() + ". It is unlocked", s);
	}
	
	@Test
	public void RemoveItemAction(){
		Container container = new Container("Name", "Description", new ArrayList<>(Arrays.asList("placeitem")), false, 0, false);
		MovableItem item = new MovableItem("", null, new ArrayList<>(Arrays.asList("pickup")), 0, false);
		Room room = new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png");
		Player player = createPlayer(room);
		player.addItem(item);
		assertEquals(1, player.getInventory().size());
		new PlaceItem().apply(container, item, player);
		assertEquals(0, player.getInventory().size());
		assertEquals(true, container.getContainedItems().contains(item));
		assertEquals(true, container.getActions().contains(new RemoveItem(item.getName()).toString()));
		new RemoveItem(item.getName()).apply(container, item, player);
		assertEquals(1, player.getInventory().size());
		assertEquals(false, container.getContainedItems().contains(item));
		assertEquals(false, container.getActions().contains(new RemoveItem(item.getName()).toString()));	
		
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
		Key item = new Key("Key", "Key", actions, 2, code, false);
		return item;
	}

	public Container createContainer() {
		List<String> actions = new ArrayList<String>();
		actions.add("Unlock");
		Container item = new Container("Cardboard Box", "A cardboard box", actions, true, 2, false);
		return item;
	}
	
	public Door createDoor(Room room1, boolean locked) {
		List<String> actions = new ArrayList<String>();
		actions.add("Unlock");
		actions.add("Lock");
		actions.add("Enter");
		Door door = new Door("Cardbord Box", "A cardboard box", actions, room1, locked, 123, false);
		return door;
	}

	public Player createPlayer(Room r) {
		Player player = new Player();
		player.setCurrentDirection(Direction.NORTH);
		player.setCurrentRoom(r);
		return player;
	}
	
	private MovableItem createMItem() {
		List<String> actions = new ArrayList<String>();
		actions.add("PickUp");
		MovableItem item = new MovableItem("Hammer", "A Hammer", actions, 2, false);
		return item;
	}
}
