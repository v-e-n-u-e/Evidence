package evidence.gameworld.items;

import evidence.gameworld.Player;
import evidence.gameworld.actions.Action;

public class WashHands extends Action {

	public WashHands(){
		super("Wash Hands", "Makes a player non bloody");
	}
	
	@Override
	public String apply(Item gameItem, MovableItem inventoryItem, Player player) {		
		String feedback = "";
		if(gameItem == null ){
			return "Need an item from the game";
		}
		
		if(gameItem.toString().equals("Sink")){
			player.setBloodie(false);
			feedback = "Hands have been washed";
		}
		
		return feedback;
	}

}
