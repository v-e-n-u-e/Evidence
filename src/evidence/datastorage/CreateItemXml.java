package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import evidence.gameworld.Room;
import evidence.gameworld.Room.Name;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;

public class CreateItemXml {

	public static void main(String[] args){
		Room bathroom = new Room(Name.BATHROOM, "img/bathroom.png", "img/bathroom.png", "img/bathroom.png", "img/bathroom.png");
		Room bedroom =new Room(Name.BEDROOM, "img/bedroom.png", "img/bedroom.png", "img/bedroom.png", "img/bedroom.png");

		List<String> actions = new ArrayList<String>();

		actions.add("Enter");
		actions.add("Unlock");
		actions.add("Lock");
		Door door = new Door("Door", "Door between the bathroom and the kitchen", actions, bathroom,
				bedroom, true, 123);
		Door door2 = new Door("Doofsdsr", "Door between the bathroosdgsgdssm and the kitchen", actions, bathroom,
				bedroom, true, 123444);
		door.setCurrentImage("img/bigdoor.png");
		door.setXPos(30);
		door.setYPos(100);
		door2.setYPos(10000);
		door2.setXPos(322);
		
		/*Container container = new Container("box", "CardboardBox",actions,images,3);
		container.setXPos(20);
		container.setYPos(30);*/
		
				
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Door.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			File file = new File("SavedGamed.xml");
			//formats and writes to the file
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//prints to file
			jaxbMarshaller.marshal(door,file);
			//prints to the console
			jaxbMarshaller.marshal(door, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
}
