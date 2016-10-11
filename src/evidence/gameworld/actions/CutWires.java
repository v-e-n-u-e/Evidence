package evidence.gameworld.actions;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlElement
public class CutWires extends Action {
	
	public CutWires(){
		super("Cut Wires","Cut these wires");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		if (gameItem == null) {
			return "Need an item from the game";
		}
		if (inventoryItem == null){
			return "please select an item";
		}
		if(gameItem.toString().equals("Computer") && inventoryItem.toString().equals("Knife")){
			gameItem.removeAction("cutwire");
			//change image of computer
			gameItem.setCurrentImage("computeroff.png");
			gameItem.removeAction("cutwire");
			return "Hardware footage has been disabled";
		}
		return "You may need somthing to cut this like a knife";
	
	}

}
