package evidence.gameworld.actions;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class Light extends Action {
	public Light() {
		super("Light", "Light this item");
	}
	
	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";
		if (gameItem == null) {
			return "Need an item from the game";
		}
		if (inventoryItem == null) {
			return "Need an item from the inventory";
		}
		
		if (gameItem.toString().equals("Fireplace") && inventoryItem.toString().equals("Matches")) {
			gameItem.setCurrentImage("firepalcelit.png");
			gameItem.removeAction("light");
			gameItem.addAction("burn");
			feedback = "Fireplace is now burning. This could be useful to get rid of evidence";
		}

		return feedback;
	}

}
