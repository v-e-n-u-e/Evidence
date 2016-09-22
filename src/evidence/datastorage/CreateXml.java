package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
/**
 * 
 * 
 * @author Connor
 *
 */
public class CreateXml {

	public static void main(String[] args){
		
		TestClass test = new TestClass();
		test.setAge(56);
		test.setId(420);
		test.setName("a");
		
		TestClass test2 = new TestClass();
		test2.setAge(33);
		test2.setId(1);
		test2.setName("tyler likes gurlz");
		
		List<TestClass> listTest = new ArrayList<TestClass>();
		listTest.add(test);
		listTest.add(test2);
		
		TestHolder list = new TestHolder();
		list.setListTestClass(listTest);
				
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(list.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			File file = new File("testFile.xml");
			//formats and writes to the file
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//prints to file
			jaxbMarshaller.marshal(list,file);
			//prints to the console
			jaxbMarshaller.marshal(list, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
