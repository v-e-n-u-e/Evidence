package evidence.gameworld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import evidence.clientserver.Server;

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
@XmlRootElement
public class Timer{
	Thread counter;
	Server server;
	int seconds;
	
	/**
	 * A constructor for a timer
	 * 
	 * @param seconds - the number of seconds to count down
	 */
	public Timer(int seconds, Server server){
		this.seconds = seconds;
		this.server = server;
		counter = new Thread("Counter") {
			public void run(){
				countdown();
			}
		};
		counter.start();
		
	}
	public Timer(){
		
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
			String time = toString();
			server.getGUI().writeToLog(time);
			server.sendToAll("/timer/" + time + "/e/");
			//System.out.println(this.toString());  //send timer here
			
			
		}
		
		server.timeEnd();
		
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
	 * "Time Left: 00 minutes, 00 seconds"
	 */
	public String toString(){
		int secs = seconds;
		String time = "Time Left: ";
		int minutes = 0;
		while(secs >= 60){
			minutes ++;
			secs -= 60;
		}
		time = time + minutes + " minutes, ";
		time = time + secs + " seconds";
		
		return time;
	}
	
	@XmlElement
	public int getSeconds(){
		return this.seconds;
	}
	
	@XmlElement
	public Server getServer(){
		return this.server;
	}
	
	@XmlElement
	public Thread getCounter(){
		return this.counter;
	}
	
	public void setCounter(Thread c){
		this.counter = c;
	}
}
