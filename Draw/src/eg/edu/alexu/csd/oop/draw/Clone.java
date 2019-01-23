package eg.edu.alexu.csd.oop.draw;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Clone extends JFrame {

	private JPanel contentPane;
	static Shape shape;
	private JLabel labelx1;
	private JLabel labelx2;
	private JLabel labely1;
	private JLabel labely2;
	private JTextField textFieldx1;
	private JTextField textFieldx2;
	private JTextField textFieldy1;
	private JTextField textFieldy2;
	private JButton ok;
	

	/**
	 * Create the frame.
	 */
	public Clone( final Shape shape2, String name) {
		// TODO Auto-generated constructor stub
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 264, 322);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel labelx = new JLabel("x");
			final JTextField textFieldx = new JTextField("0.0");
			JLabel labely = new JLabel("y");
			final JTextField textFieldy = new JTextField("0.0");
			 labelx.setBounds(50, 10, 70, 20);
			 textFieldx.setBounds(100, 10, 70, 20);
			contentPane.add(labelx);
			contentPane.add(textFieldx);
			 labely.setBounds(50, 50, 70, 20);
			 textFieldy.setBounds(100, 50, 70, 20);
			contentPane.add(labely);
			contentPane.add(textFieldy);
			
			labelx1 = new JLabel("x2");
			textFieldx1 = new JTextField("0.0");
			labely1 = new JLabel("y2");
			textFieldy1 = new JTextField("0.0");
			labelx1.setBounds(50, 90, 70, 20);
			textFieldx1.setBounds(100, 90, 70, 20);
			labely1.setBounds(50, 130, 70, 20);
			textFieldy1.setBounds(100, 130, 70, 20);
			contentPane.add(labelx1);
			contentPane.add(textFieldx1);			
			contentPane.add(labely1);
			contentPane.add(textFieldy1);
			labelx1.setVisible(false);
			labely1.setVisible(false);
			textFieldx1.setVisible(false);
			textFieldy1.setVisible(false);
			
			labelx2 = new JLabel("x3");
			textFieldx2 = new JTextField("0.0");
			labely2 = new JLabel("y3");
			textFieldy2 = new JTextField("0.0");
			labelx2.setBounds(50, 170, 70, 20);
			textFieldx2.setBounds(100, 170, 70, 20);
			labely2.setBounds(50, 210, 70, 20);
			textFieldy2.setBounds(100, 210, 70, 20);
			contentPane.add(labelx2);
			contentPane.add(textFieldx2);			
			contentPane.add(labely2);
			contentPane.add(textFieldy2);
			labelx2.setVisible(false);
			labely2.setVisible(false);
			textFieldx2.setVisible(false);
			textFieldy2.setVisible(false);
			
			if (name.equals("Line")) {    	
				labelx1.setVisible(true);
				labely1.setVisible(true);
				textFieldx1.setVisible(true);
				textFieldy1.setVisible(true);


			} else if (name.equals("Triangle") || name.equals("Triangl")) {
				labelx1.setVisible(true);
				labely1.setVisible(true);
				labelx2.setVisible(true);
				labely2.setVisible(true);
				textFieldx1.setVisible(true);
				textFieldy1.setVisible(true);
				textFieldx2.setVisible(true);
				textFieldy2.setVisible(true);
			}
						
			ok = new JButton("OK");
			ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							shape = (Shape) shape2.clone();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						shape.getProperties().put("x", Double.parseDouble(textFieldx.getText()));
						shape.getProperties().put("y", Double.parseDouble(textFieldy.getText()));
						Point s = new Point();
						s.x = (int) Double.parseDouble(textFieldx.getText());
						s.y = (int) Double.parseDouble(textFieldy.getText());
						shape.setPosition(s);
						
						if (name.equals("Line")) {    	
							shape.getProperties().put("x2", Double.parseDouble(textFieldx1.getText()));
							shape.getProperties().put("y2", Double.parseDouble(textFieldy1.getText()));
						} else if (name.equals("Triangle") || name.equals("Triangl")) {
							shape.getProperties().put("x2", Double.parseDouble(textFieldx1.getText()));
							shape.getProperties().put("y2", Double.parseDouble(textFieldy1.getText()));
							
							shape.getProperties().put("x3", Double.parseDouble(textFieldx2.getText()));
							shape.getProperties().put("y3", Double.parseDouble(textFieldy2.getText()));
						}
		        		setVisible(false);
		        	}
		        });
			ok.setBounds(60, 242, 95,30);  
		     contentPane.add(ok);
		     
		    /* if (textFieldy.getText().equals("0.0") || textFieldx.getText().equals("0.0")) {
				     ok.setEnabled(false);
		     } else if ((textFieldy2.getText().equals("0.0") || textFieldx2.getText().equals("0.0") ||
		    		 textFieldy1.getText().equals("0.0") || textFieldx1.getText().equals("0.0")) &&
		    		 (name.equals("Triangle") || name.equals("Triangl"))) {
				  	ok.setEnabled(false);
			}	else if ((textFieldy1.getText().equals("0.0") || textFieldx1.getText().equals("0.0")) &&
		    		 name.equals("Line")) {
				  	ok.setEnabled(false);
			} else {
			  	ok.setEnabled(true);
			}
	            repaint ();*/
	}

}
