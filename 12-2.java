package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class Gui extends JFrame {
	private Point p = new Point(50,50);
	private drawPanel dp = new drawPanel();
	
	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dp.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				p = e.getPoint();
				
				repaint();
				
			}
		});
		setContentPane(dp);

		
		setSize(300, 300);
		setVisible(true);

	}

	class drawPanel extends JPanel {
		private ImageIcon imgIcon = new ImageIcon("images/back.jpg");
		private Image img = imgIcon.getImage();
		
		public drawPanel() {
			setLayout(new FlowLayout());
				
			
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0,getWidth(),getHeight(), this);
			g.setColor(Color.GREEN);
			g.fillOval((int)p.getX(),(int)p.getY(), 20, 20);
			
		}
		
	}


	public static void main(String[] args) {
		new Gui();
	}

}