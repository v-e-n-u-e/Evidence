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

/**
 * This Class reads Information about the current state of the Game, Players and
 * items from an xml file. 
 * 
 * @author Connor
 *
 */
public class ReadXml {

	public static void main(String[] args){
		
		try{
			File file = new File("testFile.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(TestHolder.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TestHolder test = (TestHolder) jaxbUnmarshaller.unmarshal(file);
			for(TestClass t : test.getListTestClass()){
				System.out.println(t.getName());
			}
		}catch (JAXBException e) {
		 e.printStackTrace();	
		}
			
		}
}
