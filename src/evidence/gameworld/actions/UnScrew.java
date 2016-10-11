package evidence.gameworld.actions;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;
import evidence.gameworld.items.MovableItem;

@XmlRootElement
public class UnScrew extends Action {
	
	public UnScrew(){
		super("Unscrew","unscrews this item");
	}

	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {
		String feedback = "";
		if (gameItem == null) {
			return "Need an item from the game";
		}
		if (inventoryItem == null){
			return "please select an item";
		}
		if(gameItem.toString().equals("Camera") && inventoryItem.toString().equals("Screw Driver")){
			gameItem.setCurrentImage("cameraoff.png");
			return "Camera has been disabled";
		}
		return "You may need a screw driver to do this";
	}

}
