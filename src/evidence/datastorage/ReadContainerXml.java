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
import evidence.gameworld.items.Key;

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
			JAXBContext jaxbContext = JAXBContext.newInstance(Key.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Key safe = (Key) jaxbUnmarshaller.unmarshal(file);
			Key container = new Key(safe.getName(),safe.getDescription(),safe.getActions(),safe.getImages(),safe.getSize(),safe.getCode());
			System.out.println("X Position: " + container.getXPos());
			System.out.println("Y Position: " + container.getYPos());
			System.out.println("Name: " + container.getName());
			System.out.println("Description: " + container.getDescription());
			System.out.println("description of image: " +safe.getImages().get(0));
			System.out.println("size: " + container.getSize());
			System.out.println("code: " + container.getCode());
			for(String a : container.getActions()){
				System.out.println("this action does this: "+container.getAction(a).getDescription());
			}
			
		}catch (JAXBException e) {
		 e.printStackTrace();	
		}
			
		}
}
