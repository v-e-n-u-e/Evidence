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
		g.drawImage(new ImageIcon(rPackage.getFrontWall().getImageName() ).getImage(), 0, 0, null);
		for(Item item : rPackage.getFrontWall().getItems() ){
			g.drawImage(new ImageIcon(item.getImageName() ).getImage(), item.getXPos(), item.getYPos(), null);
		}
		
	}
}
