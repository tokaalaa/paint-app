package eg.edu.alexu.csd.oop.draw;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DrawingApp {
	private static JFrame frame;
	static DrawingApp window;
	 protected static DrawingEngine mypaint = new OurDrawingEngine();
	 protected JPanel control;
	 protected static JComponent canvas;
	
	public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
				@SuppressWarnings("static-access")
				public void run() {
					try {
						window = new DrawingApp();
						 frame.setPreferredSize(new Dimension(1100, 700));
						 frame.pack();
						 frame.setLocationRelativeTo(null);
						 frame.setVisible(true);
						frame.getContentPane().setLayout(null);
						
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	    }
	    
	    /**
		 * Create the application.
		 */
		public DrawingApp() {
			initialize();
		}
	
	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	private void initialize() {	
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JMenuBar menuBar = new JMenuBar();
	    frame.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(file);
		
	    JMenuItem save = new JMenuItem(new AbstractAction("Save") {
	        public void actionPerformed(ActionEvent e) {
	            // Button pressed logic goes here
	        	String s = "" + this.getClass();
		    	  Save sav = new Save(s);
		    	  sav.setVisible(true);
	        }
	    });
		file.add(save);
		
		JMenuItem load = new JMenuItem(new AbstractAction("Load") {
	        public void actionPerformed(ActionEvent e) {
	            // Button pressed logic goes here
                showOpenFileDialog();
		}
	    });	
		file.add(load);
		
		JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
	        @SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
	            // Button pressed logic goes here
	        	window.frame.setVisible(false);
			}
	    });	
		file.add(exit);
		
		control = new JPanel();
		control.setMaximumSize(new Dimension(100, frame.getHeight()));
		control.setBounds(0, 0, 100, frame.getHeight());
		frame.getContentPane().add(control);
		control.setLayout(null);
		
		Object[] data = {"Circle","Rectangle", "Triangle", "Line", "Elipse", "sqaure"};
		final JList list = new JList(data); //data has type Object[]
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				 if (e.getValueIsAdjusting() == false) {
				        if (list.getSelectedIndex() != -1) {
				        	//open properties window
				        	Properties pro;
				        	switch(list.getSelectedIndex()) {
				        	case 0:
				        		pro = new Properties("eg.edu.alexu.csd.oop.draw.Circle",
				        				new Circle(), 0);
				        		pro.setVisible(true);
				        		break;
				        	case 1:
				        		pro = new Properties("eg.edu.alexu.csd.oop.draw.Rectangle",
				        				new Rectangle(), 0);
				        		pro.setVisible(true);
				        		break;
				        	case 2:
				        		pro = new Properties("eg.edu.alexu.csd.oop.draw.Triangle",
				        				new Triangle(), 0);
				        		pro.setVisible(true);
				        		break;
				        	case 3:
				        		pro = new Properties("eg.edu.alexu.csd.oop.draw.Line",
				        				new Line(), 0);
				        		pro.setVisible(true);
				        		break;
				        	case 4:
				        		pro = new Properties("eg.edu.alexu.csd.oop.draw.Elipse",
				        				new Elipse(), 0);
				        		pro.setVisible(true);
				        		break;
				        	case 5:
				        		pro = new Properties("eg.edu.alexu.csd.oop.draw.Sqaure",
				        				new Sqaure(), 0);
				        		pro.setVisible(true);
				        		break;
				        	}
				        	list.clearSelection();
				        }
				    }		
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScroller.setSize(80, 116);
		listScroller.setLocation(10, 10);
		listScroller.setPreferredSize(new Dimension(100, 80));
	    control.add(listScroller);  
	    
	    canvas = new drawArea();
        canvas.setMaximumSize(new Dimension(frame.getWidth() - 100, frame.getHeight()));
        canvas.setBounds(100, 0, frame.getWidth() - 100, frame.getHeight());
		frame.getContentPane().add(canvas);
		canvas.setLayout(null);
	        
		 frame.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					menuBar.setSize(frame.getWidth(), 30);
			        canvas.setBounds(100, 0, frame.getWidth() - 100, frame.getHeight());
					control.setBounds(0, 0, 100, frame.getHeight());
				}
			});

			JMenu undo = new JMenu();
			undo.setText("undo");
			undo.setMnemonic(KeyEvent.VK_F);
		    menuBar.add(undo);
		    undo.addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
		        	mypaint.undo();
	        		DrawingApp.canvas.repaint();
				}

		    });
		    
			JMenu redo = new JMenu();
			redo.setText("redo");
			redo.setMnemonic(KeyEvent.VK_F);

		    menuBar.add(redo);
		    redo.addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
		        	mypaint.redo();
	        		DrawingApp.canvas.repaint();
				}

		    });

		    JButton saveBar = new JButton();
		    saveBar.setText("Save");
		    menuBar.add(saveBar);
		    saveBar.setBorder(null);
		    saveBar.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  String s = "" + this.getClass();
		    	  Save sav = new Save(s);
		    	  sav.setVisible(true);
		      }
		    });
	}
	
	public void showOpenFileDialog() {
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) { }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
		     mypaint.load(selectedFile.getAbsolutePath());
     		canvas.repaint();
        }
    }
  
}