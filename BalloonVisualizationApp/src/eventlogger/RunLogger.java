package eventlogger;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;

import balloonanimator.Background;
import balloonanimator.Balloon;
import balloonanimator.Balloons;
import balloonanimator.StickFigures;



public class RunLogger {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		final long startTime = System.currentTimeMillis();

		final ReferenceQueue<Object> refQ = new ReferenceQueue<Object>(); // used to check if objects have been GCd

		final ArrayList<LogEntry> log = new ArrayList<LogEntry>(); // 
		final phantomRefHashMap<Integer, Integer> pRefToObj = new phantomRefHashMap<Integer, Integer>(); //this maps phantomRef hashcodes to their object hashcodes
		final ArrayList<Object> objQC1 = new ArrayList<Object>(); // objects that are still referenced
		final ArrayList<Object> objQC2 = new ArrayList<Object>(); // objects that are still referenced
		final ArrayList<Object> objQC3 = new ArrayList<Object>(); // objects that are still referenced
		final ArrayList<PhantomReference> phantomRefs = new ArrayList<PhantomReference>();

		final int creationRate = 5;
		final int deleteRate = 5;

		// Random object creation thread
		new Thread(new Runnable() {
			public void run() {
				Random rand = new Random();
				int classType = rand.nextInt(3);
				int i = 0;

				while(i < 40) {

					try {
						Thread.sleep(creationRate);
					} catch (InterruptedException e1) {
					}

					classType = rand.nextInt(3);

					try {

						switch(classType) {

						case 0:
							Class1 c1 = new Class1();
							PhantomReference<Class1> pr1 = new PhantomReference<Class1>(c1, refQ);
							phantomRefs.add(pr1);
							pRefToObj.putHashCodes(pr1, c1);
							log.add(new LogEntry(c1, "creation", startTime));
							objQC1.add(c1);
							c1 =null; 
							break;

						case 1:
							Class2 c2 = new Class2();
							PhantomReference<Class2> pr2 = new PhantomReference<Class2>(c2, refQ);
							phantomRefs.add(pr2);
							pRefToObj.putHashCodes(pr2, c2);
							log.add(new LogEntry(c2, "creation", startTime));
							objQC2.add(c2);
							c2 =null; 
							break;

						case 2:
							Class3 c3 = new Class3();
							PhantomReference<Class3> pr3 = new PhantomReference<Class3>(c3, refQ);
							phantomRefs.add(pr3);
							pRefToObj.putHashCodes(pr3, c3);
							log.add(new LogEntry(c3, "creation", startTime));
							objQC3.add(c3);
							c3 =null; 
							break;
						}
					}
					catch (OutOfMemoryError e) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e1) {
						}
					}
					i++;
				}

				BalloonInfo[] bI= logEntryArrayToBalloonInfoArray(log);

				// This was for testing
				System.out.println(bI.length);				
				int a = 0;
				int b = 0;
				int c = 0;
				int d = 0;
				int e = 0;
				int f = 0;
				
				for(int j=0; j < (bI.length - 1); j++) {
					if((bI[j] != null) && (bI[j].releaseTime != -1)) {
					System.out.println(bI[j].objectType + " " + bI[j].creationTime + " " + bI[j].releaseTime + "         " + (bI[j].releaseTime - bI[j].creationTime));
					switch(bI[j].objectType) {
					
					case "Class1":
						d = d + (bI[j].releaseTime - bI[j].creationTime);
						a++;
						break;
					case "Class2":
						e = e + (bI[j].releaseTime - bI[j].creationTime);
						b++;
						break;
					case "Class3":
						f = f + (bI[j].releaseTime - bI[j].creationTime);
						c++;
						break;
					}
					}
					else {
						//System.out.println("NULL");
					}
				}
				System.out.println("CLASS1 avg: " + d/a);
				System.out.println("CLASS2 avg: " + e/b);
				System.out.println("CLASS3 avg: " + f/c);

				// Creates the frame
				JFrame frame = new JFrame("Rising Balloon");
				Background bg = new Background();
				Balloons balls = new Balloons(bI);
				frame.setSize(800, 800);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				//balls are added to the frame
				frame.add(balls);
				bg.add(balls);

				// Draws balloons
				new Thread(bg).start();


			}
		}).start();

// Class1 Object deletion thread
		new Thread(new Runnable() {
			public void run() {
				Object ob;
				int objQSize;

				try {
					Thread.sleep(3);
				} catch (InterruptedException e1) {
				}

				while(true) {
					objQSize = objQC1.size();

					try {
						Thread.sleep(50);
					} catch (InterruptedException e1) {
					}

					if(objQSize > 0) {

						ob = objQC1.remove(0);
						ob = null;
					}
				}
			} }).start();
		////////////////////////////////////////////////
		// Class2 Object deletion thread
		new Thread(new Runnable() {
			public void run() {
				Object ob;
				int objQSize;

				try {
					Thread.sleep(3);
				} catch (InterruptedException e1) {
				}

				while(true) {
					objQSize = objQC2.size();

					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
					}

					if(objQSize > 0) {

						ob = objQC2.remove(0);
						ob = null;
					}
				}
			} }).start();
		////////////////////////////////////////////////////////////
		// Class3 Object deletion thread
		new Thread(new Runnable() {
			public void run() {
				Object ob;
				int objQSize;

				try {
					Thread.sleep(3);
				} catch (InterruptedException e1) {
				}

				while(true) {
					objQSize = objQC3.size();

					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
					}

					if(objQSize > 0) {

						ob = objQC3.remove(0);
						ob = null;
					}
				}
			} }).start();
		////////////////////////////////////////////////////////////
		// Garbage Collection checking thread
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


// Balloon Information Extractor component
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
				balloonInfoArray[indexFromHM].releaseTime = (int) currLogEntry.eventTime;
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