package evidence.gameworld;

import java.util.ArrayList;

import javax.swing.ImageIcon;
/**
 * Object for the body
 * @author Georgina Murphy
 *
 */
public class Body extends Item {

	public Body(String currentImage, String description) {
		super(currentImage, description);
	}

	@Override
	public void addActions() {
		super.actions = new ArrayList<Action>();
		actions.add(new PickUp());

	}

	@Override
	public void addImages() {
		super.images = new ArrayList<ImageIcon>();
		images.add(new ImageIcon("body.png", "Original dead body"));
	}

}
