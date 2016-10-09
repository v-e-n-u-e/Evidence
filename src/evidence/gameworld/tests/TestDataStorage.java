package evidence.gameworld.tests;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import evidence.gameworld.Game;

public class TestDataStorage {
	
	
	@Test
	/**
	 * This test first initializes a game the same way it is created and then also reads in a seperate game
	 * from an XML file. It then checks to see that both games hold the same rooms.It also checks that if a
	 * wall has items on it, It will check that each item at index 0 matches.
	 */
	public void ReadInFromFile() throws Exception{
		Game initialGame = new Game();
		initialGame.setup();
		Game readIn = new Game();
		readIn.ReadFromXml("SavedGamed.xml");
		
		for(int i = 0; i<initialGame.getRoom().size();i++){
			assertEquals(initialGame.getRoom().get(i).getName(),readIn.getRoom().get(i).getName());
			if(!initialGame.getRoom().get(i).getWalls()[0].getItems().isEmpty()){
				assertEquals(initialGame.getRoom().get(i).getWalls()[0].getItems().get(0).getName(),readIn.getRoom().get(i).getWalls()[0].getItems().get(0).getName());
			}
		}
	}
	
	@Test
	/**
	 * This test makes sure that an error is thrown when the wrong file is read in.
	 */
	public void WrongFileName(){
		Game initialGame = new Game();
		initialGame.setup();
		Game readIn = new Game();
		try{
			
		readIn.ReadFromXml("Saved444asdgGamed.xml");
		fail();
		}catch(Exception e){
			assertEquals(1,1);
		}
	}
}
