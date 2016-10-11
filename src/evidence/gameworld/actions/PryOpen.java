package evidence.gameworld.actions;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlElement
public class PryOpen extends Action {
	
	public PryOpen(){
		super("Pry Open","Pry this open");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";
		if (gameItem == null) {
			return "Need an item from the game";
		}
		if (inventoryItem == null){
			return "please select an item from your inventory";
		}
		if(gameItem.toString().equals("Computer") && inventoryItem.toString().equals("Crow Bar")){
			gameItem.removeAction("pryopen");
			gameItem.addAction("cutwire");
			System.out.println(gameItem.getActions());
			return "Back of computer has been pryed open, we can see the wires.";
		}
		return "You may need a crow bar to open this up";
	
	}

}
