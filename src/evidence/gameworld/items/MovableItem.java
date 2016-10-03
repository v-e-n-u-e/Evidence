package evidence.gameworld.items;

import java.util.List;
import java.util.Map;


/**
 * Class for a movable item.
 * A movable item is one that can be picked up and put into a players inventory or a container
 * @author Georgina Murphy
 *
 */
public class MovableItem extends Item {
	private int size;
	
	public MovableItem(String name, String description, List<String> actions, List<String> images, int size) {
		super(name, description, actions, images);
		this.size = size;
	}

	public int getSize() {
		return size;
	}

}
