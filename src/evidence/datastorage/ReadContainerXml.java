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
			File file = new File("testFile.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(TestItem.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TestItem safe = (TestItem) jaxbUnmarshaller.unmarshal(file);
			System.out.println("X position " +safe.getXPos());
			System.out.println("Y position " +safe.getYPos());
			System.out.println("name " + safe.getName());
			System.out.println("description " + safe.getDescription());
			System.out.println(safe.getImages().get("hammer.png"));
			
			Container container = new Container(safe.getName(),safe.getDescription(),null,safe.getImages(),3);
			
		}catch (JAXBException e) {
		 e.printStackTrace();	
		}
			
		}
}
