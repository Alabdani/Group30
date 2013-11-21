package balloonanimator;

import javax.media.NoPlayerException;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

//Balloon has a color, point.x,point.y and a timer

@SuppressWarnings("serial")
public class Balloon extends JPanel{

	  private int creationtime; 	
	  private Color color;
	  private Point point;
	  private int timer;
	  private double points[][] = { 
	            { 0, 50 }, { 30, 30 }, { 50, 5 }, { 20, 20 }, 
	            { 100, 40 }, { 60, 80 }, { 80, 80 }, { 50, 90 }, 
	            { 20, 120 }, { 40, 90 }, { 0, 60 } 
	        };
	  
	  PopPlayer pop = new PopPlayer();


      
      public Balloon(Color color){
    	  setColor(color);
    	  
      }
      public int getCreationTime(){
    	  return creationtime;
      }
      
      public void decrementCreationTime(){
    	  int k = getCreationTime();
    	  if( k !=0){
    		  k = k -1;
    		  setCreationTime(k);
    	  }
      }
      public void setCreationTime(int ct){
    	  this.creationtime = ct;
      }
      

    public void decrementTimer(){
    	int k = getTimer();
    	if(k !=0){
    		k = k - 1;
    		addTimer(k);
    	}
    }
      
    public void addTimer(int t){
    	this.timer = t;
    }
    
    public int getTimer(){
    	return timer;
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






//where the balloon is painted
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		Point p = getLocation();
		int ct = getCreationTime();
		if(p.y > 50 && ct == 0){
		g2d.setColor(color);
		g2d.fillOval(p.x, p.y, 50, 60);
		g2d.setColor(Color.black);
		g2d.drawLine(p.x+25, p.y+115, p.x+25,p.y+60);
		}else{
			
			//where its suppose to explode
		}
		

	}


	private void drawStar(Graphics2D g2d) {

	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                             RenderingHints.VALUE_ANTIALIAS_ON);

	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
	                             RenderingHints.VALUE_RENDER_QUALITY);

	        g2d.translate(25, 5);

	        GeneralPath star = new GeneralPath();

	        star.moveTo(points[0][0], points[0][1]);

	        for (int k = 1; k < points.length; k++)
	            star.lineTo(points[k][0], points[k][1]);

	        star.closePath();
	        g2d.fill(star);
		
	}

		



}



