package balloonanimator;

import java.io.File;
import java.io.IOException;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;

public class PopPlayer {
    
    public void explode() throws NoPlayerException, IOException{
    
    int i = (int) Math.round(Math.random() * 3);
    File f;
    
    switch (i) {
    	case 0: f = new File("sounds/balloon1.wav");
    				break;
    	case 1: f = new File("sounds/balloon2.wav");
    				break;
    	case 2: f = new File("sounds/balloon3.wav");
    				break;
    	case 3: f = new File("sounds/balloon4.wav");
    				break;
    	default: System.err.println("error in soundeffect");
    			f = null;
    			break;
    }
    	
    MediaLocator ml = new MediaLocator(f.toURL());
    Player p = Manager.createPlayer(ml);
    p.start();

    }
    
}