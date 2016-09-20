package evidence.datastorage;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This Class reads Information about the current state of the game, characters and
 * items from an xml file. 
 * 
 * @author Connor
 *
 */
public class ReadXml {

	
	public void testReadFile() throws ParserConfigurationException, IOException, SAXException{
		InputStream inputStream = (InputStream) getClass().getClassLoader().getResourceAsStream("SavedGame.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream);
		
		NodeList children = document.getChildNodes();
		Element firstThing = (Element)children.item(0);
		
		
		
	}
}
