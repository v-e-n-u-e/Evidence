package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import evidence.gameworld.Room;
import evidence.gameworld.Room.Name;
import evidence.gameworld.Wall;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;

public class CreateGameWorldXml {
	
	public static void main(String[] args){
		//Creates a room That has four walls, One of which contains a Container Item("box").
		//Container currently works
		//Wall currently works
		//ROOM WORKS!!
		
	Room bathroom = new Room(Name.BATHROOM, "img/bathroom.png", "img/bathroom.png", "img/bathroom.png", "img/bathroom.png");
	List<String> actions = new ArrayList<String>();
	List<String> images = new ArrayList<String>();
	actions.add("Enter");
	actions.add("Unlock");
	actions.add("Lock");
	images.add("painting.png");
	Container container = new Container("box", "CardboardBox",actions,images,false, 3);
	container.setXPos(20);
	container.setYPos(30);
	Wall[] walls = bathroom.getWalls();
	walls[0].addItem(container);
	bathroom.setWalls(walls);
	
	
			
	
	try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Wall.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		File file = new File("SavedGamed.xml");
		//formats and writes to the file
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//prints to file
		jaxbMarshaller.marshal(bathroom,file);
		//prints to the console
		jaxbMarshaller.marshal(bathroom, System.out);
	} catch (JAXBException e) {
		e.printStackTrace();
	}

	}
}
