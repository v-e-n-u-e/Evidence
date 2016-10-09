package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import evidence.gameworld.Game;
import evidence.gameworld.Player;
import evidence.gameworld.Room;
import evidence.gameworld.Wall;
import evidence.gameworld.Room.Name;
import evidence.gameworld.items.Container;
import evidence.gameworld.items.Evidence;
/**
 * 
 * @author Connor
 *
 */
public class TestCreateXml {
	
	public static void main(String args[]){
		Game game = new Game();
		game.setup();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			File file = new File("SavedGamed.xml");
			//formats and writes to the file
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//prints to file
			jaxbMarshaller.marshal(game,file);
			//prints to the console
			jaxbMarshaller.marshal(game, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
