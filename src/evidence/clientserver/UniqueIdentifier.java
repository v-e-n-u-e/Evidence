package evidence.clientserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueIdentifier {
	
	private static List<Integer> ids = new ArrayList<Integer>();
	private static final int RANGE = 10000;
	
	private static int index = 0;
	
	private UniqueIdentifier(){
		
	}
	
	static {
		for(int i = 0; i < RANGE; i++){
			ids.add(i);
		}
		Collections.shuffle(ids);
	}
	
	public static int getIdentifier(){
		if(index > ids.size() - 1){
			index = 0;
		}
		// Post increment is used here, returns index then increments index
		return ids.get(index++);
	}

}
