package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Evidence;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
/**
 * Places all the objects in a container at random positions next to the item
 * that was kicked
 * 
 * @author Georgina Murphy
 *
 */

public class TurnOff extends Action {

	public TurnOff() {
		super("Turn Off", "Turn off security camera");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if (gameItem == null) {
			return "Need an item from the game";
		}
		if (inventoryItem == null) {
			return "Need an item from the inventory";
		}

		String feedback = "";
		if (gameItem.toString().equals("Camera")) {
			Evidence camera = (Evidence) gameItem;
			if (inventoryItem.toString().equals("Screw Driver") || inventoryItem.toString().equals("Hammer")) {
				camera.setValue(10);
				camera.removeAction("turnoff");
				camera.setCurrentImage("cameraoff.png");
				feedback = "The camera has been disabled";
			} else {
				feedback = "The camera cannot be disabled with " + inventoryItem;
			}
		}
		return feedback;
	}

}
