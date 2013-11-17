package balloondispatcher;

import java.util.ArrayList;

import eventlogger.LogEntry;

public class Dispatcher {
	
	public ArrayList<LogEntry> sortedEvents;
	public long timeDiff;
	
	public Dispatcher (ArrayList<LogEntry> events) {
		sortedEvents = SortEvents(events);
		
	}
	
	public void run(){
		System.out.println("Starting run method " + sortedEvents.size());
		timeDiff = System.currentTimeMillis() - sortedEvents.get(0).eventTime;
		for(LogEntry e: sortedEvents){

			//simple loop for stalling until the event should be executed
			while(e.eventTime + timeDiff > System.currentTimeMillis()){		
			}
			if(e.eventType.equals("creation")){
				System.out.println("Creating object: " + e.objectID + " - " + e.objectType);
			}
			//assuming there arn't any other types than construction and destruction
			else {
				System.out.println("Destroying object: " + e.objectID + " - " + e.objectType);
			}
			
			
		}
	}
	
	private ArrayList<LogEntry> SortEvents (ArrayList<LogEntry> unsortedEvents) {
		
		sortedEvents = new ArrayList<LogEntry>();
		
		// some pretty ugly sorting (but simple)
		while(unsortedEvents.size() > 0){

			LogEntry smallest = unsortedEvents.get(0);
			//find smallest element
			for(LogEntry e: unsortedEvents){
				if(e.eventTime < smallest.eventTime){
					smallest = e;
				}
			}
			sortedEvents.add(smallest);
			unsortedEvents.remove(smallest);
		}		
		return sortedEvents;
	}

}
