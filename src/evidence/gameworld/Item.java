package evidence.gameworld;


import java.util.List;

import javax.swing.ImageIcon;

public abstract class  Item {
	ImageIcon currentImage;
	List<Action> actions;
	List<ImageIcon> images; //will be null if there are no alternate images
	
	public Item(String currentImage, String description){
		this.currentImage  = new ImageIcon(currentImage, description);
	}
	
	public abstract void addActions();
	public abstract void addImages();
}
