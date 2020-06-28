
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
		mp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e ) {
				Point p = e.getPoint();
				th = new Thread(new Bubble(mp,p));
				th.start();
			}
		});

		setSize(500, 400);
		setVisible(true);

	}

	class MyPanel extends JPanel {

		public MyPanel() {

		}
	}

	public static void main(String[] args) {
		new Gui();
	}
}

class Bubble implements Runnable {

	private Point p;
	private JPanel J;
	ImageIcon imgI = new ImageIcon("images/chicken.jpg");

	public Bubble(JPanel J, Point p) {
		this.p = p;
		this.J = J;
	}

	public void run() {
		JLabel img = new JLabel(imgI);
		img.setLocation(p);
		img.setSize(60, 60);
		J.add(img);
		while (true) {
			if (p.y <= -5) {
				img.setSize(0,0);
				return;
			}
			img.setLocation(p.x, p.y - 5);
			p = img.getLocation();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				return;
			}

		}
	}
}
