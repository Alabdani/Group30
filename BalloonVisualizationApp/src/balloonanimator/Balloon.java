package balloonanimator;




import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;


import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Balloon extends JPanel{

	
	  private Color color;
	  private Point point;

      
      public Balloon(Color color){
    	  setColor(color);
   

    	  
      }
      

      
 


	private void setColor(Color c) {
		this.color = c;
		
	}
	
	public Point getLocation(){
		return point;
		
	}
	
	public void setLocation(Point point){
		this.point = point;
		
	}







	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		Point p = getLocation();
		g2d.setColor(color);
		g2d.fillOval(p.x, p.y, 50, 60);
		g2d.setColor(Color.black);
		g2d.drawLine(p.x+25, p.y+115, p.x+25,p.y+60);

}
}



