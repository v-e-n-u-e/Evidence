package evidence.gameworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import evidence.gameworld.Room.Name;
import evidence.gameworld.Wall.Direction;
import evidence.gameworld.actions.Action;
import evidence.gameworld.actions.Enter;
import evidence.gameworld.actions.Lock;
import evidence.gameworld.actions.PickUp;
import evidence.gameworld.actions.Unlock;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

public class Game {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();

	/**
	 * Reads in the state of a game from a xml file
	 */
	public void setup() {
		rooms.add(new Room(Name.BATHROOM));
		rooms.add(new Room(Name.BEDROOM));
		rooms.add(new Room(Name.KITCHEN));
		rooms.add(new Room(Name.GARAGE));
		rooms.add(new Room(Name.LOUNGE));
		rooms.add(new Room(Name.OFFICE));
		
		List<Action> actions = new ArrayList<Action>();
		List<String> images = new ArrayList<String>();
		
		actions.add(new Enter());
		actions.add(new Unlock());
		actions.add(new Lock());
		images.clear();
		images.add("painting.png");
		Door door = new Door("Door", "Door between the bathroom and the kitchen", actions, images, rooms.get(0), rooms.get(1), true, 123);
		door.setCurrentImage("painting.png");
		door.setXPos(30);
		door.setYPos(100);
		rooms.get(0).getWalls()[2].addItem(door);
		rooms.get(1).getWalls()[0].addItem(door);
		
		Player player = new Player();
		player.setDirection(Direction.SOUTH);
		player.setRoom(rooms.get(0));
		players.add(player);
	}

	/**
	 * Starts a new game
	 */
	public void start() {

	}
	
	public List<Player> getPlayers(){
		return this.players;
	}
	
	public void addPlayer(Player p){
		this.players.add(p);
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
		List<String> actionStrings = new ArrayList<String>();
		for (Action action : item.getActions()) {
			actionStrings.add(action.toString());
		}
		return actionStrings;
	}
	
	/**
	 * Method to apply an action on an item by a player
	 * 
	 * @param item - the item that is being acted on
	 * @param player - the player that is doing the action
	 * @param action - the action that is being performed
	 * @return - a string with updated state
	 */
	public String apply(Item item, Player player, String action){
		String feedback = "";
		switch(action){
		case "Enter":
			feedback = new Enter().apply(item, player);
		}
		return feedback;
	}
	
	
	/**
	 * Looks through all the rooms for evidence
	 * 
	 * @return
	 */
	public int calculateScore(){
		int score = 0;
		for(Room room : rooms){
			for(Wall wall : room.getWalls()){
				for(Item item : wall.getItems()){
					if(item instanceof Evidence){
						Evidence evidence = (Evidence)item;
						score += evidence.getValue();
					}
				}
			}
		}
		return score;
	}
}
