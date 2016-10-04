package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;

import evidence.gameworld.Room.Name;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Item;

public class Game {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();

	/**
	 * Reads in the state of a game from a xml file
	 */
	public void setup() {
		rooms.add(new Room(Name.BATHROOM, "bathroom.png", "bathroom.png", "bathroom.png", "bathroom.png"));
		rooms.add(new Room(Name.BEDROOM, "bedroom.png", "bedroom.png", "bedroom.png", "bedroom.png"));
		rooms.add(new Room(Name.KITCHEN, "kitchen.png", "kitchen.png", "kitchen.png", "kitchen.png"));
		rooms.add(new Room(Name.GARAGE, "garage.png", "garage.png", "garage.png", "garage.png"));
		rooms.add(new Room(Name.LOUNGE, "lounge.png", "lounge.png", "lounge.png", "lounge.png"));
		rooms.add(new Room(Name.OFFICE, "office.png", "office.png", "office.png", "office.png"));

		List<String> actions = new ArrayList<String>();
		List<String> images = new ArrayList<String>();

		actions.add("Enter");
		actions.add("Unlock");
		actions.add("Lock");
		images.add("painting.png");
		Door door = new Door("Door", "Door between the bathroom and the kitchen", actions, images, rooms.get(0),
				rooms.get(1), true, 123);
		door.setCurrentImage("img/door.png");
		door.setXPos(30);
		door.setYPos(100);
		rooms.get(0).getWalls()[1].addItem(door);
		rooms.get(1).getWalls()[0].addItem(door);
	}

	/**
	 * Starts a new game
	 */
	public void start() {

	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void addPlayer(Player p) {
		p.setRoom(rooms.get(0));
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
	public String apply(Item gameItem, Item inventoryItem, Player player, String action) {
		String feedback = "";
		for (Item i : player.getWall().getItems()) {
			if (gameItem.toString().equals(i.toString())) {
				for (Item i2 : player.getWall().getItems()) {
					if (inventoryItem.toString().equals(i2.toString())) {
						gameItem.getAction(action).apply(i, i2, player);
					}
				}
			}
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
