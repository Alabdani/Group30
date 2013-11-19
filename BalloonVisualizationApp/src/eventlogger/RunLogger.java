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
	
		final ReferenceQueue<Object> refQ = new ReferenceQueue<Object>(); // used to check if objects have been GCd

		final ArrayList<LogEntry> log = new ArrayList<LogEntry>(); // this is the eventual output
		final phantomRefHashMap<Integer, Integer> pRefToObj = new phantomRefHashMap<Integer, Integer>(); //this maps phantomRef hashcodes to their object hashcodes
		final ArrayList<Object> objQ = new ArrayList<Object>(); // objects that are still referenced
		final ArrayList<PhantomReference> phantomRefs = new ArrayList<PhantomReference>();
		
		
		new Thread(new Runnable() {
			public void run() {
				Random rand = new Random();
				int classType = rand.nextInt(3);
				int i = 0;
				
				while(i < 50) {
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
					}
					
				classType = rand.nextInt(3);

				switch(classType) {
				
				case 0:
				Class1 c1 = new Class1();
				PhantomReference<Class1> pr1 = new PhantomReference<Class1>(c1, refQ);
				phantomRefs.add(pr1);
				pRefToObj.putHashCodes(pr1, c1);
				log.add(new LogEntry(c1, "creation"));
				objQ.add(c1);
				c1 =null; 
				break;
				
				case 1:
				Class2 c2 = new Class2();
				PhantomReference pr2 = new PhantomReference(c2, refQ);
				pRefToObj.putHashCodes(pr2, c2);
				log.add(new LogEntry(c2, "creation"));
				objQ.add(c2);
				break;
				
				case 2:
				Class3 c3 = new Class3();
				PhantomReference pr3 = new PhantomReference(c3, refQ);
				pRefToObj.putHashCodes(pr3, c3);
				log.add(new LogEntry(c3, "creation"));
				objQ.add(c3);
				break;
				}
				i++;
				}
				
				new Thread(new Runnable() {
					public void run() {
						for(int i=0; i < (log.size() - 1); i++) {
							log.get(i).printLogEntry();
						}
				}
				}).start();
				
			}
		}).start();
		
		
		new Thread(new Runnable() {
			public void run() {
				Object ob;
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
				}
				
				while(true) {
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
					}
					
					if(objQ.size() > 0) {
				ob = objQ.remove(0);
				//System.out.println("object rmoved: " + ob.hashCode());
				ob = null;
				//System.out.println("# OF ALIVE OBJEEECTS ++++++++++++: " + objQ.size());
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
						log.add(new LogEntry(hashCode, className, "GC"));
						
					} catch (InterruptedException e) {
					}
				}
			} }).start();
		}	
	}