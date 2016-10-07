package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

public class RemoveItem extends Action {

	public RemoveItem(){
		super("Remove Item", "Remove an item from a container");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if(gameItem == null ){
			return "Need an item from the game";
		}
		if(inventoryItem == null){
			return "Need an item from the inventory";
		}
		String feedback = "";
		if(gameItem instanceof Container){
			Container container = (Container)gameItem;
			feedback = container.getItem(inventoryItem, player);
		}else
			feedback = "Cannot perform " + this.toString() + " on " + gameItem.toString();

		return feedback;
	}

}
