package evidence.gameworld.items;


import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * Class for pieces of evidence.
 * 
 * Each item will have a name, description, 
 * 
 * @author Georgina Murphy
 *
 */
@XmlRootElement(name = "Evidence")
public class Evidence extends Item{
	private int value;
	
	public Evidence(String name, String description, List<String> actions, List<String> images, int value) {
		super(name, description, actions, images);
		this.value = value;
	}
	public Evidence(){
		
	}
	
	@XmlElement
	public int getValue(){
		return value;
	}
	
	public void setValue(int v){
		this.value =v;
	}
}
