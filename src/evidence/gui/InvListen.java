package evidence.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InvListen implements ActionListener{

	
	/**
	 * Formula for getting position of the item in the players inventory as compared to the grid is 3y+x
	 * e.g. the item in grid pos [1][1] relates to position 4 in the inventory array.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ClientWindow.retButtons()[0][0])){
			resetSelected();
			ClientWindow.retButtons()[0][0].setBackground(Color.BLACK);
			System.out.println("0,0");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[0][1])){
			resetSelected();
			ClientWindow.retButtons()[0][1].setBackground(Color.BLACK);
			System.out.println("0,1");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[0][2])){
			resetSelected();
			ClientWindow.retButtons()[0][2].setBackground(Color.BLACK);
			System.out.println("0,2");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][0])){
			resetSelected();
			ClientWindow.retButtons()[1][0].setBackground(Color.BLACK);
			System.out.println("1,0");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][1])){
			resetSelected();
			ClientWindow.retButtons()[1][1].setBackground(Color.BLACK);
			System.out.println("1,1");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][2])){
			resetSelected();
			ClientWindow.retButtons()[1][2].setBackground(Color.BLACK);
			System.out.println("1,2");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][0])){
			resetSelected();
			ClientWindow.retButtons()[2][0].setBackground(Color.BLACK);
			System.out.println("2,0");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][1])){
			resetSelected();
			ClientWindow.retButtons()[2][1].setBackground(Color.BLACK);
			System.out.println("2,1");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][2])){
			resetSelected();
			ClientWindow.retButtons()[2][2].setBackground(Color.BLACK);
			System.out.println("2,2");
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
	

}
