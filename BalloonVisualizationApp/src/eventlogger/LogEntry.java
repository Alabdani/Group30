package eventlogger;

public class LogEntry {
	public String objectType;
	public int objectID;
	public long eventTime;
	public String eventType;
	
	public LogEntry(Object a, String t) {
		objectType = parseClassName(a.toString());
		objectID = a.hashCode();
		eventTime = System.currentTimeMillis();
		eventType = t;
	}
	
	private String parseClassName(String s) {
		return s.substring(lastPeriod(s), lastAtSymbol(s));
	}

	private int lastPeriod(String s) {
		return s.lastIndexOf(".") + 1;
	}
	
	private int lastAtSymbol(String s) {
		return s.lastIndexOf("@");
	}
	
	public void printLogEntry() {
		System.out.println("objectType: " + objectType + "\nobjectID: " + objectID + "\neventTime: " + eventTime + "\neventType: " + eventType + "\n");
	}
}
