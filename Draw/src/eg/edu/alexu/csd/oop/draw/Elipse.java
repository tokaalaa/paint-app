package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;


public class Elipse extends OurShape {

	private int r1, r2;
	public Elipse () {
		super();
		super.properties.put("r1" , 0.0);
		super.properties.put("r2" , 0.0);
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		super.draw((Graphics)canvas);
		r1 = properties.get("r1").intValue();
		r2 = properties.get("r2").intValue();
		Graphics2D g2 = (Graphics2D) canvas;
		if(super.getColor() != null) {
			g2.setColor((Color) getColor());
			g2.draw(new Ellipse2D.Double(((Point)getPosition()).x, ((Point)getPosition()).y, r1, r2));
		}
		if(super.getFillColor() != null) {
			g2.setColor((Color) getFillColor());
			g2.fill(new Ellipse2D.Double(((Point)getPosition()).x, ((Point)getPosition()).y, r1, r2));
		}
	}


}
