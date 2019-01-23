package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public class Rectangle extends OurShape{

	private int width, height;
	
	public Rectangle() {
		super();
		super.properties.put("width" , 0.0);
		super.properties.put("height" , 0.0);
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		super.draw((Graphics)canvas);
		width = super.properties.get("width").intValue();
		height = super.properties.get("height").intValue();
		if(super.getColor() != null) {
		((Graphics) canvas).setColor((Color) getColor());
		((Graphics) canvas).drawRect (((Point)getPosition()).x, ((Point)getPosition()).y, width, height);
		}
		if(super.getFillColor() != null) {
		((Graphics) canvas).setColor((Color) getFillColor());
		((Graphics) canvas).fillRect(((Point)getPosition()).x, ((Point)getPosition()).y, width, height);	
		}
	}


}
