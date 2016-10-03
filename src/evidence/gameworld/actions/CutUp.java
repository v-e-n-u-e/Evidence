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
	public String apply(Item item, Player player) {
		String feedback = "";
		if (item.toString().equals("Body") ) {
				
			
			// TODO create body piece objects
			// TODO delete body object
			
			feedback = item.toString() + " was " + this.toString();
		}else{
			feedback = "Cannot perform " + this.toString() + " on " + item.toString();
		}

		return feedback;
	}

}
