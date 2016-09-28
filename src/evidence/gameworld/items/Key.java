package evidence.gameworld.items;

import java.util.List;
import java.util.Map;

import evidence.gameworld.actions.Action;

public class Key extends MovableItem {
	private int code;
	public Key(String name, String description, List<Action> actions, Map<String, String> images, int size , int code) {
		super(name, description, actions, images, size);
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}

}