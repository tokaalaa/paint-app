package eg.edu.alexu.csd.oop.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Triangle extends OurShape{
	private int[] x = new int[3], y = new int[3];
	public Triangle() {
		super();

		super.properties.put("x2" , 0.0);
		super.properties.put("y2" , 0.0);
		super.properties.put("x3" , 0.0);
		super.properties.put("y3" , 0.0);	
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		super.draw((Graphics)canvas);
		x[0] = properties.get("x").intValue();
		x[1] = properties.get("x2").intValue();
		x[2] = properties.get("x3").intValue();
		y[0] = properties.get("y").intValue();
		y[1] = properties.get("y2").intValue();
		y[2] = properties.get("y3").intValue();
	    Graphics2D g2d = (Graphics2D) canvas;
	    if(super.getColor() != null) {
	    	g2d.setStroke(new BasicStroke(5));
			g2d.setColor((Color) getColor());
	    g2d.drawPolygon(x, y, 3);
	    }
	    if(super.getFillColor() != null) {
	    	g2d.setColor((Color) getFillColor());
	    g2d.fillPolygon(x, y, 3);
	    }
	}


}
