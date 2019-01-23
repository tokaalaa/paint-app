package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Circle extends OurShape {
	private int radius;
	
	public Circle() {
		super();
		super.properties.put("radius", 0.0);
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
				//System.out.println(getProperties().get("x").intValue());
				super.draw((Graphics)canvas);
				//System.out.println(getProperties().get("x").intValue());

				radius = properties.get("radius").intValue();
				if(super.getColor() != null) {
					((Graphics) canvas).setColor((Color) getColor());
					((Graphics) canvas).drawOval(((Point)getPosition()).x, ((Point)getPosition()).y, radius, radius);
				}
				if(super.getFillColor() != null) {
					((Graphics) canvas).setColor((Color) getFillColor());
					((Graphics) canvas).fillOval(((Point)getPosition()).x, ((Point)getPosition()).y, radius, radius);
				}		
	}

}
