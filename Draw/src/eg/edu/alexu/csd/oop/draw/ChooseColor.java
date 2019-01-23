package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseColor{
	private JLabel sampleText = new JLabel("Show Color");
	private JButton chooseButton = new JButton("Choose Color");
	private JLabel sampleText2 = new JLabel("Show Color Fill");
	private JButton chooseButton2 = new JButton("Choose Color Fill");
	
	ChooseColor() {
	    JPanel panel1 = new JPanel();
	    sampleText.setBackground((Color) Properties.shape.getColor());
	    panel1.add(sampleText);
	    chooseButton.addActionListener(new ButtonListener());
	    panel1.add(chooseButton);
	    
	    sampleText2.setBackground((Color) Properties.shape.getFillColor());
	    panel1.add(sampleText2);
	    chooseButton2.addActionListener(new ButtonListener2());
	    panel1.add(chooseButton2);
	    
	    Properties.contentPane.add(panel1);
	    panel1.setSize(450, 50);  
	  }

	  private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	      Color c = JColorChooser.showDialog(null, "Choose a Color", sampleText.getForeground());
	      if (c != null)
	        sampleText.setForeground(c);
	      for(int i = 0; i <  (Properties.shape.getProperties().size() - 4); i++) {
	    	  if (Properties.labels[i].getText().equals("Color")) {
	    		  Color r = sampleText.getForeground();
			    	Properties.textField[i].setText(OurShape.HexCodeOfColor(r.getRed(), r.getGreen(), r.getBlue())); 
				      Properties.shape.setColor((Color)r);
	      }
	      }
	    }
	  }
	  
	  private class ButtonListener2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
		      Color c = JColorChooser.showDialog(null, "Choose a Color Fill", sampleText2.getForeground());
		      if (c != null)
		        sampleText2.setForeground(c);
		      for(int i = 0; i <  (Properties.shape.getProperties().size() - 4); i++) {
		    	  if (Properties.labels[i].getText().equals("ColorFill")) {
		    		  Color r = sampleText2.getForeground();
		    		  Properties.textField[i].setText(OurShape.HexCodeOfColor(r.getRed(), r.getGreen(), r.getBlue()));
				      Properties.shape.setFillColor((Color)r);
		      }
		      }
		    }
		  }
}