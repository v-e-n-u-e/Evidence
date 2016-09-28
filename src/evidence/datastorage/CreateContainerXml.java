package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sun.javafx.collections.MappingChange.Map;

import evidence.gameworld.actions.Action;
import evidence.gameworld.actions.Unlock;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.TestItem;
/**
 * 
 * 
 * @author Connor
 *
 */
public class CreateContainerXml {

	public static void main(String[] args){
		
		List<Action> actions = new ArrayList<Action>();
		actions.add(new Unlock());
		HashMap<String,String> images = new HashMap<String,String>();
		images.put("hammer.png", "picture of a hammer");
		TestItem safe = new TestItem("safe", "Metal Safe",null,images, 3);
		safe.setXPos(20);
		safe.setYPos(30);
		safe.setCurrentImage("hammer.png");
				
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(safe.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			File file = new File("testFile.xml");
			//formats and writes to the file
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//prints to file
			jaxbMarshaller.marshal(safe,file);
			//prints to the console
			jaxbMarshaller.marshal(safe, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}