package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

public class PryOpen extends Action {

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";
		if (gameItem == null) {
			return "Need an item from the game";
		}
		if (inventoryItem == null){
			return "please select an item";
		}
		if(gameItem.toString().equals("Computer") && inventoryItem.toString().equals("Crow Bar")){
			gameItem.setCurrentImage("cameraoff.png");
			return "Back of computer has been pryed open, we can see the wires.";
		}
		return "You may need a crow bar to open this up";
	
	}

}
