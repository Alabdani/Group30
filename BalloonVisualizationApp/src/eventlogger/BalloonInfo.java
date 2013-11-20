package eventlogger;

public class BalloonInfo {
	
	public String objectType;
	public int creationTime;
	public int releaseTime;
	
	public BalloonInfo(String className, long t1, long t2) {
		objectType = className;
		creationTime = (int) t1;
		releaseTime = (int) t2;
	}
	
}
