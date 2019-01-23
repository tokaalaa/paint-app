package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public class Line extends OurShape{
	
	private int x2, y2;
	public Line() {
		super();
		super.properties.put("x" , 0.0);
		super.properties.put("y" , 0.0);
		super.properties.put("x2" , 0.0);
		super.properties.put("y2" , 0.0);
		
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		super.draw((Graphics)canvas);
		x2 = super.properties.get("x2").intValue();
		y2 = super.properties.get("y2").intValue();
		((Graphics) canvas).setColor((Color) getColor());
		((Graphics) canvas).drawLine(((Point)getPosition()).x, ((Point)getPosition()).y, x2, y2);
	}

}
