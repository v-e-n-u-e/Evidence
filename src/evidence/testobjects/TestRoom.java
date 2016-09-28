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
		TestItem mop = new TestItem("img/bigmop.png",600,400);
		TestItem box = new TestItem("img/bigcbbox.png",530,450);
		TestItem fridge = new TestItem("img/bigfridge.png",20,300);
		//TestItem camera = new TestItem("img/cameraon.png");
		TestItem painting = new TestItem("img/bigpainting.png",450,130);
		
		north.addItem(mop);
		north.addItem(box);
		
		south.addItem(fridge);
		
		//east.addItem(camera);
		
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
