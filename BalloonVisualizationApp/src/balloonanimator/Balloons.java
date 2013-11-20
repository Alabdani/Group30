package balloonanimator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import eventlogger.BalloonInfo;


//Where a list of balloons are created

public class Balloons extends JPanel {
	private List<Balloon> Balls;

	//each balloon is given a random color and a timer
	public Balloons(BalloonInfo[] bInfo){
		Balls = new ArrayList<Balloon>(25);
		BalloonInfo bInfoEntry;
		for(int i = 0; i < bInfo.length -1 ; i++){
			bInfoEntry = bInfo[i];
			if(bInfoEntry != null) {
			Balloon ball = new Balloon(new Color(random(255), random(255), random(255), random(255)));
			ball.addTimer(bInfoEntry.releaseTime);
			ball.setCreationTime(bInfoEntry.creationTime);
			
			
			Balls.add(ball);
			}
		}
	}
	   public static int random(int maxRange) {
	        return (int) Math.round((Math.random() * maxRange));
	    }
	   
	     @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g.create();
	            for (Balloon ball : Balls) {
	                ball.paint(g2d);
	            }
	            g2d.dispose();
	        }
	
	   public List<Balloon> getBall(){
		return Balls;
	}

}
