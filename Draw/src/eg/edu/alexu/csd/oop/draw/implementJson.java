package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map; 

 
public class implementJson {	
	private static int count = 0;
	private static int countC = 0;
	private static int countCF = 0;
	private static int r = 0;
	private static int g = 0;
	private static int b = 0;
	private static int r1 = 0, g1 = 0, b1 = 0;

	 @SuppressWarnings("rawtypes")
	public static  void write(Shape[] shape, String path) throws IOException {
		 // The name of the file to open.
	        String fileName = path; 
	        FileWriter fileWriter = new FileWriter(fileName);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        	bufferedWriter.write("{\"ShapeArray\" :\n" );
        	bufferedWriter.write("[\n" );
        	for (int i = 0; i < shape.length; i++) {
        		bufferedWriter.write("{ \"className\" : " + "\"" + shape[i].getClass() +"\",\n");		 
        		int k = 0;
        		if (shape[i].getProperties() != null) {
        		int max = shape[i].getProperties().size();
        		for(Map.Entry m:shape[i].getProperties().entrySet()){
        			if (k == (max - 1) ) {
	        			bufferedWriter.write("\"" + m.getKey()+ "\" : " + "\"" + m.getValue() +"\"\n"); 
	        		}
	        		else {
	        			bufferedWriter.write("\"" + m.getKey()+ "\" : " + "\"" + m.getValue() +"\",\n"); 
	        		}
        			k++;
        		}
        		} else {
        			bufferedWriter.write("\"properties\" : " + "\"null\"\n");
        		}
        		//System.out.println(shape.length);
        		if (i == (shape.length - 1) ) {
		            bufferedWriter.write("}\n");
        		}
        		else {
	            bufferedWriter.write("},\n");
        		}  
        		
        	}
            bufferedWriter.write("]\n}\n");
            bufferedWriter.close();   
	 }
	 
	@SuppressWarnings("unused")
	public static  Shape[] read(String path) throws FileNotFoundException, ClassNotFoundException {
	        String fileName1 = path;
	        String line = null;
	    	 LinkedList<Shape> Shapes = new LinkedList<Shape>();
	        Shape[] shape = new Shape[100];
	    	String numRegex   = ".*[0-9].*";
            String alphaRegex = ".*[A-Z].*";
	        FileReader fileReader =  new FileReader(fileName1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            int flage = 0;
	        try {
	        	line = bufferedReader.readLine();
	        	line = bufferedReader.readLine();
	   		 	Map<String, Double> properties = new HashMap<String, Double>();
	            while((line = bufferedReader.readLine()) != null) {
		        	//System.out.println(line);
	            	if(line.contains("}") && flage == 1) {
	            		shape[i].setProperties(properties);
            			Shapes.add(shape[i]);
	            		i++;
	            		flage = 0;
	            		properties = new HashMap<String, Double>();
			        	//System.out.println("set pro");
	            	} if(line.contains("className") && flage == 0) {
	            		flage = 1;
	            		line = line.substring(23, line.length()-2);
	                    shape[i] = (Shape) createObject(line);
			        	//System.out.println("creat shape");
	            	} else if (line.matches(numRegex) || line.matches(alphaRegex)) {
	            	line = line.replaceAll("\"", "");
	            	line = line.replaceAll(" ", "");
	            	line = line.replaceAll("\t", "");
	            	line = line.replaceAll(",", "");
	            	String[] parts = line.split(":");
	            	Double d;
            		if (! (parts[1].equals("null")))
            		d = Double.parseDouble(parts[1]);
            		else
            		d = 0.0;
	            	properties.put(parts[0], d);
	            	Double x, y;
	            	Point p = new Point();
	            	if (parts[0].equals("x")) {
	            		x = d;
	            		count++;
	            	} else if (parts[0].equals("y")) {
	            		y = d;
	            		count++;
	            	} else if (parts[0].equals("colorR")) {
	            		r = d.intValue();
	            		countC++;
	            	} else if (parts[0].equals("colorG")) {
	            		g = d.intValue();
	            		countC++;
	            	} else if (parts[0].equals("colorB")) {
	            		b = d.intValue();
	            		countC++;
	            	} else if (parts[0].equals("colorFillR")) {
	            		r1 = d.intValue();
	            		countCF++;
	            	} else if (parts[0].equals("colorFillG")) {
	            		g1 = d.intValue();
	            		countCF++;
	            	} else if (parts[0].equals("colorFillB")) {
	            		b1 = d.intValue();
	            		countCF++;
	            	}
	            	if (count == 2) {
	            		shape[i].setPosition((Point)p);
	            		count =0;
	            	}
	            	if (countC == 3) {
	            		shape[i].setColor((Color)getHexCodeOfColor(HexCodeOfColor(r,g,b)));
	            		countC =0;
	            	}
	            	if (countCF == 3) {
	            		shape[i].setFillColor((Color)getHexCodeOfColor(HexCodeOfColor(r1,g1,b1)));	            		
	            		countCF =0;
	            	}
	            	}
	            }
	            bufferedReader.close(); 
	            
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        int size = Shapes.size();
	        Shape[] rshape = new Shape[size];
	        for (i = 0; i < size; i++) {
	        	rshape[i] = shape[i];
	        }

			return rshape;
	 }
	
	@SuppressWarnings("deprecation")
	private static Object createObject(String className) {
	      Object object = null;
	      try {
	          @SuppressWarnings("rawtypes")
			Class classDefinition = Class.forName(className);
	          object = classDefinition.newInstance();
	      } catch (InstantiationException e) {
	          System.out.println(e);
	      } catch (IllegalAccessException e) {
	          System.out.println(e);
	      } catch (ClassNotFoundException e) {
	          System.out.println(e);
	      }
	      return object;
	   }
	
	private static String HexCodeOfColor (int r, int g, int b) {
		 	String s = String.format("#%02x%02x%02x", r, g, b);  
			return s;
	    }
	    
	private static Color getHexCodeOfColor(String s) {
	          return new Color(
	                  Integer.valueOf( s.substring( 1, 3 ), 16 ),
	                  Integer.valueOf( s.substring( 3, 5 ), 16 ),
	                  Integer.valueOf( s.substring( 5, 7 ), 16 ) );
	      }
	    
}
