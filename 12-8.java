package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Gui extends JFrame {

	private MyPanel mp = new MyPanel();
	private Vector<Circle> Vc = new Vector<Circle>();
	private int radius = 0;
	public static double getDistance(int x, int y, int x1, int y1) {

		return Math.sqrt(Math.pow(Math.abs(x1 - x), 2) + Math.pow(Math.abs(y1 - y), 2));

	}

	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);
		MyMouseListener m1 = new MyMouseListener();
		mp.addMouseListener(m1);
		mp.addMouseMotionListener(m1);

		setSize(300, 300);
		setVisible(true);

	}

	class MyMouseListener extends MouseAdapter {
		private Point center = new Point();

		@Override
		public void mousePressed(MouseEvent e) {
			center = e.getPoint();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Point Last = e.getPoint();
			radius = (int)getDistance(center.x,center.y,Last.x,Last.y);
			Vc.add(new Circle(center,radius));
			repaint();
		}

	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i =0;i<Vc.size();i++) {
				g.setColor(Color.RED);
				g.drawOval(Vc.elementAt(i).Start.x, Vc.elementAt(i).Start.y, Vc.elementAt(i).End.x, Vc.elementAt(i).End.y);
			}
		}
	}

	class Circle {
		public Point Start= new Point();
		public Point End = new Point();
		public Circle(Point S, int radius) {
			Start = new Point(S.x-radius,S.y-radius);
			End = new Point(S.x+radius,S.y+radius);
		}
	}

	public static void main(String[] args) {
		new Gui();
	}

}