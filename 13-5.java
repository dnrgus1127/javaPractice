
package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {
	
	ImageIcon imgI = new ImageIcon("images/chicken.jpg");

	JLabel Chk = new JLabel(imgI);
	JLabel Bull = new JLabel();
	JLabel user = new JLabel();
	Thread th = new Thread(new Chicken(Chk));
	Thread th2 = new Thread(new Bulit(Bull,Chk));
	private Point bul_p = new Point(245,275);
	public Gui() {
		setTitle("실습 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Container C = getContentPane();
		Chk.setLocation(0,0);
		Chk.setSize(imgI.getIconWidth(),imgI.getIconHeight());
		
		user.setLocation(210,285);
		user.setSize(80,80);
		user.setBackground(Color.black);
		user.setOpaque(true);
		
		Bull.setLocation(bul_p);
		Bull.setSize(10,10);
		Bull.setBackground(Color.RED);
		Bull.setOpaque(true);
		
		C.add(Chk);
		C.add(user);
		C.add(Bull);
		C.setFocusable(true);
		C.requestFocus();
		C.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER && th2.isAlive() != true)
				{
					th2 = new Thread(new Bulit(Bull,Chk));
					th2.start();
				}
			}
		});
		th.start();
		
		setSize(500, 400);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Gui();
	}
}
class Chicken implements Runnable{

	JLabel t;
	Point p;
	boolean right = true; 
	public Chicken(JLabel t) {
		this.t = t;
		this.p = t.getLocation();
	}
	@Override
	public void run() {
		while(true) {
			p = t.getLocation();
			if(right ==true) {
				if(p.x<=420)
					p.x +=5;
				else {
					right = false;
				}
			}
			else {
				if(p.x>0) {
					p.x-=5;
				}
				else {
					right = true;
				}
			}
			t.setLocation(p);
			try{
				Thread.sleep(20);
			}catch (InterruptedException e) {return;}
		}
		
	} // 치킨 스레드
}
class Bulit implements Runnable{
	JLabel bull;
	JLabel Chk;
	public Bulit(JLabel bull,JLabel Chk) {
		this.bull = bull;
		this.Chk = Chk;
	}
	public void run() {
		while(true) {
			Point p = bull.getLocation();
			Point ch = Chk.getLocation();
			if(p.y != 0)
			{
					bull.setLocation(p.x,p.y-5);
					if(p.x <= ch.x+60 && p.x >= ch.x && p.y <= ch.y+60)
					{
						bull.setLocation(245,275);
						Chk.setLocation(0,0);
						return;
					}
			}
			else {
				bull.setLocation(245,275);
				return;
			}
			try {
				Thread.sleep(20);
			}catch(InterruptedException e) {return;}
			
		}
	}
	
}
	

