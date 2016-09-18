package evidence.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class RenderCanvas extends Canvas{
	
	ImageIcon img;
	Image image;
	
	public RenderCanvas(){
		super();

	}
	
	public void setImage(String file){
		img = new ImageIcon(file);
		image = img.getImage();
	}
	
	@Override
	public void paint(Graphics g){
		if(image == null){return;}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		g.drawImage(image, 0, 0, null);
	}
}
