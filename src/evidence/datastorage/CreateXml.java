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
 * Copies and writes the current version of the Game class to an XML file. This includes all the Items,
 * Rooms,Walls,Doors.
 * 
 * @author Connor
 *
 */
public class CreateXml {
	
	public void CreateGame(String FileName,Game game) throws Exception{
		/*To create a new XMl make sure to remove the Set methods from wall.*/
		//Game game = new Game();
		//game.setup();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			File file = new File(FileName);
			//formats and writes to the file
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//prints to file
			jaxbMarshaller.marshal(game,file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
