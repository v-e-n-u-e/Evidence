package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Room.Name;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Furniture;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;
import evidence.gameworld.items.MovableItem;
import evidence.gameworld.items.Weapon;
//import sun.lwawt.macosx.CCheckboxMenuItem;
//import sun.security.krb5.internal.ccache.CCacheInputStream;

@XmlRootElement
public class Game {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();
public Game(){
	
}
	/**
	 * Reads in the state of a game from a xml file
	 */
	public void setup() {
		setupRooms();
		ArrayList<Door> doors = setupDoors();
		setupBathroom(doors);
		setupBedroom(doors);
		setupKitchen(doors);
		setupGarage(doors);
		setupLounge(doors);
		setupOffice(doors);
	}

	private void setupRooms(){
		rooms.add( new Room(Name.BATHROOM, "img/bathroom.png", "img/bathroom.png", "img/bathroom.png", "img/bathroom.png"));
		rooms.add( new Room(Name.BEDROOM, "img/bedroom.png", "img/bedroom.png", "img/bedroom.png", "img/bedroom.png"));
		rooms.add( new Room(Name.KITCHEN, "img/kitchen.png", "img/kitchen.png", "img/kitchen.png", "img/kitchen.png"));
		rooms.add( new Room(Name.GARAGE, "img/garage.png", "img/garage.png", "img/garage.png", "img/garage.png"));
		rooms.add( new Room(Name.LOUNGE, "img/lounge.png", "img/lounge.png", "img/lounge.png", "img/lounge.png"));
		rooms.add( new Room(Name.OFFICE, "img/office.png", "img/office.png", "img/office.png", "img/office.png"));
	}
	
	private ArrayList<Door> setupDoors() {
		ArrayList<Door> doors = new ArrayList<Door>();
		List<String> actions = new ArrayList<String>();
		
		actions.add("Unlock");
		Door door = new Door("Door", "Door between the lounge and the bedroom", actions, getRoom(Name.LOUNGE), getRoom(Name.BEDROOM), true, 1);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		actions.clear();
		actions.add("Enter");
		actions.add("Lock");
		door = new Door("Door", "Door between the lounge and the kitchen", actions, getRoom(Name.LOUNGE), getRoom(Name.KITCHEN), false, 2);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		actions.clear();
		actions.add("Enter");
		actions.add("Lock");
		door = new Door("Door", "Door between the bathroom and the bedroom", actions, getRoom(Name.BATHROOM),	getRoom(Name.BEDROOM), false, 3);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		actions.clear();
		actions.add("Enter");
		actions.add("Lock");
		door = new Door("Door", "Door between the office and the bedroom", actions, getRoom(Name.OFFICE),	getRoom(Name.BEDROOM), false, 4);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(260);
		door.setYPos(230);
		doors.add(door);
		
		actions.clear();
		actions.add("Enter");
		actions.add("Lock");
		door = new Door("Door", "Door between the office and the kitchen", actions, getRoom(Name.OFFICE),	getRoom(Name.KITCHEN), false, 5);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(460);
		door.setYPos(230);
		doors.add(door);
		
		actions.clear();
		actions.add("Unlock");
		door = new Door("Door", "Door between the garage and the kitchen", actions, getRoom(Name.GARAGE),	getRoom(Name.KITCHEN), true, 6);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		return doors;
	}

