package evidence.gameworld;

/**
 * Timer class is responsible for counting down to zero from the specified
 * number of seconds. True is returned when the time is up
 * 
 * The toString method returns a String in the following format:
 * "Time Remaining: 00 minutes, 00 seconds"
 * 
 * @author Georgina Murphy
 *
 */
public class Timer{
	Thread thread;
	int seconds;
	
	/**
	 * A constructor for a timer
	 * 
	 * @param seconds - the number of seconds to count down
	 */
	public Timer(int seconds){
		thread = new Thread();
		this.seconds = seconds;
	}

	/**
	 * 
	 * Counts down to zero from the specified number of seconds
	 * 
	 * @return true - when the time is up
	 * @return false - when an exception is encountered
	 * @throws InterruptedException
	 */
	public boolean countdown(){
		for(; seconds >= 0; seconds--){
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
			System.out.println(this.toString());  //send timer here
		}
		return true;
	}
	
	/**
	 * String is returned in the following format:
	 * "Time Remaining: 00 minutes, 00 seconds"
	 */
	public String toString(){
		int secs = seconds;
		String time = "Time Remaining: ";
		int minutes = 0;
		while(secs >= 60){
			minutes ++;
			secs -= 60;
		}
		time = time + minutes + " minutes, ";
		time = time + secs + " seconds";
		
		return time;
	}
}
