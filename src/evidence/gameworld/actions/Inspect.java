package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class Inspect extends Action {
	
	public Inspect(){
		super("Inspect Item", "Get this items description");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if (gameItem == null) {
			return "Need an item from the game";
		}
		String feedback = "\n" + gameItem.getDescription();
		if (gameItem instanceof Door) {
			Door door = (Door) gameItem;
			if (door.getLocked())
				feedback += ". It is locked";
			else
				feedback += ". It is unlocked";
		}
		System.out.println(feedback);
		
		return feedback;
	}

}