	private void setupBathroom(ArrayList<Door> doors){
		List<String> actions = new ArrayList<String>();
		
		// Bathroom North Wall

		actions.clear();
		actions.add("Pickup");
		MovableItem mop = new MovableItem("Mop", "A Mop", actions, 2);
		mop.setCurrentImage("img/mop.png");
		mop.setXPos(160);
		mop.setYPos(490);
		getRoom(Name.BATHROOM).getWalls()[0].addItem(mop);

		Container bucket = new Container("Bucket", "A bucket", actions, false, 2);
		bucket.setCurrentImage("img/bucket.png");
		bucket.setXPos(180);
		bucket.setYPos(510);
		getRoom(Name.BATHROOM).getWalls()[0].addItem(bucket);

		// Bathroom East Wall
		Container bath = new Container("Bath", "A bath tub", actions, false, 6);
		bath.setCurrentImage("img/bath.png");
		bath.setXPos(300);
		bath.setYPos(510);
		getRoom(Name.BATHROOM).getWalls()[1].addItem(bath);

		// Bathroom South Wall
		Container toilet = new Container("Toilet", "A toilet", actions, false, 2);
		toilet.setCurrentImage("img/toilet.png");
		toilet.setXPos(450);
		toilet.setYPos(500);
		getRoom(Name.BATHROOM).getWalls()[2].addItem(toilet);

		Container trashCan = new Container("Trash Can", "A trash can", actions, false, 5);
		trashCan.setCurrentImage("img/trashcan.png");
		trashCan.setXPos(200);
		trashCan.setYPos(500);
		getRoom(Name.BATHROOM).getWalls()[2].addItem(trashCan);

		// Bathroom West Wall
		getRoom(Name.BATHROOM).getWalls()[3].addItem(doors.get(2));

		Container sink = new Container("Sink", "A sink", actions, false, 1);
		sink.setCurrentImage("img/sink.png");
		sink.setXPos(450);
		sink.setYPos(380);
		getRoom(Name.BATHROOM).getWalls()[3].addItem(sink);
	}

	private void setupBedroom(ArrayList<Door> doors) {
		List<String> actions = new ArrayList<String>();
		
		// Bedroom North Wall
		Container bed = new Container("Bed", "A bed", actions, false, 6);
		bed.setCurrentImage("img/bed.png");
		bed.setXPos(0);
		bed.setYPos(0);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(bed);

		Key key = new Key("Key", "Key to unlock the safe", actions, 1, 555);
		key.setCurrentImage("img/key.png");
		key.setXPos(300);
		key.setYPos(400);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(key);
		
		Furniture bedsideTable = new Furniture("Bedside Table", "A bedside table", actions);
		bedsideTable.setCurrentImage("img/bedsidetabel.png");
		bedsideTable.setXPos(0);
		bedsideTable.setYPos(0);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(bedsideTable);

		// Bedroom East Wall
		Container cupboard = new Container("Cupboard", "A Cupboard", actions, true, 7);
		cupboard.setCurrentImage("img/cupboard.png");
		cupboard.setXPos(0);
		cupboard.setYPos(0);
		getRoom(Name.BEDROOM).getWalls()[1].addItem(cupboard);

		
		getRoom(Name.BEDROOM).getWalls()[1].addItem(doors.get(2));

		// Bedroom South Wall
		getRoom(Name.BEDROOM).getWalls()[2].addItem(doors.get(3));

		// Bedroom West Wall
		getRoom(Name.BEDROOM).getWalls()[3].addItem(doors.get(0));
	}

