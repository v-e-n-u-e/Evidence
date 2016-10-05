package evidence.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class InvListen implements ActionListener{

	
	/**
	 * Formula for getting position of the item in the players inventory as compared to the grid is 3y+x
	 * e.g. the item in grid pos [1][1] relates to position 4 in the inventory array.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ClientWindow.retButtons()[0][0])){
			performButton(ClientWindow.retButtons()[0][0]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[0][1])){
			performButton(ClientWindow.retButtons()[0][1]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[0][2])){
			performButton(ClientWindow.retButtons()[0][2]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][0])){
			performButton(ClientWindow.retButtons()[1][0]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][1])){
			performButton(ClientWindow.retButtons()[1][1]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][2])){
			performButton(ClientWindow.retButtons()[1][2]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][0])){
			performButton(ClientWindow.retButtons()[2][0]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][1])){
			performButton(ClientWindow.retButtons()[2][1]);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][2])){
			performButton(ClientWindow.retButtons()[2][2]);
		}
	}
	
	
	/**
	 * This method is used to cleanly turn all background of icons light gray, so that you can't highlight more than one button 
	 * at a time. 
	 */
	public void resetSelected(){
		for(int x =0; x<3; x++){
			for(int y = 0; y<3; y++){
				ClientWindow.retButtons()[x][y].setBackground(Color.LIGHT_GRAY);
			}
		}
	}
	
	
	/**
	 * This is used just to clean up the actionPerformed method. Better to have a method here than copy and paste into each button
	 * @param i - the position in the grid you've selected. From left-right, top-bottom, 0-8
	 */
	public void performButton(JButton b){
		if(b.getIcon()!=null){
			resetSelected();
			b.setBackground(Color.BLACK);
			System.out.println(b.getIcon().toString());
		}
		else{
			System.out.println("Empty hand");
		}
	}

}
