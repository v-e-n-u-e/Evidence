package evidence.testobjects;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class TestItem implements Serializable{
	private static final long serialVersionUID = -5968895252584282495L;
	
	public String image;
	public int x;
	public int y;
	
	public TestItem(String img, int x, int y){
		this.image = img;
		this.x = x;
		this.y = y;
	}
}