	private void setupKitchen(ArrayList<Door> doors) {
		List<String> actions = new ArrayList<String>();

		// Kitchen North Wall
		getRoom(Name.KITCHEN).getWalls()[0].addItem(doors.get(1));

		Furniture stool = new Furniture("Stool", "A stool", actions);
		stool.setCurrentImage("img/stool.png");
		stool.setXPos(0);
		stool.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[0].addItem(stool);

		Furniture stoolOne = new Furniture("Stool", "A stool", actions);
		stoolOne.setCurrentImage("img/stool.png");
		stoolOne.setXPos(0);
		stoolOne.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[0].addItem(stoolOne);

		// Kitchen East Wall
		getRoom(Name.KITCHEN).getWalls()[1].addItem(doors.get(4));

		Container fridge = new Container("Fridge", "A Fridge", actions, false, 5);
		fridge.setCurrentImage("img/bigfridge.png");
		fridge.setXPos(0);
		fridge.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[1].addItem(fridge);

		// Kitchen South Wall
		Container oven = new Container("Oven", "An oven", actions, false, 3);
		fridge.setCurrentImage("img/oven.png");
		fridge.setXPos(0);
		fridge.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(oven);

		MovableItem gloves = new MovableItem("Gloves", "Rubber gloves", actions, 1);
		gloves.setCurrentImage("img/gloves.png");
		gloves.setXPos(0);
		gloves.setYPos(0);

		MovableItem bleach = new MovableItem("Bleach", "Kitchen Bleach", actions, 2);
		gloves.setCurrentImage("img/bleach.png");
		gloves.setXPos(0);
		gloves.setYPos(0);

		Container cabnet = new Container("Cabnet", "A cabnet", actions, false, 3);
		fridge.setCurrentImage("img/oven.png");
		fridge.setXPos(0);
		fridge.setYPos(0);
		cabnet.getContainedItems().add(gloves);
		cabnet.getContainedItems().add(bleach);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(oven);

		Weapon scissors = new Weapon("Scissors", "A pair of Kitchen scissors", actions, 1);
		scissors.setCurrentImage("img/scissors.png");
		scissors.setXPos(0);
		scissors.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(scissors);

		// Kitchen West Wall
		getRoom(Name.KITCHEN).getWalls()[3].addItem(doors.get(5));

		Furniture table = new Furniture("Table", "Dining Table", actions);
		table.setCurrentImage("img/table2.png");
		table.setXPos(0);
		table.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[3].addItem(table);
	}
	
	private void setupGarage(ArrayList<Door> doors) {
		List<String> actions = new ArrayList<String>();

		// Garage North Wall

		// Garage East Wall
		getRoom(Name.GARAGE).getWalls()[1].addItem(doors.get(5));

		// Garage South Wall
		Weapon axe = new Weapon("Axe", "An Axe", actions, 3);
		axe.setCurrentImage("img/axe.png");
		axe.setXPos(0);
		axe.setYPos(0);
		getRoom(Name.GARAGE).getWalls()[2].addItem(axe);

		Weapon sledgeHammer = new Weapon("Sledge Hammer", "A Sledge Hammer", actions, 3);
		sledgeHammer.setCurrentImage("img/axe.png");
		sledgeHammer.setXPos(0);
		sledgeHammer.setYPos(0);
		getRoom(Name.GARAGE).getWalls()[2].addItem(sledgeHammer);

		Container cbBox = new Container("CardboardBox", "A cardboard box", actions, false, 4);
		cbBox.setCurrentImage("ing/cbbox.png");
		cbBox.setXPos(0);
		cbBox.setYPos(0);
		getRoom(Name.GARAGE).getWalls()[2].addItem(cbBox);

		// Garage West Wall
		Furniture bench = new Furniture("Bench", "Work bench", actions);
		bench.setCurrentImage("img/bencg.png");
		bench.setXPos(0);
		bench.setYPos(0);
		getRoom(Name.GARAGE).getWalls()[3].addItem(bench);

		Weapon saw = new Weapon("Saw", "A saw", actions, 3);
		saw.setCurrentImage("img/saw.png");
		saw.setXPos(0);
		saw.setYPos(0);
		getRoom(Name.GARAGE).getWalls()[3].addItem(saw);

		MovableItem wrench = new MovableItem("Wrench", "A wrench", actions, 1);
		wrench.setCurrentImage("img/wrech.png");
		wrench.setXPos(0);
		wrench.setYPos(0);

		MovableItem hammer = new MovableItem("Hammer", "A Hammer", actions, 1);
		hammer.setCurrentImage("img/hammer.png");
		hammer.setXPos(0);
		hammer.setYPos(0);

		MovableItem screwdriver = new MovableItem("Screw Driver", "A screw driver", actions, 1);
		screwdriver.setCurrentImage("img/screwdriver.png");
		screwdriver.setXPos(0);
		screwdriver.setYPos(0);

		Container toolbox = new Container("Tool Box", "A tool Box", actions, true, 7);
		toolbox.setCurrentImage("img/toolbox.png");
		toolbox.setXPos(0);
		toolbox.setYPos(0);
		toolbox.getContainedItems().add(screwdriver);
		toolbox.getContainedItems().add(wrench);
		toolbox.getContainedItems().add(hammer);
		getRoom(Name.GARAGE).getWalls()[3].addItem(toolbox);
	}
	
