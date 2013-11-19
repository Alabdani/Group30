package balloonanimator;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class StickFigure extends JPanel {
	int x;
	
	public StickFigure(int x){
		setXCoord(x);
	
	}
	
	public void setXCoord(int x){
		this.x = x;
	}
	public int getXCoord(){
		return x;
	}
	
	public void graphics(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int xc = getXCoord();
		
	       
	       // the syntax of drawOval is (x1,y1,w,h)
	       //    where (x1,y1) is the top left corner  and
	       //    (w,h) is the width and height of the bounding rectangle
		
	       // the head
	       g2d.drawOval(xc+90,60,20,20);
	       
	       // the syntax of drawLine is (x1,y1,x2,y2);
	       // to draw a line from point (x1,y1) to (x2,y2)
	       
	       // the body
	       g2d.drawLine(xc+100,80,100,120);
	       
	       // the hands
	       g2d.drawLine(xc+100,100,80,100);
	       g2d.drawLine(xc+100,100,120,75);
		
		//the hands
		
		//legs
		g2d.drawLine(xc+100,120,85,135);
		g2d.drawLine(xc+100,120,115,135);
	}

}
