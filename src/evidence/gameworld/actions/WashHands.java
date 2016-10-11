package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class WashHands extends Action {
	public WashHands() {
		super("Wash Hands", "Wash blood off your hands");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";
		if (gameItem == null) {
			return "Need an item from the game";
		}
		
		if(gameItem.toString().equals("Sink")){
			player.setBloodie(false);
			feedback = "Your hands have been washed";
		}
		return feedback;
	}

}
