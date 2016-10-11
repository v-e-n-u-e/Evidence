package evidence.gameworld.actions;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class Fill extends Action {
	public Fill() {
		super("Fill", "Fill this item");
	}
	
	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";
		if (gameItem == null) {
			return "Need an item from the game";
		}
		
		if (gameItem.toString().equals("Bath")) {
			Container bath = (Container) gameItem;
			if (bath.getContainedItems().size() != 0) {
				feedback = "Bath must be empty to fill";
			} else {
				bath.setCapacity(0);
				feedback = "Bath full";
				bath.setActions(new ArrayList<String>());
			}
		}
		
		
		else if (inventoryItem == null) {
			return "Need an item from the inventory";
		}

		if (gameItem.toString().equals("Bucket")) {

			Container bucket = (Container) gameItem;
			if (bucket.getContainedItems().size() != 0) {
				feedback = "Bucket needs to be empty to fill";
			} else {
				bucket.setCapacity(0);
				feedback = "Bucket full";
			}
		}

		return feedback;
	}

}
