package evidence.clientserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The UniqueIdentifier class is responsible for returning a globally
 * unique Identifier, in the for form of an Integer, for our server
 * to assign to the different Client's that connect to it.  
 * 
 * The RANGE variable effectively serves as the maximum number of
 * users that may connect to the server at any given time.  For the purposes
 * of our game, we will only ever be connecting 1, 2 or 3 people at a time.  But
 * setting it to 10,000 does no harm.
 * 
 * @author Tyler Jones
 */
public class UniqueIdentifier {
	
	// An ArrayList of integers, when this class is first accessed, 
	// the method simply labelled static below is called.  Which fills
	// this array with numbers from 0 -> RANGE.
	private static List<Integer> ids = new ArrayList<Integer>();
	
	// The RANGE of Integers we would like to be able to assign to our Clients
	private static final int RANGE = 10000;
	
	// The index of where we currently are in the Array
	private static int index = 0;
	
	/**
	 * A private constructor for UniqueIdentifier, exists to make sure
	 * we don't accidentally make an instance of this class anywhere, as
	 * we want it do be static only
	 */
	private UniqueIdentifier(){
		
	}
	
	/**
	 * This method is simply called when this class is first accessed in anyway,
	 * in the context of our program, this is when the first client connects to the 
	 * server
	 */
	static {
		for(int i = 0; i < RANGE; i++){
			ids.add(i);
		}
		Collections.shuffle(ids);
	}
	
	/**
	 * Returns a Globally Unique Integer
	 * 
	 * @return - The globally Unique Integer
	 */
	public static int getIdentifier(){
		if(index > ids.size() - 1){
			index = 0;
		}
		// Post increment is used here, returns index then increments index
		return ids.get(index++);
	}

}
