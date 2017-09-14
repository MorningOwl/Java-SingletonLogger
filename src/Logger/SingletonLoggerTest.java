package Logger;

public class SingletonLoggerTest {

	public static void main(String[] args) {
		SingletonLogger log = SingletonLogger.getInstance();
		
		log.logInFile("test");
	}

}
