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
public class RipUp extends Action {


	public RipUp() {
		super("Rip Up", "Rip this item up");
	}

	@Override
	public String apply(Item item, Player player) {
		if (item.toString().equals("Body") ) {
			
			// TODO create body piece objects
			// TODO create blood objects
			// TODO delete body object
		}

		return "Cannot perform " + this.toString() + " on " + item.toString();
	}

}
