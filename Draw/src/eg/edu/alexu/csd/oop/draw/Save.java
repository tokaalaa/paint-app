package eg.edu.alexu.csd.oop.draw;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Save extends JFrame {

	private JPanel contentPane;
	private String type;

	/**
	 * Create the frame.
	 * @param s 
	 */
	public Save(final String s) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		final JCheckBox checkListj = new JCheckBox("JsOn");
		checkListj.setBounds(70, 56, 97, 23);
		contentPane.add(checkListj);
		
		final JCheckBox checkListx = new JCheckBox("Xml");
		checkListx.setBounds(226, 56, 97, 23);
		contentPane.add(checkListx);

		
		JLabel lblNewLabel = new JLabel("Choose the type to save the file");
		lblNewLabel.setBounds(94, 11, 188, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Select location");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkListx.isSelected() && checkListj.isSelected()) {
					type = "two";
					showSaveFileDialog(type);
				} else if(checkListj.isSelected()) {
					type = "JsOn";
					String s ="_testDraw." + type;
					showSaveFileDialog(s);
				} else if(checkListx.isSelected()) {
					type = "Xml";
					String s ="_testDraw." + type;
					showSaveFileDialog(s);
				}
				if (!(checkListj.isSelected() || checkListx.isSelected())) {
					System.out.println("Please choose");
					//btnNewButton_1.setEnabled(false);
				}
		    	 setVisible(false);	
		    }
		});
		btnNewButton_1.setBounds(94, 110, 148, 40);
		contentPane.add(btnNewButton_1);
	}
	
	private void showSaveFileDialog(String m) {
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) { }
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		int userSelection = fileChooser.showSaveDialog(fileChooser);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			if (m.equals("two")) {
				DrawingApp.mypaint.save(fileToSave.getAbsolutePath() + "Xml_testDraw.Xml");
				DrawingApp.mypaint.save(fileToSave.getAbsolutePath() + "JsOn_testDraw.JsOn");
			} else {
				DrawingApp.mypaint.save(fileToSave.getAbsolutePath() + m);
			}
		}

	}
}
