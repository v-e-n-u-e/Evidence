package evidence.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class RenderCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	
	ImageIcon img;
	Image image;
	ImageIcon img2;
	Image image2;
	
	public RenderCanvas(){
		super();
	}
	
	public void setImage(String file1, String file2){
		img = new ImageIcon(file1);
		image = img.getImage();
		img2 = new ImageIcon(file2);
		image2 = img2.getImage();
	}
	
	@Override
	public void paint(Graphics g){
		if(image == null){return;}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		g.drawImage(image, 0, 0, null);
		g.drawImage(image2, 200, 200, null);
	}
}
