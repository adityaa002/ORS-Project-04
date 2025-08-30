package in.co.rays.testModel;

import org.apache.log4j.Logger;

public class TestLog {
	public static void main(String[] args) {
		
		Logger log = Logger.getLogger(TestLog.class);
		
		log.debug("debug level");
		log.error("error level");
	

		try {
			
			
			
			int a = 0;
			int b = 10;
			int c = b / a;
		} catch (RuntimeException e) {
			log.error("exception occured");
		}

	}
}
