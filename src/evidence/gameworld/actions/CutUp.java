/**
 * 
 */
package evidence.gameworld.actions;

import evidence.gameworld.Player;
import evidence.gameworld.items.Item;

/**
 * @author Georgina Murphy
 *
 */
public class CutUp extends Action {


	public CutUp() {
		super("Cut Up", "Cut this item up");
	}

	@Override
	public String apply(Item gameItem, Item inventoryItem, Player player) {
		String feedback = "";
		if (gameItem.toString().equals("Body") ) {
				
			
			// TODO create body piece objects
			// TODO delete body object
			
			feedback = gameItem.toString() + " was " + this.toString();
		}else{
			feedback = "Cannot perform " + this.toString() + " on " + gameItem.toString();
		}

		return feedback;
	}

}
