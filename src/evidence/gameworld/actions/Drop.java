package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

public class Drop extends Action {

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		player.getWall().getItems().add(inventoryItem);
		player.removeItem(inventoryItem);
		return inventoryItem + " was dropped";
	}

}
