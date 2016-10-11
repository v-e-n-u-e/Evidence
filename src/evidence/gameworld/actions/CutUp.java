/**
 *
 */
package evidence.gameworld.actions;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

/**
 * 
 * 
 * @author Georgina Murphy
 *
 */
@XmlRootElement
public class CutUp extends Action {

	public CutUp() {
		super("Cut Up", "Cut this item up");
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
		if (gameItem.toString().equals("Body")) {
			if (inventoryItem.toString().equals("Saw") || inventoryItem.toString().equals("Axe")) {
				player.getWall().addItem(makeBodyPart("barm.png", gameItem.getXPos(), gameItem.getYPos(), 1, true));
				player.getWall().addItem(makeBodyPart("bhead.png", gameItem.getXPos() + 30, gameItem.getYPos(), 4, true));
				player.setBloodie(true);
				player.getWall().removeItem(gameItem);

				feedback = gameItem.toString() + " was " + this.toString();
			} else
				feedback = "Cannot perform " + this.toString() + " using " + inventoryItem.toString();
		} else {
			feedback = "Cannot perform " + this.toString() + " on " + gameItem.toString();
		}

		return feedback;
	}

	public MovableItem makeBodyPart(String image, int x, int y, int size, boolean blood) {
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("pickup");
		actions.add("clean");
		MovableItem item = new MovableItem("Body Part", "This is a body part", actions, size, blood);
		item.setCurrentImage(image);
		item.setXPos(x);
		item.setYPos(y);
		return item;
	}

}
