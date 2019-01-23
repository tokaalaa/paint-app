package eg.edu.alexu.csd.oop.draw;

import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Properties extends JFrame {

	static JPanel contentPane;
	static String line;
	static String name;
	static Shape shape;
	static JLabel[] labels;
	static JTextField[] textField;
	int flag = 0;
	/**
	 * Create the frame.
	 * @param line 
	 */
	@SuppressWarnings({ "rawtypes", "unused"})
	public Properties(String line2, final Shape shape2, final int check) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		line = line2;
		name = line.substring(26, line.length());
		
		setTitle("Set Properties to " + name);
		setVisible(true);
		setBounds(700, 100, 450, 383);
		
        shape =  shape2; 
        int j = 40;
        int i = 0;
        int nr = 0, nr1 = 0, nb = 0, nb1 = 0, ng = 0, ng1 = 0;
        
		labels = new JLabel[shape.getProperties().size() - 4];
		textField = new JTextField[shape.getProperties().size() - 4];
        for(Map.Entry m:shape.getProperties().entrySet()){
		    if(!(m.getKey().equals("colorFillG") || m.getKey().equals("colorFillR")
		    		|| m.getKey().equals("colorFillB") || m.getKey().equals("colorG")
		    		|| m.getKey().equals("colorR") || m.getKey().equals("colorB"))) {
		    	labels[i] = new JLabel((String) m.getKey());
			    textField[i] = new JTextField(Double.toString((double) m.getValue()));
		    	 labels[i].setBounds(10, j, 70, 20);
				textField[i].setBounds(100, j, 70, 20);
				contentPane.add(labels[i]);
				contentPane.add(textField[i]);
				j = j + 20;
				i++;
		    }
		    if (m.getKey().equals("colorFillG"))
		    	ng1 =   ((Double) m.getValue()).intValue();
		    else if (m.getKey().equals("colorFillR"))
		    	nr1 =   ((Double) m.getValue()).intValue();
		    else if (m.getKey().equals("colorFillB"))
		    	nb1 =   ((Double) m.getValue()).intValue();
		    else if (m.getKey().equals("colorG"))
		    	ng =   ((Double) m.getValue()).intValue();
		    else if (m.getKey().equals("colorR"))
		    	nr =  ((Double) m.getValue()).intValue();
		    else if (m.getKey().equals("colorB"))
		    	nb =   ((Double) m.getValue()).intValue();
		    
        }
        ChooseColor c = new ChooseColor();
        
        labels[i] = new JLabel("Color");
	    textField[i] = new JTextField(OurShape.HexCodeOfColor(nr, ng, nb));
	    labels[i].setBounds(10, j, 70, 20);
		textField[i].setBounds(100, j, 70, 20);
		contentPane.add(labels[i]);
		contentPane.add(textField[i]);
		textField[i].setEditable(false);
		j = j + 20;
		i++;

		labels[i] = new JLabel("ColorFill");
	    textField[i] = new JTextField(OurShape.HexCodeOfColor(nr1, ng1, nb1));
	    labels[i].setBounds(10, j, 70, 20);
		textField[i].setBounds(100, j, 70, 20);
		contentPane.add(labels[i]);
		contentPane.add(textField[i]);
		textField[i].setEditable(false);		
		j = j + 40;
		i++;
		if(check != 0) {
		 JButton clone = new JButton("Clone");
		 clone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clone c = new Clone(shape, name);
					flag = 1;
					c.setVisible(true);
					
	        	}
	        });
		 clone.setBounds(50, j,95,30);  
	     contentPane.add(clone);
		
	     JButton delete = new JButton("Delete");
	     delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DrawingApp.mypaint.removeShape(shape);
	        		DrawingApp.canvas.repaint();
	        		setVisible(false);
	        	}
	        });
	     delete.setBounds(150, j,95,30);  
	     contentPane.add(delete);}
			j = j + 50;
		
        JButton b = new JButton("OK");  
        b.addActionListener(new ActionListener() {
        	@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
        		int i = 0;
        		for(Map.Entry m:shape.getProperties().entrySet()){
        		    if(!(m.getKey().equals("colorFillG") || m.getKey().equals("colorFillR")
        		    		|| m.getKey().equals("colorFillB") || m.getKey().equals("colorG")
        		    		|| m.getKey().equals("colorR") || m.getKey().equals("colorB"))) {
        		    	m.setValue(Double.parseDouble(textField[i].getText()));
        		    	i++;
        		    }
        		 }
        		if (flag == 1) {
        			DrawingApp.mypaint.addShape(Clone.shape);	
        			flag = 0;
        		}
        		if(check == 1)
        			DrawingApp.mypaint.updateShape(shape2, shape);
        		else if(check == 0)
        			DrawingApp.mypaint.addShape(shape);
        		DrawingApp.canvas.repaint();
        		setVisible(false);
        	}
        });
        b.setBounds(50,j,95,30);  
        contentPane.add(b);
        
	}
}
