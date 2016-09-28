package evidence.testobjects;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class TestItem implements Serializable{
	private static final long serialVersionUID = -5968895252584282495L;
	
	String image;
	
	public TestItem(String img){
		this.image = img;
	}
}
