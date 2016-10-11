package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class RemoveItem extends Action {

	public RemoveItem(String name) {
		super("Remove " + name, "Remove an item from a container");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if (gameItem == null) {
			return "Need an item from the game";
		}
		if(player.getInventory().size() >= 8){return "Cant hold anymore items!";}
		String feedback = "";
		if (gameItem instanceof Container) {
			Container container = (Container) gameItem;
			String[] s = super.getName().split(" ", 2);
			for (int i = 0; i < container.getContainedItems().size(); i++) {
				Item item = container.getContainedItems().get(i);
				if (item.toString().equals(s[1].trim())) {
					if (item.toString().equals("Gloves")) {
						player.addItem(item);
					} else {
						feedback = container.removeItem((MovableItem) item, player);
						container.removeAction("remove " + item.toString());
					}
				}else{
					feedback = item.toString() + " not in " + container.toString();
				}
			}
		} else
			feedback = "Cannot perform " + this.toString() + " on " + gameItem.toString();

		return feedback;
	}

}
