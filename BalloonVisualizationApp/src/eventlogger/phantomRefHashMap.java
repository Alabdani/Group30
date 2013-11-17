package eventlogger;

import java.lang.ref.PhantomReference;
import java.util.HashMap;

public class phantomRefHashMap<K, M> extends HashMap {
	
	public void putHashCodes(PhantomReference pr, Object o) {
		
		java.util.Map.Entry<String,Integer> pair=new java.util.AbstractMap.SimpleEntry<>(o.getClass().toString(),o.hashCode());
		
		super.put(pr.hashCode(), pair);
		
	}
	
	public int getHashCode(K key) {
		
		String s = super.get(key).toString();
		int i = s.indexOf("=");
		
		return Integer.parseInt(s.substring(i+1));
	}
	
	public String getClassName(K key) {
		
		String s = super.get(key).toString();
		int f = s.lastIndexOf(".");
		int l = s.lastIndexOf("=");
		
		return s.substring(f+1, l);
	}
}
