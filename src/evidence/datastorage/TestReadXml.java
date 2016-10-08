package evidence.datastorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import evidence.gameworld.Game;
import evidence.gameworld.Player;
import evidence.gameworld.Room;
import evidence.gameworld.Wall;
/**
 * 
 * @author Connor
 *
 */
public class TestReadXml {

public static void main(String[] args){
	//Reading in rooms array from game works, havnt tried players.
		
		try{
			File file = new File("SavedGamed.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Game game = (Game) jaxbUnmarshaller.unmarshal(file);
			List<Player> players = new ArrayList<Player>();
			List<Room> rooms = new ArrayList<Room>();
			players = game.getPlayers();
			rooms = game.getRoom();
			for(Room r : rooms){
				System.out.println(r.getName().toString());
				Wall[] wall = r.getWalls();
				for(int i=0;i<4;i++){
					System.out.println(wall[i].getDirection().toString());
					if(wall[i].getItems().size() != 0){
						System.out.println(wall[i].getItems().get(0).getDescription());
					}
				}
			}
			
		}catch (JAXBException e) {
		 e.printStackTrace();	
		}
			
		}
	
}
