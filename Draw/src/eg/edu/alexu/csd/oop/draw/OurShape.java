package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class OurShape implements Shape {
	protected Point position = new Point();
	protected Map<String, Double> properties = new HashMap<String, Double>();
	protected Color color = null;
	protected Color fillColor = null;
	
	
	public OurShape() {
		this.properties.put("x", 0.0);
		this.properties.put("y", 0.0);

		this.properties.put("colorR", 0.0);
		this.properties.put("colorB", 0.0);
		this.properties.put("colorG", 0.0);
		
		this.properties.put("colorFillR", 0.0);
		this.properties.put("colorFillB", 0.0);
		this.properties.put("colorFillG", 0.0);
	}
	@Override
	public void setPosition(Object position) {
		// TODO Auto-generated method stub
		this.position.x = (int) ((Point) position).getX();
		this.position.y = (int) ((Point) position).getY();
		this.properties.put("x", ((Point) position).getX());
		this.properties.put("y", ((Point) position).getY());
	}
	@Override
	public Object getPosition() {
		// TODO Auto-generated method stub
		Point p = new Point();
		p.x = (int) this.position.getX();
		p.y = (int) this.position.getY();
		return p;
	}
 
	@SuppressWarnings("rawtypes")
	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub	
		for(Map.Entry m:properties.entrySet()){
			this.properties.put((String)m.getKey(), (Double) m.getValue());
		}

	}
	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return this.properties;
	}
	@Override
	public void setColor(Object color) {
		// TODO Auto-generated method stub
		this.color = (Color) color;
		this.properties.put("colorR", (double) ((Color) color).getRed());
		this.properties.put("colorB", (double) ((Color) color).getBlue());
		this.properties.put("colorG", (double) ((Color) color).getGreen());
	}
	@Override
	public Object getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}
	@Override
	public void setFillColor(Object color) {
		// TODO Auto-generated method stub
		this.fillColor = (Color) color;
		this.properties.put("colorFillR", (double) fillColor.getRed());
		this.properties.put("colorFillB", (double) fillColor.getBlue());
		this.properties.put("colorFillG", (double) fillColor.getGreen());

	}
	@Override
	public Object getFillColor() {
		// TODO Auto-generated method stub
		return this.fillColor;
	}

// create a deep clone of the shape
	
    @SuppressWarnings("rawtypes")
	public Object clone() throws CloneNotSupportedException {
    	Shape v;
    	Map<String, Double> p = new HashMap<String, Double>();
		try {
			v = getClass().getConstructor().newInstance();
			for(Map.Entry m:getProperties().entrySet()){
				p.put((String)m.getKey(), (Double)m.getValue());
			}
			v.setProperties(p);
			if(color != null)
				v.setColor(color);
			if (fillColor != null)
				v.setFillColor(fillColor);
	    	v.setPosition(position);
	    	return v;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
		
	}

    
    public static String HexCodeOfColor (int r, int g, int b) {
        String s = String.format("#%02x%02x%02x", r, g, b);
		return s;
    }
    
    public static Color getHexCodeOfColor(String s) {
    	return new Color(
                Integer.valueOf( s.substring( 1, 3 ), 16 ),
                Integer.valueOf( s.substring( 3, 5 ), 16 ),
                Integer.valueOf( s.substring( 5, 7 ), 16 ) );
    }
    @Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		position.x = properties.get("x").intValue();
		position.y = properties.get("y").intValue();
	}
    
}