	private void setupLounge(ArrayList<Door> doors) {
		List<String> actions = new ArrayList<String>();

		// Lounge North Wall
		Furniture fireplace = new Furniture("Fireplace", "A Fireplace", actions);
		fireplace.setCurrentImage("img/fireplace.png");
		fireplace.setXPos(0);
		fireplace.setYPos(0);
		getRoom(Name.LOUNGE).getWalls()[0].addItem(fireplace);

		// Lounge East Wall

		getRoom(Name.LOUNGE).getWalls()[1].addItem(doors.get(0));

		Furniture tv = new Furniture("TV", "A Television", actions);
		tv.setCurrentImage("img/tv.png");
		tv.setXPos(0);
		tv.setYPos(0);
		getRoom(Name.LOUNGE).getWalls()[1].addItem(tv);

		// Lounge South Wall
		getRoom(Name.LOUNGE).getWalls()[2].addItem(doors.get(1));

		Furniture stool = new Furniture("Stool", "A stool", actions);
		stool.setCurrentImage("img/stool.png");
		stool.setXPos(0);
		stool.setYPos(0);
		getRoom(Name.LOUNGE).getWalls()[2].addItem(stool);

		Furniture stoolOne = new Furniture("Stool", "A stool", actions);
		stoolOne.setCurrentImage("img/stool.png");
		stoolOne.setXPos(0);
		stoolOne.setYPos(0);
		getRoom(Name.LOUNGE).getWalls()[2].addItem(stool);

		// Lounge West Wall
		Furniture sofa = new Furniture("Sofa", "A sofa", actions);
		sofa.setCurrentImage("img/sofa.png");
		sofa.setXPos(0);
		sofa.setYPos(0);
		getRoom(Name.LOUNGE).getWalls()[3].addItem(sofa);

		Furniture coffeeTable = new Furniture("Coffee Table", "A coffee table", actions);
		coffeeTable.setCurrentImage("img/table.png");
		coffeeTable.setXPos(0);
		coffeeTable.setYPos(0);
		getRoom(Name.LOUNGE).getWalls()[3].addItem(coffeeTable);
	}
	
	private void setupOffice(ArrayList<Door> doors) {
		List<String> actions = new ArrayList<String>();
		
		// Office North Wall
		getRoom(Name.OFFICE).getWalls()[0].addItem(doors.get(3));

		Furniture potplant = new Furniture("Pot Plant", "A pot plant", actions);
		potplant.setCurrentImage("img/potplant.png");
		potplant.setXPos(300);
		potplant.setYPos(500);
		getRoom(Name.OFFICE).getWalls()[0].addItem(potplant);

		// Office East Wall
		Evidence body = new Evidence("Body", "Victim's Body", actions, 20);
		body.setCurrentImage("img/body.png");
		body.setXPos(100);
		body.setYPos(400);
		getRoom(Name.OFFICE).getWalls()[1].addItem(body);

		Weapon knife = new Weapon("Knife", "A kitchen kinfe", actions, 2);
		knife.setCurrentImage("img/bknife.png");
		knife.setXPos(300);
		knife.setYPos(400);
		getRoom(Name.OFFICE).getWalls()[1].addItem(knife);

		// Office South Wall
		Evidence camera = new Evidence("Camera", "A security camera", actions, 20);
		camera.setCurrentImage("img/cameraon.png");
		camera.setXPos(550);
		camera.setYPos(30);
		getRoom(Name.OFFICE).getWalls()[2].addItem(camera);

		Container safe = new Container("Safe", "A safe", actions, true, 6);
		safe.setCurrentImage("img/safe.png");
		safe.setXPos(300);
		safe.setYPos(500);
		getRoom(Name.OFFICE).getWalls()[2].addItem(safe);

		Furniture computer = new Furniture("Computer", "A computer", actions);
		computer.setCurrentImage("img/computer.png");
		computer.setXPos(100);
		computer.setYPos(420);
		getRoom(Name.OFFICE).getWalls()[2].addItem(computer);

		// Office West Wall
		getRoom(Name.OFFICE).getWalls()[3].addItem(doors.get(4));

		Furniture painting = new Furniture("Painting", "A painting", actions);
		painting.setCurrentImage("img/painting.png");
		painting.setXPos(200);
		painting.setYPos(200);
		getRoom(Name.OFFICE).getWalls()[3].addItem(painting);
	}

	
	
