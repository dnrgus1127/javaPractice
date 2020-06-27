package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {

	MyLabel mb = new MyLabel();
	Thread th = new Thread(mb);
	
	public Gui() {
		setTitle("½Ç½À 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Container c = getContentPane();
		mb.setFont(new Font("Arial",Font.PLAIN,50));
		mb.setHorizontalAlignment(JLabel.CENTER);
		c.add(mb,BorderLayout.CENTER);
		th.start();

		setSize(300, 300);
		setVisible(true);

	}
	class MyLabel extends JLabel implements Runnable{
		
		@Override
		public void run() {
			while(true) {
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int min = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);
				super.setText(hour+" : "+min+" : "+ second);	
			}
						
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