package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {

	private MyPanel mp = new MyPanel();
	public Point p = new Point(20, 20);
	private Thread th = new Thread(new MyThread(p,mp));

	public Gui() {
		setTitle("½Ç½À 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mp.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				th.start();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		setContentPane(mp);

		setSize(500, 300);
		setVisible(true);

	}

	class MyPanel extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.setColor(Color.RED);
			g.drawOval(p.x, p.y,50,50);
		}
	}

	public static void main(String[] args) {
		new Gui();
	}
}

class MyThread implements Runnable {
	Point p;
	Component mp;

	public MyThread(Point p, Component mp) {
		this.p = p;
		this.mp = mp;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(400);

				mp.getParent().repaint();
			} catch (InterruptedException e) {return;}
			p.x =(int)(Math.random()*(mp.getWidth()-50));
			p.y = (int)(Math.random()*(mp.getHeight()-50));
		}
	}
}