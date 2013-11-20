package eventlogger;

public class BalloonInfo {
	
	public String objectType;
	public long creationTime;
	public long releaseTime;
	
	public BalloonInfo(String className, long t1, long t2) {
		objectType = className;
		creationTime = t1;
		releaseTime = t2;
	}
	
}
