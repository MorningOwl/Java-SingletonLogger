package Logger;

public class SingletonLoggerTest {
	

	public static void main(String[] args) {			
	    RunnableDemo R1 = new RunnableDemo( "Thread-1");
	    R1.start();
	    
	    RunnableDemo R2 = new RunnableDemo( "Thread-2");
	    R2.start(); 
	}
}


class RunnableDemo implements Runnable {
	   private Thread t;
	   private String threadName;
	   SingletonLogger log = SingletonLogger.getInstance();
	   
	   RunnableDemo( String name) {
	      threadName = name;
	      log.logInFile("Creating " +  threadName );
	   }
	   
	   public void run() {
		  log.logInFile("Running " +  threadName );
	      try {
	         for(int i = 4; i > 0; i--) {
	        	 log.logInFile("Thread: " + threadName + ", " + i);
	            Thread.sleep(50);
	         }
	      }catch (InterruptedException e) {
	    	  log.logInFile("Thread " +  threadName + " interrupted.");
	      }
	      log.logInFile("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start () {
		   log.logInFile("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	}