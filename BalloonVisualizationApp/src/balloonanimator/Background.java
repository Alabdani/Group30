package balloonanimator;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Background extends JComponent implements Runnable {

	public Balloons balls;


	

		
/*	public Background(StickFigures f){
		this.stickfig = f;
	}*/
/*	public Background(Balloons b){
		this.balls = b;
	
		
	}*/
	

	@Override
	public void run() {
		
	
		
/*		for (StickFigure sf : getFigs().getStickFigs()){
			sf.repaint();
			
		}*/
		
		// each balloon is given a random starting position x.
		for(Balloon ball : getBalloons().getBall()){
			int x = (int) (Math.random()*700);
			
			ball.setLocation(new Point(x,800));
			
		};;;;
		
		while (getBalloons().isVisible()){
			 SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
    				 getBalloons().repaint();
				
                 }
             });
			 
/*			 CountDownLatch start = new CountDownLatch(3);
			 CountDownLatch start1 = new CountDownLatch(1);
		
				try {
					start.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
*/
			 
		//Checks if the balloons are ready to fly
			 for(Balloon ball : getBalloons().getBall()){
				 int time = ball.getTimer();
				 
				 if(time < 1){
					 
				 move(ball); 
				 
				 ball.decrementTimer(); 
				 
	//			 System.out.println(time);
				 
				 }
				 
				 ball.decrementTimer();
				 
				 
			 }
			 try{
				 Thread.sleep(50);
			 }catch (InterruptedException ex){
				 
			 }
		}
	}
	


	
	public Balloons getBalloons(){
		return balls;
	}


	
	public void move(Balloon ball){
		Point p = ball.getLocation();
		
		int x = p.x;
		int y = p.y;
		
	//random movement of the balloons
		int r = (int) (Math.random()*10);
		if(r < 4){
			x = x - 1;
		
		}else {
			x = x + 2;	
		}
		int h = (int) (Math.random()*10);
		if(h < 4){
			y = y - 2;
		
		}else {
			y = y -5;	
		}

		
		ball.setLocation(new Point (x,y));
		
	}
	public void add(Balloons b) {
		this.balls = b;
		
	}





}
