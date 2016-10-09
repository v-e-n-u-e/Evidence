package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
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
public class TestReadXml {
	
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();


public void ReadInGame(String FileName) throws Exception{
	try{
		File file = new File(FileName);
		Door d = new Door();
		JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Game game = (Game) jaxbUnmarshaller.unmarshal(file);
		List<Door> doors = new ArrayList<Door>();
		doors = game.getDoors();
		players = game.getPlayers();
		rooms = game.getRoom();
		//Manually set roomOne and roomTwo for doors.
		doors.get(0).setRoomOne(game.getRoom(Name.LOUNGE));
		doors.get(0).setRoomTwo(game.getRoom(Name.BEDROOM));
		doors.get(1).setRoomOne(game.getRoom(Name.LOUNGE));
		doors.get(1).setRoomTwo(game.getRoom(Name.KITCHEN));
		doors.get(2).setRoomOne(game.getRoom(Name.BATHROOM));
		doors.get(2).setRoomTwo(game.getRoom(Name.BEDROOM));
		doors.get(3).setRoomOne(game.getRoom(Name.OFFICE));
		doors.get(3).setRoomTwo(game.getRoom(Name.BEDROOM));
		doors.get(4).setRoomOne(game.getRoom(Name.OFFICE));
		doors.get(4).setRoomTwo(game.getRoom(Name.KITCHEN));
		doors.get(5).setRoomOne(game.getRoom(Name.GARAGE));
		doors.get(5).setRoomTwo(game.getRoom(Name.KITCHEN));
		
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
		
		System.out.println(doors.get(0).getActionsString().get(0));
		
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
	
}
