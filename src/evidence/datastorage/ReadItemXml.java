package evidence.datastorage;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import evidence.gameworld.Room;
import evidence.gameworld.Wall;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Door;

public class ReadItemXml {

public static void main(String[] args){
		
		try{
			File file = new File("SavedGamed.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Door.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Door door = (Door) jaxbUnmarshaller.unmarshal(file);
			System.out.println(door.getDescription());
			
			
			
			
			
		}catch (JAXBException e) {
		 e.printStackTrace();	
		}
			
		}
	
}
