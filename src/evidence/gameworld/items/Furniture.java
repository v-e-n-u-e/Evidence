package evidence.gameworld.items;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import evidence.gameworld.Player;
import evidence.gameworld.actions.Action;

@XmlRootElement
public class Furniture extends Item {

	public Furniture(String name, String description, List<String> actions, boolean bloodied){
		super(name, description, actions, bloodied);
	}
	public Furniture(){
		
	}
}
