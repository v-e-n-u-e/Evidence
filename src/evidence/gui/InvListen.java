package evidence.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InvListen implements ActionListener{

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(ClientWindow.retButtons()[0][0])){
			System.out.println("0,0");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[0][1])){
			System.out.println("0,1");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[0][2])){
			System.out.println("0,2");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][0])){
			System.out.println("1,0");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][1])){
			System.out.println("1,1");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[1][2])){
			System.out.println("1,2");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][0])){
			System.out.println("2,0");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][1])){
			System.out.println("2,1");
		}
		if(e.getSource().equals(ClientWindow.retButtons()[2][2])){
			System.out.println("2,2");
		}
	}
	

}
