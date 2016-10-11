package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class Burn extends Action {
	public Burn() {
		super("Burn", "Burn stuff");
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
		
		if(gameItem.toString().equals("Fireplace")){
			if(inventoryItem.getSize() < 3){
			player.getWall().removeItem(inventoryItem);
			player.removeItem(inventoryItem);
			feedback = inventoryItem + " was burned";
			}else
				feedback = inventoryItem + " cannot be burned";
		}
		return feedback;
	}

}
