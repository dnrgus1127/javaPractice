package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Gui extends JFrame {

	private MyPanel mp = new MyPanel();

	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);

		setSize(300, 300);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			double w = getWidth()/10.0;
			double h = getHeight()/10.0;
			for(int i=1 ; i<10;i++) {
				g.drawLine((int)(0+w*i), 0, (int)(0+w*i), getHeight());
				g.drawLine(0,(int)(0+h*i),getWidth(),(int)(0+h*i));
				
			}
			

		}
	}

	public static void main(String[] args) {
		new Gui();
	}

}