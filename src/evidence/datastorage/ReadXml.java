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
 * will be copied over to this new instance of game. The doors are then connected to the walls manually.
 * 
 * @author Connor
 *
 */
public class ReadXml {
	
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();
	private List<Door> doors = new ArrayList<Door>();

public void ReadInGame(String FileName) throws Exception{
	try{
		File file = new File(FileName);
		JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Game game = (Game) jaxbUnmarshaller.unmarshal(file);
		doors = game.getDoors();
		players = game.getPlayers();
		rooms = game.getRoom();
		//Manually set roomOne and roomTwo for doors.
		//System.out.println(game.getRoom(Name.LOUNGE).getWalls()[1].getImageName());
		
		setupDoors(game);
		
		
	}catch (JAXBException e) {
	 e.printStackTrace();
	 throw e;
	}
}

public List<Room> getRoom() {
	return this.rooms;
}

public List<Player> getPlayers() {
	return this.players;
}


private void setupDoors(Game game) {
	this.doors = new ArrayList<Door>();

	Door door = new Door("Door", "Door between the lounge and the bedroom",
			new ArrayList<>(Arrays.asList("inspect", "unlock")), game.getRoom(Name.LOUNGE), game.getRoom(Name.BEDROOM), true,
			1, false);
	door.setCurrentImage("door.png");
	door.setXPos(60);
	door.setYPos(44);
	doors.add(door);

	door = new Door("Door", "Door between the lounge and the kitchen",
			new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.LOUNGE), game.getRoom(Name.KITCHEN),
			false, 2, false);
	door.setCurrentImage("door.png");
	door.setXPos(60);
	door.setYPos(44);
	doors.add(door);

	door = new Door("Door", "Door between the bathroom and the bedroom",
			new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.BATHROOM),game.getRoom(Name.BEDROOM), false, 3, false);
	door.setCurrentImage("door.png");
	door.setXPos(60);
	door.setYPos(44);
	doors.add(door);

	door = new Door("Door", "Door between the office and the bedroom",
			new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.OFFICE), game.getRoom(Name.BEDROOM),
			false, 4, false);
	door.setCurrentImage("door.png");
	door.setXPos(260);
	door.setYPos(44);
	doors.add(door);

	door = new Door("Door", "Door between the office and the kitchen",
			new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.OFFICE), game.getRoom(Name.KITCHEN),
			false, 5, false);
	door.setCurrentImage("door.png");
	door.setXPos(460);
	door.setYPos(44);
	doors.add(door);

	door = new Door("Door", "Door between the garage and the kitchen",
			new ArrayList<>(Arrays.asList("inspect", "enter", "lock")), game.getRoom(Name.GARAGE), game.getRoom(Name.KITCHEN),
			false, 6, false);
	door.setCurrentImage("door.png");
	door.setXPos(60);
	door.setYPos(44);
	doors.add(door);
	
	game.getRoom(Name.BATHROOM).getWalls()[3].getItems().set(0, doors.get(2));
	game.getRoom(Name.BEDROOM).getWalls()[1].getItems().set(1, doors.get(2));
	game.getRoom(Name.BEDROOM).getWalls()[2].getItems().set(0, doors.get(3));
	game.getRoom(Name.BEDROOM).getWalls()[3].getItems().set(0,doors.get(0));
	game.getRoom(Name.KITCHEN).getWalls()[0].getItems().set(0,doors.get(1));
	game.getRoom(Name.KITCHEN).getWalls()[1].getItems().set(0,doors.get(4));
	game.getRoom(Name.KITCHEN).getWalls()[3].getItems().set(0,doors.get(5));
	game.getRoom(Name.GARAGE).getWalls()[1].getItems().set(0,doors.get(5));
	game.getRoom(Name.LOUNGE).getWalls()[1].getItems().set(0,doors.get(0));
	game.getRoom(Name.LOUNGE).getWalls()[2].getItems().set(0,doors.get(1));
	game.getRoom(Name.OFFICE).getWalls()[0].getItems().set(0,doors.get(3));
	game.getRoom(Name.OFFICE).getWalls()[3].getItems().set(0,doors.get(4));
}
	
}
