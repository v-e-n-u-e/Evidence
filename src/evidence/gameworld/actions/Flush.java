package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
/**
 * Removes all items in the container from the game
 * 
 * 
 * @author Georgina Murphy
 *
 */
public class Flush extends Action {
	public Flush() {
		super("Flush", "All items in the toilet are removed from the game");
	}
	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";
		if(gameItem == null ){
			return "Need an item from the game";
		}
		if(gameItem.toString().equals("Toilet")){
			Container toilet = (Container)gameItem;
			for(int i = 0; i < toilet.getContainedItems().size(); i++){
				toilet.removeAction("remove " + toilet.getContainedItems().get(i).toString());
				toilet.setCapacity(toilet.getCapacity() + toilet.getContainedItems().get(i).getSize());
				toilet.getContainedItems().remove(i);
			}
			feedback = "Toilet flushed, it is now empty";
		}
		
		return feedback;
	}

}
