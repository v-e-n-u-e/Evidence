package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;


import evidence.gameworld.Room.Name;
import evidence.gameworld.actions.Action;
import evidence.gameworld.items.Item;

public class Game {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();

	/**
	 * Reads in the state of a game from a xml file
	 */
	public void setup() {
		rooms.add(new Room(Name.Bathroom));
		rooms.add(new Room(Name.Bedroom));

		// rooms.get
		// rooms.add(new Room(Name.Garage));
		// rooms.add(new Room(Name.Kitchen));
		// rooms.add(new Room(Name.Office));

		// read in game here from xml
	}

	/**
	 * Starts a new game
	 */
	public void start() {

	}

	/**
	 * Rotate the players view left
	 * 
	 * @param player
	 *            - the player who is rotating left
	 */
	public void rotateLeft(Player player) {
		player.rotateView("L");
	}

	/**
	 * Rotate the players view right
	 * 
	 * @param player
	 *            - the player who is rotating right
	 */
	public void rotateRight(Player player) {
		player.rotateView("R");
	}
	
	/**
	 * gets a string list of actions for the provided item
	 * @param item 
	 * @return
	 */
	public List<String> getActions(Item item){
		List<String> actionStrings = new ArrayList<String>();
		for(Action action : item.getActions()){
			actionStrings.add(action.toString());
		}
		return actionStrings;
	}
	
	
}
