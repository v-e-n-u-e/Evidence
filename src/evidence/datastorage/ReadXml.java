package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import evidence.gameworld.Game;
import evidence.gameworld.Player;
import evidence.gameworld.Room;
import evidence.gameworld.Wall;
import evidence.gameworld.Room.Name;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
/**
 * Reads A new instance of Game from an XML file, All of the fields and classes from the previous instance
 * will be copied over to this new instance of game. The doors are then connected to the Rooms walls manually.
 * 
 * @author Connor
 *
 */
public class ReadXml {
	
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();
	private List<Door> doors = new ArrayList<Door>();
	private int seconds;

	public void ReadInGame(String FileName) throws Exception{
		try{
			File file = new File(FileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Game game = (Game) jaxbUnmarshaller.unmarshal(file);
			players = game.getPlayers();
			rooms = game.getRoom();
			seconds = game.getSeconds();
			/*Manually up the doors, This has to be done as it causes an infinite loop for 		
			jaxB if this is not done.*/
			setupDoors(game);
		
		}catch (JAXBException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public int getSeconds() {
		return seconds;
	}


	/**
	 * links the doors to a room and then adds the new doors to the current rooms.
	 * @param game
	 */
	private void setupDoors(Game game) {
		doors = new ArrayList<Door>();

		Door door = new Door("Door", "Door between the bedroom and the lounge",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.LOUNGE), false, 1, false);
		door.setCurrentImage("door.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the lounge and the bedroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.BEDROOM), false, 1, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the lounge and the kitchen",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.KITCHEN), false, 2, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the kitchen and the lounge",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.LOUNGE), false, 2, false);
		door.setCurrentImage("door.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the bathroom and the bedroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.BEDROOM), false, 3, false);
		door.setCurrentImage("door.png");
		door.setXPos(0);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the bedroom and the bathroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.BATHROOM), false, 3, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the bedroom and the office",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.OFFICE), false, 4, false);
		door.setCurrentImage("door.png");
		door.setXPos(220);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the office and the bedroom",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.BEDROOM), false, 4, false);
		door.setCurrentImage("door.png");
		door.setXPos(220);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the office and the kitchen",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.KITCHEN), false, 5, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the kitchen and the office",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.OFFICE), false, 5, false);
		door.setCurrentImage("door.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the garage and the kitchen",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.KITCHEN), false, 6, false);
		door.setCurrentImage("door.png");
		door.setXPos(400);
		door.setYPos(44);
		doors.add(door);

		door = new Door("Door", "Door between the kitchen and the garage",
				new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.GARAGE), false, 6, false);
		door.setCurrentImage("door.png");
		door.setXPos(20);
		door.setYPos(44);
		doors.add(door);

		
		for (int i = 0; i < doors.size()-1; i++) {
			doors.get(i).setOtherDoor(doors.get(i+1));
			doors.get(i+1).setOtherDoor(doors.get(i));
		}
		
	
		game.getRoom(Name.BATHROOM).getWalls()[3].getItems().set(0, doors.get(4));
		game.getRoom(Name.BEDROOM).getWalls()[1].getItems().set(1, doors.get(5));
		game.getRoom(Name.BEDROOM).getWalls()[2].getItems().set(0, doors.get(6));
		game.getRoom(Name.BEDROOM).getWalls()[3].getItems().set(0,doors.get(0));
		game.getRoom(Name.KITCHEN).getWalls()[0].getItems().set(0,doors.get(3));
		game.getRoom(Name.KITCHEN).getWalls()[1].getItems().set(0,doors.get(9));
		game.getRoom(Name.KITCHEN).getWalls()[3].getItems().set(0,doors.get(11));
		game.getRoom(Name.GARAGE).getWalls()[1].getItems().set(0,doors.get(10));
		game.getRoom(Name.LOUNGE).getWalls()[1].getItems().set(0,doors.get(1));
		game.getRoom(Name.LOUNGE).getWalls()[2].getItems().set(0,doors.get(2));
		game.getRoom(Name.OFFICE).getWalls()[0].getItems().set(0,doors.get(7));
		game.getRoom(Name.OFFICE).getWalls()[3].getItems().set(0,doors.get(8));
	}
	
}
