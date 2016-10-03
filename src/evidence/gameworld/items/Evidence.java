package evidence.gameworld.items;


import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import evidence.gameworld.actions.Action;

/**
 * Class for pieces of evidence.
 * 
 * Each item will have a name, description, 
 * 
 * @author Georgina Murphy
 *
 */
public class Evidence extends Item{
	private int value;
	
	public Evidence(String name, String description, List<Action> actions, List<String> images, int value) {
		super(name, description, actions, images);
		this.value = value;
	}
	
	
	public int getValue(){
		return value;
	}
}
