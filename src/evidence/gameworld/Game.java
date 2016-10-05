package evidence.gameworld;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Room.Name;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class Game {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();

	/**
	 * Reads in the state of a game from a xml file
	 */
	public void setup() {
		rooms.add(new Room(Name.BATHROOM, "img/bathroom.png", "img/bathroom.png", "img/bathroom.png", "img/bathroom.png"));
		rooms.add(new Room(Name.BEDROOM, "img/bedroom.png", "img/bedroom.png", "img/bedroom.png", "img/bedroom.png"));
		rooms.add(new Room(Name.KITCHEN, "img/kitchen.png", "img/kitchen.png", "img/kitchen.png", "img/kitchen.png"));
		rooms.add(new Room(Name.GARAGE, "img/garage.png", "img/garage.png", "img/garage.png", "img/garage.png"));
		rooms.add(new Room(Name.LOUNGE, "img/lounge.png", "img/lounge.png", "img/lounge.png", "img/lounge.png"));
		rooms.add(new Room(Name.OFFICE, "img/office.png", "img/office.png", "img/office.png", "img/office.png"));

		List<String> actions = new ArrayList<String>();
		List<String> images = new ArrayList<String>();

		actions.add("Enter");
		actions.add("Unlock");
		actions.add("Lock");
		images.add("painting.png");
		Door door = new Door("Door", "Door between the bathroom and the kitchen", actions, images, rooms.get(0),
				rooms.get(1), true, 123);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(60);
		door.setYPos(230);
		rooms.get(0).getWalls()[1].addItem(door);
		rooms.get(1).getWalls()[0].addItem(door);
	}

	/**
	 * Reads in the state of a game from a xml file
	 */
	public void setup2() {
		rooms.add(new Room(Name.BATHROOM, "img/bathroom.png", "img/bathroom.png", "img/bathroom.png", "img/bathroom.png"));
		rooms.add(new Room(Name.BEDROOM, "img/bedroom.png", "img/bedroom.png", "img/bedroom.png", "img/bedroom.png"));
		rooms.add(new Room(Name.KITCHEN, "img/kitchen.png", "img/kitchen.png", "img/kitchen.png", "img/kitchen.png"));
		rooms.add(new Room(Name.GARAGE, "img/garage.png", "img/garage.png", "img/garage.png", "img/garage.png"));
		rooms.add(new Room(Name.LOUNGE, "img/lounge.png", "img/lounge.png", "img/lounge.png", "img/lounge.png"));
		rooms.add(new Room(Name.OFFICE, "img/office.png", "img/office.png", "img/office.png", "img/office.png"));

		List<String> actions = new ArrayList<String>();
		List<String> images = new ArrayList<String>();

		actions.add("PlaceItem");
		actions.add("RemoveItem");
		actions.add("Unlock");
		actions.add("Lock");
		images.add("img/safe.png");
		Container safe = new Container("Safe", "A safe", actions, images, true, 6);
		safe.setCurrentImage("img/safe.png");
		safe.setXPos(60);
		safe.setYPos(230);
		rooms.get(5).getWalls()[2].addItem(safe);

		actions.clear();
		images.clear();
		actions.add("TurnOff");
		actions.add("Smash");
		images.add("img/cameraon.png");
		images.add("img/cameraoff.png");
		Evidence camera = new Evidence("Camera", "A security camera", actions, images, 20);
		camera.setCurrentImage("img/cameraon.png");
		camera.setXPos(60);
		camera.setYPos(230);
		rooms.get(5).getWalls()[2].addItem(camera);
	}

	/**
	 * Starts a new game
	 */
	public void start() {

	}

	@XmlElement
	public List<Player> getPlayers(){
		return this.players;
	}

	@XmlElement
	public List<Room> getRoom(){
		return this.rooms;
	}

	public void setRooms(List<Room> r){
		this.rooms = r;
	}

	public void setPlayers(List<Player> p){
		this.players =p;
	}

	public void addPlayer(Player p){
		p.setRoom(rooms.get(0));
		this.players.add(p);
	}

	public Player getPlayerWithID(Integer ID){
		for(Player p : players){
			if(p.getID().equals(ID) ){
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
	public String apply(Item gameItem, MovableItem inventoryItem, Player player, String action) {
		String feedback = "";
		for (Item i : player.getWall().getItems()) {
			if (gameItem.toString().equals(i.toString())) {
				for (Item i2 : player.getWall().getItems()) {
					if (inventoryItem.toString().equals(i2.toString())) {
						gameItem.getAction(action).apply(i, (MovableItem)i2, player);
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
