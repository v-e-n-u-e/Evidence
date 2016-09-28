package evidence.testobjects;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TestWall implements Serializable{
	private static final long serialVersionUID = -5773920118639098391L;
	
	ArrayList<TestItem> items;
	public String background;
	
	public TestWall(){
		items = new ArrayList<TestItem>();
		background = "img/testwall2.png";
	}
	
	public void addItem(TestItem toAdd){
		this.items.add(toAdd);
	}
}
