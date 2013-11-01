package balloonanimator;







import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;




public class Balloons extends JPanel {
	private List<Balloon> Balls;
	
	public Balloons(){
		Balls = new ArrayList<Balloon>(25);
		for(int i = 0; i < 25 ; i++){
			Balls.add(new Balloon(new Color(random(255), random(255), random(255), random(255))));
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
