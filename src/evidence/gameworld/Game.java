package evidence.gameworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.datastorage.CreateXml;
import evidence.datastorage.ReadXml;
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
	private List<Room> rooms = new ArrayList<Room>();
	private List<Player> players = new ArrayList<Player>();
	private ArrayList<Door> doors = new ArrayList<Door>();
	private List<Player> storedPlayers = new ArrayList<Player>();
	private int seconds;

	public Game() {

	}

	public void setSeconds(int secondsLeft) {
		this.seconds = secondsLeft;
	}

	@XmlElement
	public int getSeconds() {
		return this.seconds;
	}

	/**
	 * Reads in the state of a game from a Xml file
	 * 
	 * @throws Exception
	 */
	public void ReadFromXml(String FileName) throws Exception {
		ReadXml t = new ReadXml();
		t.ReadInGame(FileName);
		this.storedPlayers = t.getPlayers();
		this.rooms = t.getRooms();
		this.seconds = t.getSeconds();
	}

	/**
	 * Writes the current instance of this game to an Xml file.
	 * 
	 * @param FileName
	 * @throws Exception
	 */
	public void CreateXml(String FileName) throws Exception {
		CreateXml t = new CreateXml();
		t.CreateGame(FileName, this);
	}

	public void UpdatePlayersInv() {
		for (int i = 0; i < storedPlayers.size(); i++) {  
			// Set Direction
			players.get(i).setCurrentDirection(storedPlayers.get(i).getCurrentDirection());
			// Set Inventory
			players.get(i).setInventory(storedPlayers.get(i).getInventory());
			// Set Room
			players.get(i).setCurrentRoom(this.getRoom(Name.BATHROOM));//error in enter.apply
			// Set FeedBack
			players.get(i).setFeedback(storedPlayers.get(i).getFeedback());
			
		}
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

	private void setupRooms() {
		rooms.add(new Room(Name.BATHROOM, "obj/bathroom.png", "obj/bathroom.png", "obj/bathroom.png",
				"obj/bathroom.png"));
		rooms.add(new Room(Name.BEDROOM, "obj/bedroom.png", "obj/bedroom.png", "obj/bedroom.png", "obj/bedroom.png"));
		rooms.add(
				new Room(Name.KITCHEN, "obj/kitchenhole.png", "obj/kitchen.png", "obj/kitchen.png", "obj/kitchen.png"));
		rooms.add(new Room(Name.GARAGE, "obj/garage.png", "obj/garage.png", "obj/garage.png", "obj/garage.png"));
		rooms.add(new Room(Name.LOUNGE, "obj/lounge.png", "obj/lounge.png", "obj/loungehole.png", "obj/lounge.png"));
		rooms.add(new Room(Name.OFFICE, "obj/office.png", "obj/office.png", "obj/office.png", "obj/office.png"));
	}

	private ArrayList<Door> setupDoors() {
		doors = new ArrayList<Door>();

		Door door = new Door("Door", "Door to the lounge",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.LOUNGE), false, 1, false);
		door.setCurrentImage("door.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the bedroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.BEDROOM), false, 1, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the kitchen",
				new ArrayList<>(Arrays.asList("inspect", "unlock")), getRoom(Name.KITCHEN), true, 2, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the lounge",
				new ArrayList<>(Arrays.asList("inspect", "unlock")), getRoom(Name.LOUNGE), true, 2, false);
		door.setCurrentImage("door.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the bedroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.BEDROOM), false, 3, false);
		door.setCurrentImage("door.png");
		door.setXPos(0);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the bathroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.BATHROOM), false, 3, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the office",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.OFFICE), false, 4, false);
		door.setCurrentImage("door.png");
		door.setXPos(220);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the bedroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.BEDROOM), false, 4, false);
		door.setCurrentImage("door.png");
		door.setXPos(220);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the kitchen",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.KITCHEN), false, 5, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);  

		door = new Door("Door", "Door to the office",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), getRoom(Name.OFFICE), false, 5, false);
		door.setCurrentImage("door.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the kitchen",
				new ArrayList<>(Arrays.asList("inspect", "unlock")), getRoom(Name.KITCHEN), false, 6, true);
		door.setCurrentImage("bdoor.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door to the garage",
				new ArrayList<>(Arrays.asList("inspect", "unlock")), getRoom(Name.GARAGE), false, 6, true);
		door.setCurrentImage("bdoor.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		for (int i = 0; i < doors.size() - 1; i += 2) {
			doors.get(i).setOtherDoor(doors.get(i + 1));
			doors.get(i + 1).setOtherDoor(doors.get(i));
		}

		return doors;
	}
  
	private void setupBathroom(ArrayList<Door> doors) {

		// Bathroom North Wall
		MovableItem mop = new MovableItem("Mop", "A mop. This mop can be used for cleaning up and concealing evidence",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 3, false);
		mop.setCurrentImage("mop.png");
		mop.setXPos(30);
		mop.setYPos(240);
		getRoom(Name.BATHROOM).getWalls()[0].addItem(mop);

		Container bucket = new Container("Bucket", "A bucket. Things can be hidden in here",
				new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 2, false);
		bucket.setCurrentImage("bucket.png");
		bucket.setXPos(87);
		bucket.setYPos(466);
		getRoom(Name.BATHROOM).getWalls()[0].addItem(bucket);

		// Bathroom East Wall
		Container bath = new Container("Bath", "A bath tub. Things can be hidden in here",
				new ArrayList<>(Arrays.asList("inspect", "placeitem", "fill")), false, 6, false);
		bath.setCurrentImage("bath.png");
		bath.setXPos(130);
		bath.setYPos(292);
		getRoom(Name.BATHROOM).getWalls()[1].addItem(bath);

		// South Wall
		Container toilet = new Container("Toilet", "A toilet. This toilet can be flushed",
				new ArrayList<>(Arrays.asList("inspect", "flush", "placeitem")), false, 2, false);
		toilet.setCurrentImage("toilet.png");
		toilet.setXPos(383);
		toilet.setYPos(290);
		getRoom(Name.BATHROOM).getWalls()[2].addItem(toilet);

		Container trashCan = new Container("Trash Can",
				"A trash can. You can kick this trash can over if you are feeling frustrated",
				new ArrayList<>(Arrays.asList("inspect", "kick", "placeitem")), false, 5, false);
		trashCan.setCurrentImage("trashcan.png");
		trashCan.setXPos(120);
		trashCan.setYPos(473);
		getRoom(Name.BATHROOM).getWalls()[2].addItem(trashCan);

		// Bathroom West Wall
		getRoom(Name.BATHROOM).getWalls()[3].addItem(doors.get(4));

		Furniture sink = new Furniture("Sink", "A sink. Go ahead and wash your hands here. \nIt just might conceal some evidence",
				new ArrayList<>(Arrays.asList("inspect", "washhands")), false);
		sink.setCurrentImage("sink.png");
		sink.setXPos(383);
		sink.setYPos(157);
		getRoom(Name.BATHROOM).getWalls()[3].addItem(sink);
	}

	private void setupBedroom(ArrayList<Door> doors) {

		// Bedroom North Wall
		Furniture bed = new Furniture("Bed",
				"A bed. You could nap here, or you could keep covering up a murder.",
				new ArrayList<>(Arrays.asList("inspect")), false);
		bed.setCurrentImage("bed.png");
		bed.setXPos(24);
		bed.setYPos(293);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(bed);

		Furniture bedsideTable = new Furniture("Bedside Table", "A bedside table that doesn't do much",
				new ArrayList<>(Arrays.asList("inspect")), false);
		bedsideTable.setCurrentImage("bedsidetable.png");
		bedsideTable.setXPos(511);
		bedsideTable.setYPos(389);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(bedsideTable);
		
		Key key = new Key("Key", "Key to the safe", new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, 555, false);
		key.setCurrentImage("key.png");
		key.setXPos(550);
		key.setYPos(370);
		getRoom(Name.BEDROOM).getWalls()[0].addItem(key);

		// Bedroom East Wall
		Container cupboard = new Container("Cupboard",
				"A Cupboard. This cupboard is quite large and can hide many things",
				new ArrayList<>(Arrays.asList("inspect", "unlock")), true, 7, false);
		cupboard.setCurrentImage("cupboard.png");
		cupboard.setXPos(200);
		cupboard.setYPos(200);
		getRoom(Name.BEDROOM).getWalls()[1].addItem(cupboard);
		
		Furniture painting3 = new Furniture("Painting", "This painting seems to be of three cool dudes...",
				new ArrayList<>(Arrays.asList("inspect")), false);
		painting3.setCurrentImage("painting3.png");
		painting3.setXPos(70);
		painting3.setYPos(130);
		getRoom(Name.BEDROOM).getWalls()[1].addItem(painting3);

		getRoom(Name.BEDROOM).getWalls()[1].addItem(doors.get(5));

		// Bedroom South Wall
		getRoom(Name.BEDROOM).getWalls()[2].addItem(doors.get(6));

		// Bedroom West Wall
		Furniture painting2 = new Furniture("Painting", "What could it mean...",
				new ArrayList<>(Arrays.asList("inspect")), false);
		painting2.setCurrentImage("painting2.png");
		painting2.setXPos(440);
		painting2.setYPos(130);
		getRoom(Name.BEDROOM).getWalls()[3].addItem(painting2);
		getRoom(Name.BEDROOM).getWalls()[3].addItem(doors.get(0));
	}

	private void setupKitchen(ArrayList<Door> doors) {

		// Kitchen North Wall
		getRoom(Name.KITCHEN).getWalls()[0].addItem(doors.get(3));

		Furniture stool = new Furniture("Stool", "A stool. Take a seat and strech your legs",
				new ArrayList<>(Arrays.asList("inspect")), false);
		stool.setCurrentImage("stool.png");
		stool.setXPos(314);
		stool.setYPos(381);
		getRoom(Name.KITCHEN).getWalls()[0].addItem(stool);

		Furniture stoolOne = new Furniture("Stool", "Another stool. Take a seat and strech your legs",
				new ArrayList<>(Arrays.asList("inspect")), false);
		stoolOne.setCurrentImage("stool.png");
		stoolOne.setXPos(513);
		stoolOne.setYPos(381);
		getRoom(Name.KITCHEN).getWalls()[0].addItem(stoolOne);

		// Kitchen East Wall
		getRoom(Name.KITCHEN).getWalls()[1].addItem(doors.get(9));

		Container fridge = new Container("Fridge", "A Fridge", new ArrayList<>(Arrays.asList("inspect", "placeitem")),
				false, 5, false);
		fridge.setCurrentImage("fridge.png");
		fridge.setXPos(394);
		fridge.setYPos(106);
		getRoom(Name.KITCHEN).getWalls()[1].addItem(fridge);
		
		Evidence blood = new Evidence("Blood", "Victim's Blood", new ArrayList<>(Arrays.asList("inspect", "clean")), 50,
				true);
		blood.setCurrentImage("bpool.png");
		blood.setXPos(12);
		blood.setYPos(592);
		getRoom(Name.KITCHEN).getWalls()[1].addItem(blood);

		// Kitchen South Wall
		MovableItem gloves = new MovableItem("Gloves", "Rubber gloves",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, false);
		gloves.setCurrentImage("gloves.png");
		gloves.setXPos(0);
		gloves.setYPos(0);

		MovableItem bleach = new MovableItem("Bleach", "Kitchen Bleach, can be used to clean blood off items",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 2, false);
		bleach.setCurrentImage("bleach.png");
		bleach.setXPos(100);
		bleach.setYPos(100);

		MovableItem cloth = new MovableItem("Cloth", "A cloth, can be used to clean blood off items",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 2, false);
		cloth.setCurrentImage("shirt.png");
		cloth.setXPos(200);
		cloth.setYPos(100);

		MovableItem matches = new MovableItem("Matches", "Matches can be used to light the fire",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 2, false);
		matches.setCurrentImage("matches.png");
		matches.setXPos(100);
		matches.setYPos(200);

		Container kBench = new Container("Kitchen Bench", "This kitchen bench is filled with cleaning products",
				new ArrayList<>(Arrays.asList("inspect", "placeitem", "remove " + gloves.toString(),
						"remove " + bleach.toString(), "remove " + matches.toString())),
				false, 3, false);

		kBench.getContainedItems().add(gloves);
		kBench.getContainedItems().add(bleach);
		kBench.getContainedItems().add(cloth);
		kBench.getContainedItems().add(matches);
		kBench.setCurrentImage("kbench.png");
		kBench.setXPos(244);
		kBench.setYPos(279);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(kBench);

		Container oven = new Container("Oven", "An oven", new ArrayList<>(Arrays.asList("inspect", "placeitem")), false,
				3, false);
		oven.setCurrentImage("oven.png");
		oven.setXPos(-18);
		oven.setYPos(279);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(oven);

		MovableItem scissors = new MovableItem("Scissors", "A pair of Kitchen scissors",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, false);
		scissors.setCurrentImage("scissors.png");
		scissors.setXPos(0);
		scissors.setYPos(0);
		getRoom(Name.KITCHEN).getWalls()[2].addItem(scissors);

		// Kitchen West Wall
		getRoom(Name.KITCHEN).getWalls()[3].addItem(doors.get(11));

		Furniture table = new Furniture("Table", "Dining Table", new ArrayList<>(Arrays.asList("inspect")), false);
		table.setCurrentImage("table2.png");
		table.setXPos(357);
		table.setYPos(337);
		getRoom(Name.KITCHEN).getWalls()[3].addItem(table);
	}

	private void setupGarage(ArrayList<Door> doors) {

		// Garage North Wall
		Furniture gDoor = new Furniture("Garage", "Garage door", new ArrayList<>(Arrays.asList("inspect", "leavehouse")), false);
		gDoor.setCurrentImage("garagedoor.png");
		gDoor.setXPos(76);
		gDoor.setYPos(70);
		getRoom(Name.GARAGE).getWalls()[0].addItem(gDoor);

		Furniture numpad = new Furniture("Numpad", "Numpad", new ArrayList<>(Arrays.asList("inspect")), false);
		numpad.setCurrentImage("numpad.png");
		numpad.setXPos(635);
		numpad.setYPos(259);
		getRoom(Name.GARAGE).getWalls()[0].addItem(numpad);
		
		Evidence blood2 = new Evidence("Blood", "Victim's Blood", new ArrayList<>(Arrays.asList("inspect", "clean")), 50,
				true);
		blood2.setCurrentImage("bpool.png");
		blood2.setXPos(12);
		blood2.setYPos(592);
		getRoom(Name.GARAGE).getWalls()[0].addItem(blood2);

		// Garage East Wall
		getRoom(Name.GARAGE).getWalls()[1].addItem(doors.get(10));

		// Garage South Wall
		MovableItem axe = new MovableItem("Axe", "An Axe. HINT - clean this to conceal evidence",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 3, false);
		axe.setCurrentImage("axe.png");
		axe.setXPos(54);
		axe.setYPos(350);
		getRoom(Name.GARAGE).getWalls()[2].addItem(axe);

		MovableItem sledgeHammer = new MovableItem("Sledge Hammer",
				"A Sledge Hammer. Try not to get your finger prints on this. \n HINT gloves are in the kitchen cupboard",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 3, false);
		sledgeHammer.setCurrentImage("sledgehammer.png");
		sledgeHammer.setXPos(440);
		sledgeHammer.setYPos(330);
		getRoom(Name.GARAGE).getWalls()[2].addItem(sledgeHammer);

		Container cbBox = new Container("CardboardBox",
				"A cardboard box. Nice and roomy. Lots of space to hide bloody clothes. \n maybe the police will think its a costume box...",
				new ArrayList<>(Arrays.asList("inspect", "placeitem")), false, 4, false);
		cbBox.setCurrentImage("cbbox.png");
		cbBox.setXPos(462);
		cbBox.setYPos(347);
		getRoom(Name.GARAGE).getWalls()[2].addItem(cbBox);

		MovableItem clothes = new MovableItem("Bloodied Clothing",
				"Bloody clothes. The police probably wont like seeing this",
				new ArrayList<>(Arrays.asList("inspect", "pickup", "clean")), 4, true);
		clothes.setCurrentImage("bpants.png");
		clothes.setXPos(260);
		clothes.setYPos(450);
		getRoom(Name.GARAGE).getWalls()[2].addItem(clothes);

		// Garage West Wall
		Furniture bench = new Furniture("Bench", "Work bench", new ArrayList<>(Arrays.asList("inspect")), false);
		bench.setCurrentImage("bench.png");
		bench.setXPos(111);
		bench.setYPos(293);
		getRoom(Name.GARAGE).getWalls()[3].addItem(bench);

		MovableItem saw = new MovableItem("Saw", "A saw", new ArrayList<>(Arrays.asList("inspect", "pickup")), 3,
				false);
		saw.setCurrentImage("saw.png");
		saw.setXPos(240);
		saw.setYPos(307);
		getRoom(Name.GARAGE).getWalls()[3].addItem(saw);

		MovableItem wrench = new MovableItem("Wrench", "A wrench", new ArrayList<>(Arrays.asList("inspect", "pickup")),
				1, false);
		wrench.setCurrentImage("wrench.png");
		wrench.setXPos(0);
		wrench.setYPos(0);

		MovableItem hammer = new MovableItem("Hammer", "A Hammer", new ArrayList<>(Arrays.asList("inspect", "pickup")),
				1, false);
		hammer.setCurrentImage("hammer.png");
		hammer.setXPos(0);
		hammer.setYPos(0);

		MovableItem screwdriver = new MovableItem("Screw Driver", "A screw driver",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, false);
		screwdriver.setCurrentImage("screwdriver.png");
		screwdriver.setXPos(350);
		screwdriver.setYPos(436);
		
		MovableItem crowBar = new MovableItem("Crow Bar", "A crow bar",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, false);
		crowBar.setCurrentImage("crowbar.png");
		crowBar.setXPos(0);
		crowBar.setYPos(0);

		Container toolbox = new Container("Tool Box", "A tool Box",
				new ArrayList<>(Arrays.asList("inspect", "placeitem", "remove " + wrench.toString(),
						"remove " + hammer.toString(), "remove " + screwdriver.toString(), "remove " + crowBar.toString())),
				false, 7, false);
		toolbox.setCurrentImage("toolbox.png");
		toolbox.setXPos(132);
		toolbox.setYPos(242);
		toolbox.getContainedItems().add(screwdriver);
		toolbox.getContainedItems().add(wrench);
		toolbox.getContainedItems().add(hammer);
		toolbox.getContainedItems().add(crowBar);
		getRoom(Name.GARAGE).getWalls()[3].addItem(toolbox);
	}

	private void setupLounge(ArrayList<Door> doors) {

		// Lounge North Wall
		Furniture fireplace = new Furniture("Fireplace", "A Fireplace",
				new ArrayList<>(Arrays.asList("inspect", "light")), false);
		fireplace.setCurrentImage("fireplace.png");  
		fireplace.setXPos(98);
		fireplace.setYPos(119);
		getRoom(Name.LOUNGE).getWalls()[0].addItem(fireplace);
		
		Key key = new Key("Key", "Key to the garage", new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, 6, true);
		key.setCurrentImage("key.png");
		key.setXPos(450);
		key.setYPos(160);
		getRoom(Name.LOUNGE).getWalls()[0].addItem(key);

		// Lounge East Wall

		getRoom(Name.LOUNGE).getWalls()[1].addItem(doors.get(1));

		Furniture tvTable = new Furniture("Coffee Table", "A table", new ArrayList<>(Arrays.asList("inspect")), false);
		tvTable.setCurrentImage("table.png");
		tvTable.setXPos(0);
		tvTable.setYPos(428);
		getRoom(Name.LOUNGE).getWalls()[1].addItem(tvTable);

		Furniture tv = new Furniture("TV", "A Television", new ArrayList<>(Arrays.asList("inspect")), false);
		tv.setCurrentImage("tv.png");
		tv.setXPos(70);
		tv.setYPos(241);
		getRoom(Name.LOUNGE).getWalls()[1].addItem(tv);
		
		Evidence blood3 = new Evidence("Blood", "Victim's Blood", new ArrayList<>(Arrays.asList("inspect", "clean")), 50,
				true);
		blood3.setCurrentImage("bpool.png");
		blood3.setXPos(12);
		blood3.setYPos(592);
		getRoom(Name.LOUNGE).getWalls()[1].addItem(blood3);

		// Lounge South Wall
		getRoom(Name.LOUNGE).getWalls()[2].addItem(doors.get(2));

		Furniture stool = new Furniture("Stool", "A stool", new ArrayList<>(Arrays.asList("inspect")), false);
		stool.setCurrentImage("stool.png");
		stool.setXPos(30);
		stool.setYPos(381);
		getRoom(Name.LOUNGE).getWalls()[2].addItem(stool);

		Furniture stoolOne = new Furniture("Stool", "A stool", new ArrayList<>(Arrays.asList("inspect")), false);
		stoolOne.setCurrentImage("stool.png");
		stoolOne.setXPos(252);
		stoolOne.setYPos(381);
		getRoom(Name.LOUNGE).getWalls()[2].addItem(stoolOne);

		// Lounge West Wall
		Furniture sofa = new Furniture("Sofa", "A sofa", new ArrayList<>(Arrays.asList("inspect")), false);
		sofa.setCurrentImage("sofa.png");
		sofa.setXPos(63);
		sofa.setYPos(308);
		getRoom(Name.LOUNGE).getWalls()[3].addItem(sofa);

		Furniture coffeeTable = new Furniture("Coffee Table", "A coffee table",
				new ArrayList<>(Arrays.asList("inspect")), false);
		coffeeTable.setCurrentImage("table.png");
		coffeeTable.setXPos(132);
		coffeeTable.setYPos(450);
		getRoom(Name.LOUNGE).getWalls()[3].addItem(coffeeTable);
	}

	private void setupOffice(ArrayList<Door> doors) {

		// Office North Wall
		getRoom(Name.OFFICE).getWalls()[0].addItem(doors.get(7));

		Furniture potplant = new Furniture("Pot Plant", "A pot plant", new ArrayList<>(Arrays.asList("inspect")),
				false);
		potplant.setCurrentImage("potplant.png");
		potplant.setXPos(0);
		potplant.setYPos(0);
		getRoom(Name.OFFICE).getWalls()[0].addItem(potplant);

		// Office East Wall
		Evidence body = new Evidence("Body", "Victim's Body", new ArrayList<>(Arrays.asList("inspect", "cutup")), 0,
				true);
		body.setCurrentImage("nbbody.png");
		body.setXPos(12);
		body.setYPos(466);
		getRoom(Name.OFFICE).getWalls()[1].addItem(body);

		MovableItem knife = new MovableItem("Knife", "A kitchen kinfe",
				new ArrayList<>(Arrays.asList("inspect", "pickup", "clean")), 2, true);
		knife.setCurrentImage("bknife.png");
		knife.setXPos(445);
		knife.setYPos(532);
		getRoom(Name.OFFICE).getWalls()[1].addItem(knife);

		// Office South Wall  
		Evidence camera = new Evidence("Camera", "A security camera", new ArrayList<>(Arrays.asList("inspect", "turnoff","unscrew")), 30,
				false);
		camera.setCurrentImage("cameraon.png");
		camera.setXPos(550);
		camera.setYPos(30);
		getRoom(Name.OFFICE).getWalls()[2].addItem(camera);

		MovableItem gold = new MovableItem("Gold", "A bar of gold",
				new ArrayList<>(Arrays.asList("inspect", "pickup")), 2, true);
		gold.setCurrentImage("gold.png");
		
		Container safe = new Container("Safe", "A safe", new ArrayList<>(Arrays.asList("inspect", "unlock","remove " + gold.toString())), true, 6,
				false);
		safe.setCurrentImage("safe.png");
		safe.setXPos(448);
		safe.setYPos(366);
		safe.getContainedItems().add(gold);
		getRoom(Name.OFFICE).getWalls()[2].addItem(safe);
		
		Furniture desk = new Furniture("Desk", "Office desk", new ArrayList<>(Arrays.asList("inspect")), false);
		desk.setCurrentImage("desk.png");
		desk.setXPos(7);
		desk.setYPos(330);
		getRoom(Name.OFFICE).getWalls()[2].addItem(desk);

		Furniture computer = new Furniture("Computer", "A computer", new ArrayList<>(Arrays.asList("inspect","pryopen")), false);
		computer.setCurrentImage("computer.png");  
		computer.setXPos(41);
		computer.setYPos(165);
		getRoom(Name.OFFICE).getWalls()[2].addItem(computer);
		
		Key key = new Key("Key", "Key for the door between the Kitchen and the lounge", new ArrayList<>(Arrays.asList("inspect", "pickup")), 1, 2, true);
		key.setCurrentImage("key.png");
		key.setXPos(330);
		key.setYPos(325);
		getRoom(Name.OFFICE).getWalls()[2].addItem(key);

		// Office West Wall
		getRoom(Name.OFFICE).getWalls()[3].addItem(doors.get(8));

		Furniture painting = new Furniture("Painting", "A painting", new ArrayList<>(Arrays.asList("inspect")), false);
		painting.setCurrentImage("painting.png");
		painting.setXPos(170);
		painting.setYPos(130);
		getRoom(Name.OFFICE).getWalls()[3].addItem(painting);
	}

	public String timeUp() {
		int score = calculateScore();
		if (score < 70) {
			return "Congratualtions you concealed enough evidence, you have all evaded capture";
		} else {
			return "Enough evidence was found at the house to convict all of you. Enjoy prison";
		}
	}

	/**
	 * Gets a room based on the given name
	 * 
	 * @param name
	 *            - name of the room you are looking for
	 * @return
	 */
	public Room getRoom(Name name) {
		Room room = null;
		for (Room r : rooms) {
			if (r.getName().equals(name)) {
				room = r;
			}
		}
		return room;
	}

	@XmlElement
	public List<Room> getRoom() {
		return this.rooms;
	}

	@XmlElement
	public List<Player> getPlayers() {
		return this.players;
	}

	@XmlElement
	public ArrayList<Door> getDoors() {
		return this.doors;
	}

	public void setDoors(ArrayList<Door> d) {
		this.doors = d;
	}

	public void setRooms(List<Room> r) {
		this.rooms = r;
	}

	public void setPlayers(List<Player> p) {
		this.players = p;
	}

	public void addPlayer(Player p) {
		p.setCurrentRoom(rooms.get(0));
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
		if(player.getWall().getImageName().equals("obj/outside.png")){
			return "You cannot rotate outside";
		}
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
		if(player.getWall().getImageName().equals("obj/outside.png")){
			return "You cannot rotate outside";
		}
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
		if (gameItem == null) {
			feedback = inventoryItem.getAction(action).apply(gameItem, inventoryItem, player);
		} else{
			feedback = gameItem.getAction(action).apply(gameItem, inventoryItem, player);
		System.out.println(gameItem.getActions());
		System.out.println(action);
		System.out.println( gameItem.getAction(action));
		}
		return feedback;
	}

	/**
	 * Looks through all the rooms for evidence
	 *
	 * @return
	 */
	public int calculateScore() {
		int score = 0;
		for (Player player : players) {
			for (Item item : player.getInventory()) {
				rooms.get(0).addItem(item);
			}
			if (player.getBloody()) {
				score += 10;
			}
		}

		for (Room room : rooms) {
			for (Wall wall : room.getWalls()) {
				for (Item item : wall.getItems()) {
					if (item.getBloody()) {
						score += 10;
					}
					if (item instanceof Evidence) {
						Evidence evidence = (Evidence) item;
						score += evidence.getValue();
					}
				}
			}
		}
		System.out.println("SCORE WAS " + score);
		return score;
	}
	
	public boolean allPlayersOutside(){
		for(Player p : players){
			if(!p.getWall().getImageName().equals("obj/outside.png") ){
				return false;
			}
		}
		return true;
	}
}
