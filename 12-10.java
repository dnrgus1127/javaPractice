package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Gui extends JFrame {

	private MyPanel mp = new MyPanel();
	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);
		setLayout(new FlowLayout());
	
		
		setSize(300, 300);
		setVisible(true);

	}
	
	class MyPanel extends JPanel{
		ImageIcon imgI = new ImageIcon("images/back.jpg");
		Image img = imgI.getImage();
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setClip(0,0,getWidth()/2-5,getHeight()/2-5);
			g.drawImage(img,0,0,getWidth(),getHeight(),this);
			g.setClip(getWidth()/2+5,0,getWidth(),getHeight()/2-5);
			g.drawImage(img,0,0,getWidth(),getHeight(),this);
			g.setClip(0,getHeight()/2+5,getWidth()/2-5,getHeight());
			g.drawImage(img,0,0,getWidth(),getHeight(),this);
			g.setClip(getWidth()/2+5,getHeight()/2+5,getWidth(),getHeight());
			g.drawImage(img,0,0,getWidth(),getHeight(),this);
			
		}
	}
	public static void main(String[] args) {
		new Gui();
	}

}