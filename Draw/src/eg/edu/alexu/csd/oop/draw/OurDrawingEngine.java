package eg.edu.alexu.csd.oop.draw;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public  class OurDrawingEngine implements DrawingEngine {

	private LinkedList<Shape> drawingShapes = new LinkedList<Shape>();
	private Stack<Object> stredo = new Stack<Object>();
	private Stack<Object> stundo = new Stack<Object>();
	private int countRedo = 0;
	private int load = 0;

	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		drawingShapes.add(shape);
		stredo.push(shape);
		stredo.push("add");
		if (countRedo > 0) {
			countRedo--;
		}
		stundo.clear();
	}

	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		for (Shape s:drawingShapes) {
			if(s.equals(shape)) {
				drawingShapes.remove(s);
				stredo.push(shape);
				stredo.push("remove");
				if (countRedo > 0) {
					countRedo--;
				}
				stundo.clear();
				break;
			}
		}
	}

	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		int i = 0;
		boolean found = false;
		for (Shape s:drawingShapes) {		
			if(s.equals(oldShape)) {
				drawingShapes.set(i,newShape);
				found = true;
				if (countRedo > 0) {
					countRedo--;
				}
				stundo.clear();
					stredo.push(oldShape);
					stredo.push(newShape);
					stredo.push("update");
				
				break;
			}
			i++;
		}
		if(!found) {
			throw new NullPointerException();
		}
	}

	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		int i = 0;
		Shape[] shapes = new Shape[drawingShapes.size()];
		for (Shape s:drawingShapes) {
			shapes[i++] = s;
		}
		return shapes;
	}

	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		List<Class<? extends Shape>> s = new LinkedList<Class<? extends Shape>>();
		s.add(RoundRectangle.class);
		return s;
	}

	public void undo() {
		// TODO Auto-generated method stub
		String type = new String();
		if (stredo.size() != 0 && countRedo < 20) {
			type = (String) stredo.pop();
			Shape s1;
			Shape s2;
			if(type.equals("add")) {
				s1 = (Shape) stredo.pop();
				stundo.push(s1);
				stundo.push("remove");
				for (Shape s:drawingShapes) {
					if(s.equals(s1)) {
						drawingShapes.remove(s);
						break;
					}
				}
			} else if(type.equals("remove")) {
				s1 = (Shape) stredo.pop();
				stundo.push(s1);
				stundo.push("add");
				drawingShapes.add(s1);
			} else if(type.equals("update")) {
				s1 = (Shape) stredo.pop();
				s2 = (Shape) stredo.pop();
				stundo.push(s2);
				stundo.push(s1);
				stundo.push("update");
				int i = 0;
				boolean found = false;
				for (Shape s:drawingShapes) {		
					if(s.equals(s1)) {
						drawingShapes.set(i,s2);
						found = true;
						break;
					}
					i++;
				}
				if(!found) {
					throw new NullPointerException();
				}
			}
			countRedo++;
		}
	}

	public void redo() {
		// TODO Auto-generated method stub
		String type = new String();
		if (stundo.size() != 0) {
			type = (String) stundo.pop();
			Shape s1;
			Shape s2;
			if(type.equals("remove")) {
				s1 = (Shape) stundo.pop();
				drawingShapes.add(s1);
				stredo.push(s1);
				stredo.push("add");
			} else if(type.equals("add")) {
				s1 = (Shape) stundo.pop();
				for (Shape s:drawingShapes) {
					if(s.equals(s1)) {
						drawingShapes.remove(s);
							stredo.push(s1);
							stredo.push("remove");
						break;
					}
				}
			} else if(type.equals("update")) {
				s1 = (Shape) stundo.pop();
				s2 = (Shape) stundo.pop();
				int i = 0;
				boolean found = false;
				for (Shape s:drawingShapes) {		
					if(s.equals(s2)) {
						drawingShapes.set(i,s1);
						found = true;
							stredo.push(s2);
							stredo.push(s1);
							stredo.push("update");		
						break;
					}
					i++;
				}
				if(!found) {
					throw new NullPointerException();
				}				
			} 
			countRedo--;
		}
	}

	public void save(String path) {
		// TODO Auto-generated method stub
    	String[] parts = path.split("_testDraw.");
    	if(parts[1].equals("JsOn")) {
    		try {
				implementJson.write(getShapes(), path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} else if(parts[1].equals("XmL") || parts[1].equals("Xml")) {
    		try {
				implementXml.write(getShapes(), path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
	}

	public void load(String path) {
		// TODO Auto-generated method stub
		load = 1;
		String[] parts = path.split("_testDraw.");
    	if(parts[1].equals("JsOn")) {
    		try {
    			Shape[] shape = implementJson.read(path);
    			drawingShapes.clear();
    			for (int i = 0; i < shape.length; i++) {
    				addShape(shape[i]);
    			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} else if(parts[1].equals("XmL") || parts[1].equals("Xml")) {
    		try {
    			Shape[] shape = implementXml.read(path);
    			drawingShapes.clear();
    			for (int i = 0; i < shape.length; i++) {
    				addShape(shape[i]);
    			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if (load == 1) {
			stundo.clear();
			stredo.clear();
			load = 0;
		}
	}

	@Override
	public void refresh(Object canvas) {
		// TODO Auto-generated method stub
		for (Shape s:drawingShapes) {
			s.draw(canvas);
		}
	}
	


}
