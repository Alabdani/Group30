package balloonanimator;


import java.awt.Point;

import javax.swing.SwingUtilities;



public class RisingBalloon implements Runnable {
	private Balloons balls;
	public RisingBalloon(Balloons b){
		this.balls =b;
		
	}



	@Override
	public void run() {
		
		
		for(Balloon ball : getBalloons().getBall()){
			int x = (int) (Math.random()*700);
			
			ball.setLocation(new Point(x,800));
			
		}
		
		while (getBalloons().isVisible()){
			 SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                     getBalloons().repaint();
                 }
             });
			 for(Balloon ball : getBalloons().getBall()){
				 move(ball);
			 }
			 try{
				 Thread.sleep(50);
			 }catch (InterruptedException ex){
				 
			 }
		}
	}
	
	public void move(Balloon ball){
		Point p = ball.getLocation();
		
		int x = p.x;
		int y = p.y;
	
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
	
	
	public Balloons getBalloons(){
		return balls;
	}

}
