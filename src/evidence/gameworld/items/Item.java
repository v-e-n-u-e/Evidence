package evidence.gameworld.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.actions.Action;
import evidence.gameworld.actions.CutUp;
import evidence.gameworld.actions.Enter;
import evidence.gameworld.actions.Flush;
import evidence.gameworld.actions.Kick;
import evidence.gameworld.actions.Lock;
import evidence.gameworld.actions.PickUp;
import evidence.gameworld.actions.RemoveItem;
import evidence.gameworld.actions.Unlock;
import evidence.gameworld.actions.Inspect;

/**
 * Abstract class for item.
 * 
 * Each item will have a name, description,
 * 
 * @author Georgina Murphy
 *
 */
@XmlTransient
@XmlSeeAlso({ Container.class, Door.class, Evidence.class, Key.class, MovableItem.class })
public abstract class Item implements Serializable {

	private String name;
	private String description;
	private List<String> actions;
	private String currentImage;
	private int xPos;
	private int yPos;

	public Item(String name, String description, List<String> actions) {
		this.name = name;
		this.description = description;
		this.actions = actions;
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
			String[] itemString =  actionString.split(" ", 2);
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
			}
		}
		return action;
	}

	@XmlElement
	public void setCurrentImage(String fileName) {
		// for(String image : images){
		// if(image.equals(fileName)){
		// currentImage = image;
		// }
		this.currentImage = fileName;
		// }
	}

	public void clearActions() {
		actions.clear();
	}

	public void removeAction(String action) {
		System.out.println("Removing: "+ action);
		actions.remove(action);
		for(String a: actions){
			System.out.println(a);
		}
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

	@XmlElement
	public List<String> getActions() {
		List<String> actionsStrings = new ArrayList<String>();
		for (String actionString : actions) {
			Action action = getAction(actionString);
			actionsStrings.add(action.toString());
		}
		return actionsStrings;
	}
}
