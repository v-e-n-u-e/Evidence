package evidence.gameworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import evidence.gameworld.Room.Name;
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
		
		List<Action> actions = new ArrayList<Action>();
		actions.add(new Unlock());
		HashMap<String, String> images = new HashMap<String, String>();
		images.put("cbbox.png", "Clean cardboard box");
		Item item = new Container("Cardbord Box", "A cardboard box", actions, images, 7);
		item.setCurrentImage("cbbox.png");
		item.setXPos(30);
		item.setYPos(100);
		rooms.get(0).getWalls()[0].addItem(item);
		
		actions.clear();
		actions.add(new PickUp());
		images.clear();
		images.put("axe.png", "clean axe");
		images.put("baxe.png", "bloodied axe");
		item = new MovableItem("axe", "an axe", actions, images, 3);
		item.setCurrentImage("axe.png");
		item.setXPos(30);
		item.setYPos(100);
		rooms.get(0).getWalls()[1].addItem(item);
		
		actions.clear();
		actions.add(new Enter());
		actions.add(new Unlock());
		actions.add(new Lock());
		images.clear();
		images.put("painting.png", "clean painting");
		item = new Door("Door", "The door goes nowhere", actions, images, rooms.get(0), null, true, 123);
		item.setCurrentImage("painting.png");
		item.setXPos(30);
		item.setYPos(100);
		rooms.get(0).getWalls()[2].addItem(item);
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
