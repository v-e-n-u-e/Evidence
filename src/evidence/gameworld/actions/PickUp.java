package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

/**
 * Pick up action allows player to pickup up an item and put it in their
 * inventory
 *
 * @author Georgina Murphy
 */
@XmlRootElement
public class PickUp extends Action {

	public PickUp() {
		super("Pick up", "Pick up this item and put it in your inventory");
	}

	/**
	 * Method to apply the pickup action to the provided item
	 *
	 * @param item
	 *            - the item the action is being applied to
	 * @return string - updated state
	 */
	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if (gameItem == null) {
			return "Need an item from the game";
		}
		String feedback = "";
		if (gameItem instanceof MovableItem) {
			MovableItem mItem = (MovableItem) gameItem;			
			if (player.getInventory().size() < 8){
				player.getCurrentRoom().removeItem(player.getCurrentDirection(), mItem);
				player.addItem(mItem);
				player.bloodie(mItem);
				feedback = gameItem.toString() + " has been added to your inventory";
			}else
				feedback = "Your inventory is full. You can't pick this item up.";
		} else {
			feedback = "Cannot perform " + this.toString() + " on " + gameItem.toString();
		}
		System.out.println(player.getInventory());
		return feedback;
	}
}
