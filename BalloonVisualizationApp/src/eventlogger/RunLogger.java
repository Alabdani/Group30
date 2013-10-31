package eventlogger;

import java.util.ArrayList;

public class RunLogger {
	
static void CreateLogEntry(Object[][] table, Object ob, String eventType) {
		
		int i = 0;
		
		while(table[i][0] != null) {
			i++;
		}
		i = i - 1;
		table[i][0] = ob.getClass();
		table[i][1] = ob.hashCode();
		table[i][2] = System.currentTimeMillis();
		table[i][3] = eventType;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<LogEntry> log = new ArrayList<LogEntry>();
		
		Class1 c1 = new Class1();
		log.add(new LogEntry(c1, "creation"));

		Class2 c2 = new Class2();
		log.add(new LogEntry(c2, "creation"));
		
		Class3 c3 = new Class3();
		log.add(new LogEntry(c3, "creation"));
		
		log.get(0).printLogEntry();
		log.get(1).printLogEntry();
		log.get(2).printLogEntry();
	}

}
