package evidence.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import evidence.testobjects.TestWall;

public class RenderCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	
	//Create images/imgicons for later use. Will clean later.
	ImageIcon img;
	Image image;
	ImageIcon img1;
	Image image1;
	ImageIcon img2;
	Image image2;
	ImageIcon img3;
	Image image3;
	ImageIcon img4;
	Image image4;
	
	TestWall wall;
	
	public RenderCanvas(){
		super();
	}
	
	//Create the imageIcons that we'll use to draw using the array list of file names we get passed
	//We'll use a for loop through this in the future, but for now we're hard coding it
	public void setImage(String[] images){
		/*for(int i =0; i<images.length;i++){
			
		}*/
		img = new ImageIcon(images[0]);
		image = img.getImage();
		img1 = new ImageIcon(images[1]);
		image1 = img1.getImage();
		img2 = new ImageIcon(images[2]);
		image2 = img2.getImage();
		img3 = new ImageIcon(images[3]);
		image3 = img3.getImage();
		img4 = new ImageIcon(images[4]);
		image4 = img4.getImage();
	}
	
	//This will draw the images to the canvas in order of the image arraylist created above
	//Again, in the future this will use a for loop but for now we have hard coded it
	@Override
	public void paint(Graphics g){
		/*if(image == null){return;}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		g.drawImage(image, 0, 0, null);
		g.drawImage(image1, 20, 300, null);
		g.drawImage(image2, 450, 130, null);
		g.drawImage(image3, 600, 400, null);
		g.drawImage(image4, 530, 450, null);*/
		
		g.drawImage(new ImageIcon(wall.background).getImage(), 0, 0, null);
		
	}
}
