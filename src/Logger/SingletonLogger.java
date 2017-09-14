package Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingletonLogger {
	private static SingletonLogger instance;
	private BufferedWriter bwriter;
	private FileWriter fwriter;
	private File file;
	private String fileName;

	/**
	 * The constructor for SingletonLogger. Sets a fileName to be later
	 * used for logging.
	 */
	private SingletonLogger(){
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		fileName = "./outfile" + dateFormat.format(date)+ ".log";
	}
	
	/**
	 * This returns the SingletonLogger instance. A double-checked
	 * locking method implemented where it first checks if the instance was already 
	 * initiated. If so, it returns an instance. If not, it obtains a lock (important
	 * for multi-threading) and then checks again with the same condition. If it 
	 * was already created by another thread, it just simply returns the instance.
	 * @return A SingletonLogger instance of this class.
	 */
	public static SingletonLogger getInstance(){
		if(instance == null){
			synchronized(SingletonLogger.class){
				if(instance == null){
					instance = new SingletonLogger();
					System.out.println("First instance of SingletonLogger created.");
				}
			}
		}
		return instance;
	}
	
	/**
	 * Invokes the System.out.println method.
	 * @param obj
	 */
	public void println(Object obj){
		System.out.println(obj);
	}
	
	/**
	 * Invokes the System.out.print method.
	 * @param obj
	 */
	public void print(Object obj){
		System.out.print(obj);
	}
	
	public void logInFile(String s){
		bwriter = null;
		fwriter = null;
		
		try{
			synchronized(SingletonLogger.class){
				file = new File(fileName);						
				if (!file.exists()) {
					file.createNewFile();
				}

				fwriter = new FileWriter(file.getAbsoluteFile(), true);
				bwriter = new BufferedWriter(fwriter);
				bwriter.write(s);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bwriter != null)
					bwriter.close();
				if (fwriter != null)
					fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}
	}
}
