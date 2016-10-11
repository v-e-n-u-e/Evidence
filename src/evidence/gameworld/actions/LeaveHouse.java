package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class LeaveHouse extends Action {
	public LeaveHouse() {
		super("Leave House", "Leave the house, you cannot return to the game if you leave");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		System.out.println("i entered the method");
		String feedback = "";
		if (gameItem == null) {
			return "Need an item from the game";
		}

		if (gameItem.toString().equals("Garage")) {
			player.setCurrentRoom(null);
			player.removeFromGame();
			System.out.println("SUp boiiii");
			feedback = "You have left the house. You are relying on your team now!";
		}
		System.out.println("i got to da bottom");
		return feedback;
	}
}
