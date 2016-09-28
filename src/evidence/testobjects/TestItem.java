package evidence.testobjects;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class TestItem implements Serializable{
	private static final long serialVersionUID = -5968895252584282495L;
	
	String image;
	int x;
	int y;
	
	
	public TestItem(String img, int ex, int wy){
		this.image = img;
		this.x=ex;
		this.y=wy;
	}
}
