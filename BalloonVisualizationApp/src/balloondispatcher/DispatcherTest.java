package balloondispatcher;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import eventlogger.Class1;
import eventlogger.Class2;
import eventlogger.Class3;
import eventlogger.LogEntry;

public class DispatcherTest {

	public static void main(String[] args) throws InterruptedException {

		ArrayList<LogEntry> log = new ArrayList<LogEntry>();
		
		Class1 c1 = new Class1();
		log.add(new LogEntry(c1, "creation"));

		Class2 c2 = new Class2();
		log.add(new LogEntry(c2, "creation"));
		
		TimeUnit.SECONDS.sleep(2);
		
		Class3 c3 = new Class3();
		log.add(new LogEntry(c3, "creation"));
		log.add(new LogEntry(c3, "destruction"));

		TimeUnit.SECONDS.sleep(2);

		log.add(new LogEntry(c1, "destruction"));

		TimeUnit.SECONDS.sleep(2);

		log.add(new LogEntry(c2, "destruction"));
		
		Dispatcher dispatcher = new Dispatcher(log);
		dispatcher.run();
		
	}	
	
	
}
