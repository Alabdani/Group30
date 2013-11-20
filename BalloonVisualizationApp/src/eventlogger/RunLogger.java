package eventlogger;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;



public class RunLogger {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		final long startTime = System.currentTimeMillis();
	
		final ReferenceQueue<Object> refQ = new ReferenceQueue<Object>(); // used to check if objects have been GCd

		final ArrayList<LogEntry> log = new ArrayList<LogEntry>(); // this is the eventual output
		final phantomRefHashMap<Integer, Integer> pRefToObj = new phantomRefHashMap<Integer, Integer>(); //this maps phantomRef hashcodes to their object hashcodes
		final ArrayList<Object> objQ = new ArrayList<Object>(); // objects that are still referenced
		final ArrayList<PhantomReference> phantomRefs = new ArrayList<PhantomReference>();
		
		final int creationRate = 1;
		final int deleteRate = 1;
		
		new Thread(new Runnable() {
			public void run() {
				Random rand = new Random();
				int classType = rand.nextInt(3);
				int i = 0;
				
				while(i < 50) {
					
					try {
						Thread.sleep(creationRate);
					} catch (InterruptedException e1) {
					}
					
				classType = rand.nextInt(3);

				switch(classType) {
				
				case 0:
				Class1 c1 = new Class1();
				PhantomReference<Class1> pr1 = new PhantomReference<Class1>(c1, refQ);
				phantomRefs.add(pr1);
				pRefToObj.putHashCodes(pr1, c1);
				log.add(new LogEntry(c1, "creation", startTime));
				objQ.add(c1);
				c1 =null; 
				break;
				
				case 1:
				Class2 c2 = new Class2();
				PhantomReference<Class2> pr2 = new PhantomReference<Class2>(c2, refQ);
				phantomRefs.add(pr2);
				pRefToObj.putHashCodes(pr2, c2);
				log.add(new LogEntry(c2, "creation", startTime));
				objQ.add(c2);
				c2 =null; 
				break;
				
				case 2:
				Class3 c3 = new Class3();
				PhantomReference<Class3> pr3 = new PhantomReference<Class3>(c3, refQ);
				phantomRefs.add(pr3);
				pRefToObj.putHashCodes(pr3, c3);
				log.add(new LogEntry(c3, "creation", startTime));
				objQ.add(c3);
				c3 =null; 
					break;
				}
				i++;
				}
				
				BalloonInfo[] bI= logEntryArrayToBalloonInfoArray(log);
				
			//	System.out.println(log.size());				
				
				for(int j=0; j < (bI.length - 1); j++) {
					if(bI[j] != null) {
					System.out.println(bI[j].objectType + " " + bI[j].creationTime + " " + bI[j].releaseTime + "         " + j);
					}
					else {
						//System.out.println("NULL");
					}
				}
				
			}
		}).start();
		
		
		new Thread(new Runnable() {
			public void run() {
				Object ob;
				int objQSize;
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
				}
				
				while(true) {
					objQSize = objQ.size();
					
					try {
						Thread.sleep(deleteRate);
					} catch (InterruptedException e1) {
					}
					
					if(objQSize > 0) {
						
						Random rand = new Random();
						int randomObjectToRemove = rand.nextInt(objQ.size());
						
				ob = objQ.remove(randomObjectToRemove);
				ob = null;
					}
				}
			} }).start();
	
		
		new Thread(new Runnable() {
			public void run() {
				Object removedOb;

				while(true) {
					try {
						removedOb = refQ.remove();
						int removedObHashCode = removedOb.hashCode();
						int hashCode = pRefToObj.getHashCode(removedObHashCode);
						String className = pRefToObj.getClassName(removedObHashCode);
						
						phantomRefs.remove(removedOb);
						log.add(new LogEntry(hashCode, className, "GC", startTime));
						
					} catch (InterruptedException e) {
					}
				}
			} }).start();
		}
	
	
	
	static BalloonInfo[] logEntryArrayToBalloonInfoArray(ArrayList<LogEntry> log) {
		int logSize = log.size();
		HashMap<Integer, Integer> balloonInfoHM = new HashMap<Integer, Integer>();
		BalloonInfo[] balloonInfoArray = new BalloonInfo[logSize];
		LogEntry currLogEntry;
		int indexFromHM;
		int i = 0;
		int j = 0;
		
		while(i < logSize) {
			currLogEntry = log.get(i);

			if(balloonInfoHM.containsKey(currLogEntry.objectID)) {
				indexFromHM = balloonInfoHM.get(currLogEntry.objectID);
				balloonInfoArray[indexFromHM].releaseTime = currLogEntry.eventTime;
			}
			else {
				balloonInfoHM.put(currLogEntry.objectID, j);
				balloonInfoArray[j] = new BalloonInfo(currLogEntry.objectType, currLogEntry.eventTime, -1);
				j++;
			}
			i++;
		}
		return balloonInfoArray;
	}
	}