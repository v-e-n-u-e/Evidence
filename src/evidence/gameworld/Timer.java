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
	Thread counter;
	int seconds;
	
	/**
	 * A constructor for a timer
	 * 
	 * @param seconds - the number of seconds to count down
	 */
	public Timer(int seconds){
		this.seconds = seconds;
		counter = new Thread("Counter") {
			public void run(){
				countdown();
			}
		};
		counter.start();
		
	}

	/**
	 * 
	 * Counts down to zero from the specified number of seconds
	 * 
	 * @return true - when the time is up
	 * @return false - when an exception is encountered
	 * @throws InterruptedException
	 */
	public void countdown(){
		for(; seconds >= 0; seconds--){
			try {
				counter.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			System.out.println(this.toString());  //send timer here
			
			
		}
		
		System.out.println("Times up"); //make cops arrive here
		
	}
	
	/**
	 * Adds time to the counter
	 * 
	 * @param secs - number of seconds to be added to the time
	 */
	public void addTime(int secs){
		this.seconds += secs;
	}
	
	/**
	 * Removes time from the counter
	 * 
	 * @param secs - number of seconds to be removed from the time
	 */
	public void removeTime(int secs){
		this.seconds -= secs;
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