	/**
	 * Gets a room based on the given name
	 * @param name - name of the room you are looking for
	 * @return
	 */
	public Room getRoom(Name name) {
		Room room = null;
		for(Room r : rooms){
			if(r.getName().equals(name)){
				room = r;
				System.out.println("Room not null");
			}
		}
		System.out.println("here");
		return room;
	}
	
	@XmlElement
	public List<Player> getPlayers() {
		return this.players;
	}

	@XmlElement
	public List<Room> getRoom() {
		return this.rooms;
	}

	public void setRooms(List<Room> r) {
		this.rooms = r;
	}

	public void setPlayers(List<Player> p) {
		this.players = p;
	}

	public void addPlayer(Player p) {
		p.setRoom(rooms.get(0));
		this.players.add(p);
	}

	public Player getPlayerWithID(Integer ID) {
		for (Player p : players) {
			if (p.getID().equals(ID)) {
				return p;
			}
		}
		return null; // Only happens if a player disconnects
	}

	/**
	 * Rotate the players view left
	 *
	 * @param player
	 *            - the player who is rotating left
	 * @return string - updated state
	 */
	public String rotateLeft(Player player) {
		return player.rotateView("L");
	}

	/**
	 * Rotate the players view right
	 *
	 * @param player
	 *            - the player who is rotating right
	 * @return string - updated state
	 */
	public String rotateRight(Player player) {
		return player.rotateView("R");
	}

	/**
	 * gets a string list of actions for the provided item
	 *
	 * @param item
	 * @return
	 */
	public List<String> getActions(Item item) {
		return item.getActions();
	}

	/**
	 * Method to apply an action on an item by a player
	 *
	 * @param item
	 *            - the item that is being acted on
	 * @param player
	 *            - the player that is doing the action
	 * @param action
	 *            - the action that is being performed
	 * @return - a string with updated state
	 */
	public String apply(Item gameItem, MovableItem inventoryItem, Player player, String action) {
		String feedback = "";
		if (gameItem != null) {
			for (Item i : player.getWall().getItems()) {
				if (gameItem.toString().equals(i.toString())) {
					gameItem = i;
				}
			}
		}
		if (inventoryItem != null) {
			for (Item i : player.getWall().getItems()) {
				if (inventoryItem.toString().equals(i.toString())) {
					inventoryItem = (MovableItem) i;
				}
			}
		}
		feedback = gameItem.getAction(action).apply(gameItem, inventoryItem, player);

		return feedback;
	}

	/**
	 * Looks through all the rooms for evidence
	 *
	 * @return
	 */
	public int calculateScore() {
		int score = 0;
		for (Room room : rooms) {
			for (Wall wall : room.getWalls()) {
				for (Item item : wall.getItems()) {
					if (item instanceof Evidence) {
						Evidence evidence = (Evidence) item;
						score += evidence.getValue();
					}
				}
			}
		}
		return score;
	}
}
