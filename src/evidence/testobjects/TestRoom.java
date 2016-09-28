package evidence.testobjects;

import evidence.gameworld.Player;
import evidence.gameworld.Wall.Direction;

public class TestRoom{
	
	TestWall north;
	TestWall south;
	TestWall east;
	TestWall west;
	
	public TestRoom(){
		north = new TestWall();
		south = new TestWall();
		east = new TestWall();
		west = new TestWall();
	}
	
	public void setupRoom(){
		TestItem mop = new TestItem("img/bigmop.png");
		TestItem box = new TestItem("img/bigcbbox.png");
		TestItem fridge = new TestItem("img/bigfridge.png");
		TestItem camera = new TestItem("img/cameraon.png");
		TestItem painting = new TestItem("img/bigpainting.png");
		
		north.addItem(mop);
		north.addItem(box);
		
		south.addItem(fridge);
		
		east.addItem(camera);
		
		west.addItem(painting);
	}
	
	public TestWall getFacingWall(Player p){
		if(p.getCurrentDirection() == Direction.NORTH){
			return north;
		}
		else if(p.getCurrentDirection() == Direction.SOUTH){
			return south;
		}
		else if(p.getCurrentDirection() == Direction.EAST){
			return east;
		}
		else{
			return west;
		}
	}
}
