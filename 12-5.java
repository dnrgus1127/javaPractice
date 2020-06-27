package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Gui extends JFrame {

	ImageIcon imgI = new ImageIcon("images/apple.jpg");
	Image img = imgI.getImage();
	double sizeWidth = img.getWidth(this);
	double sizeHeight  = img.getHeight(this);
	
	private MyPanel mp = new MyPanel();
	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);
		mp.setFocusable(true);
		mp.requestFocus();
		MyKeyListener m1 = new MyKeyListener();
		mp.addKeyListener(m1);
		
		setSize(300, 300);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			g.drawImage(img,10,10,(int)sizeWidth,(int)sizeHeight,this);
			

		}
	}
	class MyKeyListener extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ADD) {
				sizeWidth /= 100;
				sizeWidth *= 110;
				sizeHeight /= 100;
				sizeHeight *= 110;
				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
				sizeWidth /= 100;
				sizeWidth *= 90;
				sizeHeight /= 100;
				sizeHeight *= 90;
				repaint();
			}
			
		}

		
		
		
	}



	public static void main(String[] args) {
		new Gui();
	}

}