package eg.edu.alexu.csd.oop.draw;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class FindShape {
	
	public static Boolean contains(Point p, Shape shape) {
		String s = shape.getClass().getSimpleName();
		switch(s){
		
		case "Sqaure" :
		Rectangle2D seq =
		new Rectangle2D.Double(((Point)shape.getPosition()).x, ((Point)shape.getPosition()).y,
			shape.getProperties().get("length"), shape.getProperties().get("length"));
		return seq.contains(p);
		
		case "Circle" :
			Ellipse2D circle =
			new Ellipse2D.Double(((Point)shape.getPosition()).x, ((Point)shape.getPosition()).y,
			shape.getProperties().get("radius"), shape.getProperties().get("radius"));
			return circle.contains(p);
		
		case "Line" :
			Line2D line =
			new Line2D.Double(((Point)shape.getPosition()).x, ((Point)shape.getPosition()).y,
			shape.getProperties().get("x2"), shape.getProperties().get("y2"));
			return line.contains(p);
		
		case "Rectangle" :
			Rectangle2D rec =
			new Rectangle2D.Double(((Point)shape.getPosition()).x, ((Point)shape.getPosition()).y,
			shape.getProperties().get("width"), shape.getProperties().get("height"));
			return rec.contains(p);
			
		case "Elipse" :
			Ellipse2D elipse =
			new Ellipse2D.Double(((Point)shape.getPosition()).x, ((Point)shape.getPosition()).y,
			shape.getProperties().get("r1"), shape.getProperties().get("r2"));
			return elipse.contains(p);	
			
		case "Triangle"	:
			int[] x = new int[3], y = new int[3];
			x[0] = shape.getProperties().get("x").intValue();
			x[1] = shape.getProperties().get("x2").intValue();
			x[2] = shape.getProperties().get("x3").intValue();
			y[0] = shape.getProperties().get("y").intValue();
			y[1] = shape.getProperties().get("y2").intValue();
			y[2] = shape.getProperties().get("y3").intValue();
			Polygon triangle = new Polygon(x, y, 3);
			return triangle.contains(p);
			
		default : return false;}
	}
	
}
