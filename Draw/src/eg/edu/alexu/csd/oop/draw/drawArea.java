package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JComponent;


@SuppressWarnings("serial")
public class drawArea extends JComponent{

		drawArea(){
	addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
    		Shape[] shapes = DrawingApp.mypaint.getShapes();
    		for(int i = 0; i < shapes.length; i++) {
    			if (FindShape.contains(e.getPoint(), shapes[i])) {
    				//open properties panel
    				String line = (String) shapes[i].toString().substring(0, shapes[i].toString().length()-9);
    				Properties pro = new Properties(line, shapes[i], 1);
					pro.setVisible(true);
    			}
    		}
        }
      });
}
	  public void paintComponent(Graphics g) {
		  // set background color
		  g.setColor(Color.WHITE);
		  g.fillRect(0, 0, getWidth(), getHeight());
		   DrawingApp.mypaint.refresh(g);
		  }

}
