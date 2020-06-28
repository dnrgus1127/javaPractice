
package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {

	MyPanel mp = new MyPanel();
	Thread th;

	public Gui() {
		setTitle("½Ç½À 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);

		setSize(500, 400);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		ImageIcon imgI = new ImageIcon("images/back.jpg");
		Image img = imgI.getImage();
		Vector<Point> snow = new Vector<Point>();
		Thread th;

		public MyPanel() {
			for (int i = 0; i < 50; i++) {
				Point p = new Point();
				p.x = (int) (Math.random() * 500);
				p.y = (int) (Math.random() * 400);
				snow.add(p);
			}
			th = new Thread(new snowDown(snow, this));
			th.start();

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			for (int i = 0; i < snow.size(); i++) {
				g.setColor(Color.WHITE);
				g.fillOval(snow.get(i).x, snow.get(i).y, 10, 10);
			}
		}
	}

	class snowDown implements Runnable {
		Vector<Point> snow;
		Vector<Point> ins = new Vector<Point>();
		JPanel J;

		public snowDown(Vector<Point> snow, JPanel J) {
			this.snow = snow;
			this.J = J;
		}

		public void run() {
			while (true) {
				ins = new Vector<Point>();
				for (int i = 0; i < snow.size(); i++) {
					ins.add(snow.get(i));
				}
				snow.clear();
				for (int i = 0; i < ins.size(); i++) {
					Point p = ins.get(i);
					if(p.y == 400) {
						p.x = (int) (Math.random() * 500);
						p.y = -5;
					}else {
						p.y += 1;
					}
					
					snow.add(p);
				}
				J.repaint();
				try {
					Thread.sleep(20);
				}catch (InterruptedException e) {return;}
			}
		}
	}

	public static void main(String[] args) {
		new Gui();
	}
}
