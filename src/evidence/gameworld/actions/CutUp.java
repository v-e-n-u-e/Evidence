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
		if (item.toString().equals("Body") ) {
			
			// TODO weapon item from inventory
			// TODO create body piece objects
			// TODO delete body object
		}

		return "Cannot perform " + this.toString() + " on " + item.toString();
	}

}
