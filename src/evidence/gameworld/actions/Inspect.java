package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

public class Inspect extends Action {
	
	public Inspect(){
		super("Inspect Item", "Get this items description");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if (gameItem == null) {
			return "Need an item from the game";
		}
		String feedback = gameItem.getDescription();
		if (gameItem instanceof Door) {
			Door door = (Door) gameItem;
			if (door.getLocked())
				feedback += " it is locked";
			else
				feedback += " it is unlocked";
		}
		return feedback;
	}

}
