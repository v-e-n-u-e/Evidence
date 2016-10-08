package evidence.gameworld.items;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Key extends MovableItem {
	private int code;
	public Key(String name, String description, List<String> actions, int size , int code) {
		super(name, description, actions, size);
		this.code = code;
	}
	public Key(){
		
	}
	
	@XmlElement
	public int getCode(){
		return code;
	}
	
	public void setCode(int c){
		this.code = c;
	}

}
