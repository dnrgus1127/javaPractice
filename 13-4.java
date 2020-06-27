package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {

	MyLabel mb = new MyLabel(this);
	Thread th = new Thread(mb);

	public Gui() {
		setTitle("½Ç½À 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Container c = getContentPane();
		mb.setFont(new Font("Arial", Font.PLAIN, 50));
		mb.setHorizontalAlignment(JLabel.CENTER);
		c.add(mb, BorderLayout.CENTER);
		th.start();
		setSize(300, 300);
		setVisible(true);
		this.setLocation(400, 400);
	}

	class MyLabel extends JLabel implements Runnable {
		JFrame j;
		public MyLabel(JFrame j) {
			this.j = j;
		}
		@Override
		public void run() {
			while (true) {
				Point p = j.getLocation();
				if(p.x==400)
					p.x+=10;	
				else {
					p.x-=10;
				}
				j.setLocation(p);
				
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int min = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);
				super.setText(hour + " : " + min + " : " + second);
				
			}

		}

	}

	public static void main(String[] args) {
		new Gui();
	}
}
