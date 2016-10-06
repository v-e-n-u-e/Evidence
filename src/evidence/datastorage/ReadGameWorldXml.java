package evidence.datastorage;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import evidence.gameworld.Room;
import evidence.gameworld.Wall;
import evidence.gameworld.items.Door;

public class ReadGameWorldXml {
	
public static void main(String[] args){
		
		try{
			File file = new File("SavedGamed.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Room.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Room room = (Room) jaxbUnmarshaller.unmarshal(file);
			System.out.println(room.getName().toString());
			System.out.println(room.getWalls()[0].getItems().get(0));
			System.out.println(room.getWalls()[0].getDirection().toString());
			System.out.println(room.getWalls()[0].getX());
			
			
			
		}catch (JAXBException e) {
		 e.printStackTrace();	
		}
			
		}

}
