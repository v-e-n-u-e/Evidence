package evidence.gameworld.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.actions.Action;
import evidence.gameworld.actions.Burn;
import evidence.gameworld.actions.Clean;
import evidence.gameworld.actions.CutUp;
import evidence.gameworld.actions.CutWires;
import evidence.gameworld.actions.Drop;
import evidence.gameworld.actions.Enter;
import evidence.gameworld.actions.Fill;
import evidence.gameworld.actions.Flush;
import evidence.gameworld.actions.Kick;
import evidence.gameworld.actions.LeaveHouse;
import evidence.gameworld.actions.Light;
import evidence.gameworld.actions.Lock;
import evidence.gameworld.actions.PickUp;
import evidence.gameworld.actions.PlaceItem;
import evidence.gameworld.actions.PryOpen;
import evidence.gameworld.actions.RemoveItem;
import evidence.gameworld.actions.UnScrew;
import evidence.gameworld.actions.TurnOff;
import evidence.gameworld.actions.Unlock;
import evidence.gameworld.actions.Inspect;

/**
 * Abstract class for item.
 * 
 * Each item will have a name, description,List of actions, a current image, x and y positions and a 
 * boolean for bloody or not.
 * 
 * @author Georgina Murphy
 *
 */
@XmlTransient
@XmlSeeAlso({ Container.class, Door.class, Evidence.class, Key.class, MovableItem.class, Furniture.class })
public abstract class Item implements Serializable {

	private String name;
	private String description;
	private List<String> actions;
	private String currentImage;
	private int xPos;
	private int yPos;
	private boolean bloody;

	public Item(String name, String description, List<String> actions, boolean bloody) {
		this.name = name;
		this.description = description;
		this.actions = actions;
		this.bloody = bloody;
	}

	public Item() {

	}

	/**
	 * Method to get a new instance of an action from a string
	 * 
	 * @param actionString
	 *            = a string of the action you are looking for
	 * @return a new instance of the action
	 */
	public Action getAction(String actionString) {
		Action action = new Inspect();
		if (actionString.toLowerCase().startsWith("remove")) {
			String[] itemString = actionString.split(" ", 2);
			return new RemoveItem(itemString[1]);
		} else {
			actionString = actionString.toLowerCase().replaceAll("\\s+", "");
			switch (actionString) {
			case "cutup":
				action = new CutUp();
				break;
			case "enter":
				action = new Enter();
				break;
			case "lock":
				action = new Lock();
				break;
			case "unlock":
				action = new Unlock();
				break;
			case "pickup":
				action = new PickUp();
				break;
			case "flush":
				action = new Flush();
				break;
			case "kick":
				action = new Kick();
				break;
			case "inspect":
				action = new Inspect();
				break;
			case "placeitem":
				action = new PlaceItem();
				break;
			case "fill":
				action = new Fill();
				break;
			case "drop":
				action = new Drop();
				break;
			case "clean":
				action = new Clean();
				break;
			case "light":
				action = new Light();
				break;
			case "washhands":
				action = new WashHands();
				break;  
			case "unscrew":
				action = new UnScrew();
				break;
			case "leavehouse":
				action = new LeaveHouse();
				break;
			case "turnoff":
				action = new TurnOff();
				break;
			case "burn":
				action = new Burn();
				break;
			case "pryopen":
				action = new PryOpen();
				break;
			case "cutwires":
				action = new CutWires();
				break;
			}
		}
		return action;
	}

	public void setBloodie(boolean bloody) {
		this.bloody = bloody;
	}

	@XmlElement
	public void setCurrentImage(String fileName) {
		this.currentImage = fileName;
	}

	public void removeAction(String action) {
		actions.remove(action);
	}

	public void addAction(String action) {
		actions.add(action);
	}

	@XmlElement
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	@XmlElement
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public String toString() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	@XmlElement
	public String getImageName() {
		return this.currentImage;
	}

	@XmlElement
	public int getYPos() {
		return this.yPos;
	}

	@XmlElement
	public int getXPos() {
		return this.xPos;
	}

	public List<String> getActions() {
		return this.actions;
	}

	public List<String> getActionsString() {
		List<String> actionsStrings = new ArrayList<String>();
		for (String actionString : this.actions) {
			Action action = getAction(actionString);
			actionsStrings.add(action.toString());
		}
		return actionsStrings;
	}

	/**
	 * gets this items bloody image
	 * @return
	 */
	public String getBloodyImage() {
		return "b" + currentImage;
	}

	public String getCurrentImage() {
		return currentImage;
	}

	public boolean getBloody() {
		return bloody;
	}

	/**
	 * Makes this item bloody. 
	 * Changes its state
	 * Changes its image
	 * Adds, clean action
	 */
	public void makeBloody() {
		setBloodie(true);
		setCurrentImage(this.getBloodyImage());
		addAction("clean");
	}

}
