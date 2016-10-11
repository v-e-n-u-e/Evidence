package evidence.gui;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import evidence.clientserver.infoholders.RenderPackage;
import evidence.gameworld.Wall;
import evidence.gameworld.items.Item;
import evidence.testobjects.TestItem;
import evidence.testobjects.TestWall;

/**
 * The RenderCanvas is the panel that renders the game view to the user. It has a custom paint method
 * to render images to the screen.
 * 
 * @author Callum Crosby
 */
public class RenderCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	
	RenderPackage rPackage;
	public RenderCanvas(){
		super();
	}
	
	//Loops through every item in the current wall the player is facing and draws them at their specified x,y pos
	@Override
	public void paint(Graphics g){
		if(rPackage==null){return;}
		if(rPackage.getBackWall() != null){
			g.drawImage(new ImageIcon(rPackage.getBackWall().getImageName() ).getImage(), 0, 0, null);
			for(Item item : rPackage.getBackWall().getItems() ){
				g.drawImage(new ImageIcon("obj/"+item.getImageName()).getImage(), item.getXPos(), item.getYPos(), null);
			}
		}
		g.drawImage(new ImageIcon(rPackage.getFrontWall().getImageName() ).getImage(), 0, 0, null);
		for(Item item : rPackage.getFrontWall().getItems() ){
			g.drawImage(new ImageIcon("obj/"+item.getImageName()).getImage(), item.getXPos(), item.getYPos(), null);
		}
		
	}
}
