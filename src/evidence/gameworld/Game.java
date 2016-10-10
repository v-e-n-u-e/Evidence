package evidence.gameworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.datastorage.TestReadXml;
import evidence.gameworld.Room.Name;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Furniture;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.Key;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class Game {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();
	private ArrayList<Door> doors = new ArrayList<Door>();
public Game(){
	
}
	/**
	 * Reads in the state of a game from a xml file
	 * @throws Exception 
	 */
	public void ReadFromXml(String FileName) throws Exception{
		TestReadXml t = new TestReadXml();
		t.ReadInGame(FileName);
		this.players = t.getPlayers();
		this.rooms = t.getRoom();
	}
	
	
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
		doors = new ArrayList<Door>();

		Door door = new Door("Door", "Door between the lounge and the bedroom", new ArrayList<>(Arrays.asList("inspect", "unlock")), getRoom(Name.LOUNGE), getRoom(Name.BEDROOM), true, 1);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		door = new Door("Door", "Door between the lounge and the kitchen", new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.LOUNGE), getRoom(Name.KITCHEN), false, 2);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		door = new Door("Door", "Door between the bathroom and the bedroom", new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.BATHROOM),	getRoom(Name.BEDROOM), false, 3);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		door = new Door("Door", "Door between the office and the bedroom", new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.OFFICE),	getRoom(Name.BEDROOM), false, 4);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(260);
		door.setYPos(230);
		doors.add(door);
		
		door = new Door("Door", "Door between the office and the kitchen", new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.OFFICE),	getRoom(Name.KITCHEN), false, 5);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(460);
		door.setYPos(230);
		doors.add(door);
		
		door = new Door("Door", "Door between the garage and the kitchen", new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.GARAGE),	getRoom(Name.KITCHEN), false, 6);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		doors.add(door);

		return doors;
	}

	private void setupBathroom(ArrayList<Door> doors){
		
		// Bathroom North Wall
		MovableItem mop = new MovableItem("Mop", "A mop. This mop can be used for cleaning up and concealing evidence", new ArrayList<>(Arrays.asList("inspect", "pickup")), 3);
		mop.setCurrentImage("img/mop.png");
		mop.setXPos(160);
		mop.setYPos(490);
		getRoom(Name.BATHROOM).getWalls()[0].addItem(mop);

		Container bucket = new Container("Bucket", "A bucket. Things can be hidden in here", new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 2);
		bucket.setCurrentImage("img/bucket.png");
		bucket.setXPos(180);
		bucket.setYPos(510);
		getRoom(Name.BATHROOM).getWalls()[0].addItem(bucket);

		
		// Bathroom East Wall
		Container bath = new Container("Bath", "A bath tub. Things can be hidden in here", new ArrayList<>(Arrays.asList("inspect", "placeitem", "fill")), false, 6);
		bath.setCurrentImage("img/bath.png");
		bath.setXPos(300);
		bath.setYPos(510);
		getRoom(Name.BATHROOM).getWalls()[1].addItem(bath);

		//South Wall
		Container toilet = new Container("Toilet", "A toilet. This toilet can be flushed", new ArrayList<>(Arrays.asList("inspect", "flush", "placeitem")), false, 2);
		toilet.setCurrentImage("img/toilet.png");
		toilet.setXPos(450);
		toilet.setYPos(500);
		getRoom(Name.BATHROOM).getWalls()[2].addItem(toilet);

		Container trashCan = new Container("Trash Can", "A trash can. You can kick this trash can over if you are feeling frustrated", new ArrayList<>(Arrays.asList("inspect", "kick", "placeitem")), false, 5);
		trashCan.setCurrentImage("img/trashcan.png");
		trashCan.setXPos(200);
		trashCan.setYPos(500);
		getRoom(Name.BATHROOM).getWalls()[2].addItem(trashCan);

		// Bathroom West Wall
		getRoom(Name.BATHROOM).getWalls()[3].addItem(doors.get(2));

		Container sink = new Container("Sink", "A sink. Go ahead and wash your hands here. \nIt just might conceal some evidence", new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 1);
		sink.setCurrentImage("img/sink.png");
		sink.setXPos(450);
		sink.setYPos(380);
		getRoom(Name.BATHROOM).getWalls()[3].addItem(sink);
	}

	private void setupBedroom(ArrayList<Door> doors) {
		
		// Bedroom North Wall
		Container bed = new Container("Bed", "A bed. You may take a nap here, or you could hide something underneath this bed.", new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 6);
		bed.setCurrentImage("img/bed.png");
		bed.setXPos(200);
		bed.setYPos(400);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(bed);

		Key key = new Key("Key", "Key to the safe", new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, 555);
		key.setCurrentImage("img/numpad.png");
		key.setXPos(300);
		key.setYPos(400);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(key);
		
		Furniture bedsideTable = new Furniture("Bedside Table", "A bedside table that doesn't do much", new ArrayList<>(Arrays.asList("inspect")));
		bedsideTable.setCurrentImage("img/bedsidetabel.png");
		bedsideTable.setXPos(400);
		bedsideTable.setYPos(450);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(bedsideTable);

		// Bedroom East Wall
		Container cupboard = new Container("Cupboard", "A Cupboard. This cupboard is quite large and can hide many things", new ArrayList<>(Arrays.asList("inspect", "unlock")), true, 7);
		cupboard.setCurrentImage("img/cupboard.png");
		cupboard.setXPos(200);
		cupboard.setYPos(200);
		getRoom(Name.BEDROOM).getWalls()[1].addItem(cupboard);
		
		getRoom(Name.BEDROOM).getWalls()[1].addItem(doors.get(2));

		// Bedroom South Wall
		getRoom(Name.BEDROOM).getWalls()[2].addItem(doors.get(3));

		// Bedroom West Wall
		getRoom(Name.BEDROOM).getWalls()[3].addItem(doors.get(0));
	}

	private void setupKitchen(ArrayList<Door> doors) {

		// Kitchen North Wall
		getRoom(Name.KITCHEN).getWalls()[0].addItem(doors.get(1));

		Furniture stool = new Furniture("Stool", "A stool. Take a seat and strech your legs", new ArrayList<>(Arrays.asList("inspect")));
		stool.setCurrentImage("img/stool.png");
		stool.setXPos(300);
		stool.setYPos(400);
		getRoom(Name.KITCHEN).getWalls()[0].addItem(stool);

		Furniture stoolOne = new Furniture("Stool", "A stool. Only lift this stool once!", new ArrayList<>(Arrays.asList("inspect")));
		stoolOne.setCurrentImage("img/stool.png");
		stoolOne.setXPos(400);
		stoolOne.setYPos(400);
		getRoom(Name.KITCHEN).getWalls()[0].addItem(stoolOne);

		// Kitchen East Wall
		getRoom(Name.KITCHEN).getWalls()[1].addItem(doors.get(4));

		Container fridge = new Container("Fridge", "A Fridge", new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 5);
		fridge.setCurrentImage("img/bigfridge.png");
		fridge.setXPos(300);
		fridge.setYPos(400);
		getRoom(Name.KITCHEN).getWalls()[1].addItem(fridge);

		// Kitchen South Wall
		Container oven = new Container("Oven", "An oven", new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 3);
		oven.setCurrentImage("img/oven.png");
		oven.setXPos(0);
		oven.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(oven);

		MovableItem gloves = new MovableItem("Gloves", "Rubber gloves", new ArrayList<>(Arrays.asList("inspect", "pickup")), 1);
		gloves.setCurrentImage("img/gloves.png");
		gloves.setXPos(0);
		gloves.setYPos(0);

		MovableItem bleach = new MovableItem("Bleach", "Kitchen Bleach", new ArrayList<>(Arrays.asList("Inspect", "pickup")), 2);
		bleach.setCurrentImage("img/bleach.png");
		bleach.setXPos(0);
		gloves.setYPos(0);

		Container cabnet = new Container("Cabnet", "A cabnet", new ArrayList<>(Arrays.asList("inspect", "placeitem", "remove " + gloves.toString(), "remove " + bleach.toString())), false, 3);
		cabnet.setCurrentImage("img/cabnet.png");
		cabnet.setXPos(0);
		cabnet.setYPos(0);
		cabnet.getContainedItems().add(gloves);
		cabnet.getContainedItems().add(bleach);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(oven);

		MovableItem scissors = new MovableItem("Scissors", "A pair of Kitchen scissors", new ArrayList<>(Arrays.asList("inspect", "pickup")), 1);
		scissors.setCurrentImage("img/scissors.png");
		scissors.setXPos(0);
		scissors.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(scissors);

		// Kitchen West Wall
		getRoom(Name.KITCHEN).getWalls()[3].addItem(doors.get(5));

		Furniture table = new Furniture("Table", "Dining Table", new ArrayList<>(Arrays.asList("inspect")));
		table.setCurrentImage("img/table2.png");
		table.setXPos(400);
		table.setYPos(400);
		getRoom(Name.KITCHEN).getWalls()[3].addItem(table);
	}
	
	private void setupGarage(ArrayList<Door> doors) {

		// Garage North Wall

		// Garage East Wall
		getRoom(Name.GARAGE).getWalls()[1].addItem(doors.get(5));

		// Garage South Wall
		MovableItem axe = new MovableItem("Axe", "An Axe. HINT - clean this to conceal evidence", new ArrayList<>(Arrays.asList("inspect", "pickup")), 3);
		axe.setCurrentImage("img/baxe.png");
		axe.setXPos(200);
		axe.setYPos(200);
		getRoom(Name.GARAGE).getWalls()[2].addItem(axe);

		MovableItem sledgeHammer = new MovableItem("Sledge Hammer", "A Sledge Hammer. Try not to get your finger prints on this. \n HINT gloves are in the kitchen cupboard", new ArrayList<>(Arrays.asList("inspect", "pickup")), 3);
		sledgeHammer.setCurrentImage("img/sledgehammer.png");
		sledgeHammer.setXPos(350);
		sledgeHammer.setYPos(200);
		getRoom(Name.GARAGE).getWalls()[2].addItem(sledgeHammer);

		Container cbBox = new Container("CardboardBox", "A cardboard box. Nice and roomy. Lots of space to hide bloody clothes. \n maybe the police will think its a costume box...", new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 4);
		cbBox.setCurrentImage("img/cbbox.png");
		cbBox.setXPos(300);
		cbBox.setYPos(300);
		getRoom(Name.GARAGE).getWalls()[2].addItem(cbBox);
		
		Evidence clothes = new Evidence("Bloodied Clothing", "Bloody clothes. The police probably wont like seeing this", new ArrayList<>(Arrays.asList("inspect")), 4);
		clothes.setCurrentImage("img/bpants.png");
		clothes.setXPos(400);
		clothes.setYPos(400);
		getRoom(Name.GARAGE).getWalls()[2].addItem(clothes);

		// Garage West Wall
		Furniture bench = new Furniture("Bench", "Work bench", new ArrayList<>(Arrays.asList("inspect")));
		bench.setCurrentImage("img/bencg.png");
		bench.setXPos(200);
		bench.setYPos(450);
		getRoom(Name.GARAGE).getWalls()[3].addItem(bench);

		MovableItem saw = new MovableItem("Saw", "A saw", new ArrayList<>(Arrays.asList("inspect", "pickup")), 3);
		saw.setCurrentImage("img/saw.png");
		saw.setXPos(100);
		saw.setYPos(100);
		getRoom(Name.GARAGE).getWalls()[3].addItem(saw);
		
		MovableItem wrench = new MovableItem("Wrench", "A wrench", new ArrayList<>(Arrays.asList("remove")), 1);
		wrench.setCurrentImage("img/wrech.png");
		wrench.setXPos(0);
		wrench.setYPos(0);

		MovableItem hammer = new MovableItem("Hammer", "A Hammer", new ArrayList<>(Arrays.asList("remove")), 1);
		hammer.setCurrentImage("img/hammer.png");
		hammer.setXPos(0);
		hammer.setYPos(0);

		MovableItem screwdriver = new MovableItem("Screw Driver", "A screw driver", new ArrayList<>(Arrays.asList("remove")), 1);
		screwdriver.setCurrentImage("img/screwdriver.png");
		screwdriver.setXPos(0);
		screwdriver.setYPos(0);

		Container toolbox = new Container("Tool Box", "A tool Box", new ArrayList<>(Arrays.asList("inspect", "placeitem", "remove " + wrench.toString(), "remove " + hammer.toString(), "remove " + screwdriver.toString())), false, 7);
		toolbox.setCurrentImage("img/toolbox.png");
		toolbox.setXPos(300);
		toolbox.setYPos(300);
		toolbox.getContainedItems().add(screwdriver);
		toolbox.getContainedItems().add(wrench);
		toolbox.getContainedItems().add(hammer);
		getRoom(Name.GARAGE).getWalls()[3].addItem(toolbox);

		
	}
	
	private void setupLounge(ArrayList<Door> doors) {

		// Lounge North Wall
		Furniture fireplace = new Furniture("Fireplace", "A Fireplace", new ArrayList<>(Arrays.asList("inspect", "light")));
		fireplace.setCurrentImage("img/fireplace.png");
		fireplace.setXPos(0);
		fireplace.setYPos(0);
		getRoom(Name.LOUNGE).getWalls()[0].addItem(fireplace);

		// Lounge East Wall

		getRoom(Name.LOUNGE).getWalls()[1].addItem(doors.get(0));

		Furniture tv = new Furniture("TV", "A Television", new ArrayList<>(Arrays.asList("inspect")));
		tv.setCurrentImage("img/tv.png");
		tv.setXPos(400);
		tv.setYPos(300);
		getRoom(Name.LOUNGE).getWalls()[1].addItem(tv);

		// Lounge South Wall
		getRoom(Name.LOUNGE).getWalls()[2].addItem(doors.get(1));
		
		Furniture stool = new Furniture("Stool", "A stool", new ArrayList<>(Arrays.asList("inspect")));
		stool.setCurrentImage("img/stool.png");
		stool.setXPos(200);
		stool.setYPos(200);
		getRoom(Name.LOUNGE).getWalls()[2].addItem(stool);

		Furniture stoolOne = new Furniture("Stool", "A stool", new ArrayList<>(Arrays.asList("inspect")));
		stoolOne.setCurrentImage("img/stool.png");
		stoolOne.setXPos(350);
		stoolOne.setYPos(200);
		getRoom(Name.LOUNGE).getWalls()[2].addItem(stool);

		// Lounge West Wall
		Furniture sofa = new Furniture("Sofa", "A sofa", new ArrayList<>(Arrays.asList("inspect")));
		sofa.setCurrentImage("img/sofa.png");
		sofa.setXPos(200);
		sofa.setYPos(300);
		getRoom(Name.LOUNGE).getWalls()[3].addItem(sofa);

		Furniture coffeeTable = new Furniture("Coffee Table", "A coffee table", new ArrayList<>(Arrays.asList("inspect")));
		coffeeTable.setCurrentImage("img/table.png");
		coffeeTable.setXPos(350);
		coffeeTable.setYPos(300);
		getRoom(Name.LOUNGE).getWalls()[3].addItem(coffeeTable);
	}
	
	private void setupOffice(ArrayList<Door> doors) {
		
		// Office North Wall
		getRoom(Name.OFFICE).getWalls()[0].addItem(doors.get(3));

		Furniture potplant = new Furniture("Pot Plant", "A pot plant", new ArrayList<>(Arrays.asList("inspect")));
		potplant.setCurrentImage("img/potplant.png");
		potplant.setXPos(300);
		potplant.setYPos(500);
		getRoom(Name.OFFICE).getWalls()[0].addItem(potplant);

		// Office East Wall
		Evidence body = new Evidence("Body", "Victim's Body", new ArrayList<>(Arrays.asList("inspect", "cutup")), 20);
		body.setCurrentImage("img/body.png");
		body.setXPos(100);
		body.setYPos(400);
		getRoom(Name.OFFICE).getWalls()[1].addItem(body);

		MovableItem knife = new MovableItem("Knife", "A kitchen kinfe", new ArrayList<>(Arrays.asList("inspect", "pickup")), 2);
		knife.setCurrentImage("img/bknife.png");
		knife.setXPos(300);
		knife.setYPos(400);
		getRoom(Name.OFFICE).getWalls()[1].addItem(knife);

		// Office South Wall
		Evidence camera = new Evidence("Camera", "A security camera", new ArrayList<>(Arrays.asList("inspect")), 20);
		camera.setCurrentImage("img/cameraon.png");
		camera.setXPos(550);
		camera.setYPos(30);
		getRoom(Name.OFFICE).getWalls()[2].addItem(camera);

		Container safe = new Container("Safe", "A safe", new ArrayList<>(Arrays.asList("inspect", "unlock")), true, 6);
		safe.setCurrentImage("img/safe.png");
		safe.setXPos(300);
		safe.setYPos(500);
		getRoom(Name.OFFICE).getWalls()[2].addItem(safe);

		
		
		Furniture desk = new Furniture("Desk", "Office desk", new ArrayList<>(Arrays.asList("inspect")));
		desk.setCurrentImage("img/desk.png");
		desk.setXPos(150);
		desk.setYPos(400);
		getRoom(Name.OFFICE).getWalls()[2].addItem(desk);
		
		Furniture computer = new Furniture("Computer", "A computer", new ArrayList<>(Arrays.asList("inspect")));
		computer.setCurrentImage("img/computer.png");
		computer.setXPos(100);
		computer.setYPos(420);
		getRoom(Name.OFFICE).getWalls()[2].addItem(computer);

		// Office West Wall
		getRoom(Name.OFFICE).getWalls()[3].addItem(doors.get(4));

		Furniture painting = new Furniture("Painting", "A painting", new ArrayList<>(Arrays.asList("inspect")));
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
			}
		}
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
	
	@XmlElement
	public ArrayList<Door> getDoors(){
		return this.doors;
	}
	
	public void setDoors(ArrayList<Door> d){
		this.doors = d;
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
		return item.getActionsString();
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
			for (Item i : player.getInventory()) {
				if (inventoryItem.toString().equals(i.toString())) {
					inventoryItem = (MovableItem) i;
				}
			}
		}
		if(gameItem ==  null){
			feedback = inventoryItem.getAction(action).apply(gameItem, inventoryItem, player);
		}else
			feedback = gameItem.getAction(action).apply(gameItem, inventoryItem, player);
		System.out.println(feedback);
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
