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
		if(e.getSource().equals(ClientWindow.retButtons()[0])){
			performButton(0);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1])){
			performButton(1);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2])){
			performButton(2);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[3])){
			performButton(3);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[4])){
			performButton(4);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[5])){
			performButton(5);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[6])){
			performButton(6);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[7])){
			performButton(7);
		}
		if(e.getSource().equals(ClientWindow.retButtons()[8])){
			performButton(8);
		}
	}
	
	
	/**
	 * This method is used to cleanly turn all background of icons light gray, so that you can't highlight more than one button 
	 * at a time. 
	 */
	public void resetSelected(){
		for(int x =0; x<9; x++){
			
				ClientWindow.retButtons()[x].setBackground(Color.LIGHT_GRAY);
			
		}
	}
	
	
	/**
	 * This is used just to clean up the actionPerformed method. Better to have a method here than copy and paste into each button
	 * @param i - the position in the grid you've selected. From left-right, top-bottom, 0-8
	 */
	public void performButton(int i){
		if(ClientWindow.retButtons()[i].getIcon()!=null){
			resetSelected();
			ClientWindow.retButtons()[i].setBackground(Color.BLACK);
			ClientWindow.currentlySelected=ClientWindow.rPackage.getInventory().get(i);
			System.out.println(ClientWindow.currentlySelected);
		}
		else{
			ClientWindow.currentlySelected=null;
			System.out.println(ClientWindow.currentlySelected);
		}
	}

}
