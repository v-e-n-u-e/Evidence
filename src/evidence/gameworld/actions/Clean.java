package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
/**
 * Places all the objects in a container at random positions next to the item that was kicked
 * @author Georgina Murphy
 *
 */

public class Clean extends Action {

	public Clean() {
		super("Clean", "Clean this item");
	}
	
	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if(gameItem == null ){
			return "Need an item from the game";
		}
		if(inventoryItem == null ){
			return "Need an item from the inventory";
		}
		
		String feedback = "";
		if(inventoryItem.toString().equals("Bleach") || inventoryItem.toString().equals("Cloth")){
			gameItem.setBloodie(false);
			System.out.println("Image: "+ gameItem.getCurrentImage());
			gameItem.setCurrentImage(gameItem.getCurrentImage().substring(1));
			System.out.println("Image: "+ gameItem.getCurrentImage());
			
		}
		return feedback;
	}

}
