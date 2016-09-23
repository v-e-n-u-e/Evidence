package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;

import evidence.gameworld.Room.Name;

public class Game {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();
	
	/**
	 * Reads in the state of a game from a xml file
	 */
	public void setup(){
		rooms.add(new Room(Name.Bathroom));
		rooms.add(new Room(Name.Bedroom));
		rooms.add(new Room(Name.Garage));
		rooms.add(new Room(Name.Kitchen));
		rooms.add(new Room(Name.Office));
		
		//read in game here from xml
	}
	
	/**
	 * Starts a new game
	 */
	public void start(){
		
	}
}
