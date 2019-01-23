package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Sqaure extends OurShape{

	private int length;
	public Sqaure() {
		super();
		super.properties.put("length" , 0.0);
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		super.draw((Graphics)canvas);
		length = super.properties.get("length").intValue();
		if(super.getColor() != null) {
			((Graphics) canvas).setColor((Color) getColor());
			((Graphics) canvas).drawRect (((Point)getPosition()).x, ((Point)getPosition()).y, length, length);
			}
			if(super.getFillColor() != null) {
			((Graphics) canvas).setColor((Color) getFillColor());
			((Graphics) canvas).fillRect(((Point)getPosition()).x, ((Point)getPosition()).y, length, length);	
			}
	}

}
