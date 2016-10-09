package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

public class Drop extends Action {
	public Drop() {
		super("Drop", "Drop item here");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if (inventoryItem == null) {
			return "Need an item from the inventory";
		}
		player.getWall().getItems().add(inventoryItem);
		player.removeItem(inventoryItem);
		return inventoryItem + " was dropped";
	}

}
