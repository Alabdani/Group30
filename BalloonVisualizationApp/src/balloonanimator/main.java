package balloonanimator;

import javax.swing.JFrame;



public class main {
	
	public static void main(String[] args) {

		JFrame frame = new JFrame("Rising Balloon");
		Balloons balls = new Balloons();
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(balls);
		
		new Thread(new RisingBalloon(balls)).start();
		
	
	}
}
