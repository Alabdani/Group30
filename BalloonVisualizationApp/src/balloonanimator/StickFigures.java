package balloonanimator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class StickFigures extends JPanel {
	private List<StickFigure>  stickfigs;
	
	public StickFigures(){
		stickfigs = new ArrayList<StickFigure>(5);
		int x = 0;
		for(int i = 0; i < 25 ; i++){
			stickfigs.add(new StickFigure(x+5));
			x = x+10;
		}

}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (StickFigure s : stickfigs) {
            s.paint(g2d);
        }
        g2d.dispose();
    }
	
	public List<StickFigure> getStickFigs(){
		return stickfigs;
	}
}
