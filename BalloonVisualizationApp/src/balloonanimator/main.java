package balloonanimator;

import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;



public class main {
	

	public static void main(String[] args) {


		JFrame frame = new JFrame("Rising Balloon");
		Background bg = new Background();
		Balloons balls = new Balloons();
		StickFigures stickmans = new StickFigures();
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//balls are added to the frame
		frame.add(balls);
		bg.add(balls);
		
		// thread starts
		new Thread(bg).start();

	
//		new Thread(new RisingBalloon(balls)).start();

		

	
	}
	
	
}
