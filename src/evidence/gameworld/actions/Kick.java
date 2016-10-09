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

public class Kick extends Action {

	public Kick() {
		super("Kick", "Kick this item");
	}
	
	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if(gameItem == null ){
			return "Need an item from the game";
		}
		
		String feedback = "";
		if(gameItem.toString().equals("Trash Can")){
			Container trashCan = (Container)gameItem;
			trashCan.setCurrentImage("img/trashcanside.png");
			trashCan.removeAction("kick");
			trashCan.removeAction("placeitem");
			for(int i = 0; i < trashCan.getContainedItems().size(); i++){
				MovableItem item = trashCan.getContainedItems().get(i);
				trashCan.removeAction("remove " + item.toString());
				trashCan.getContainedItems().remove(i);
				item.setXPos(i*100);
				item.setYPos(400);
				player.getWall().addItem(item);
				feedback += item.toString() + " ";
			}
			feedback +=  "were removed from the trash can ";
		}
		return feedback;
	}

}
