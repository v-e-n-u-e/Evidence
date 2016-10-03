package evidence.datastorage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import evidence.gameworld.actions.Action;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.TestItem;

/**
 * This Class reads Information about the current state of the Game, Players and
 * items from an xml file. 
 * 
 * @author Connor
 *
 */
public class ReadContainerXml {

	public static void main(String[] args){
		
		try{
			File file = new File("SavedGamed.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Container.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Container safe = (Container) jaxbUnmarshaller.unmarshal(file);
			Container container = new Container(safe.getName(),safe.getDescription(),null,safe.getImages(),3);
			container.setXPos(safe.getXPos());
			container.setYPos(safe.getYPos());
			System.out.println("X Position: " + container.getXPos());
			System.out.println("Y Position: " + container.getYPos());
			System.out.println("Name: " + container.getName());
			System.out.println("Description: " + container.getDescription());
			System.out.println("description of image: " +safe.getImages().get(0));
			System.out.println("Capacity: " + safe.getCapacity());
			
		}catch (JAXBException e) {
		 e.printStackTrace();	
		}
			
		}
}
