package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Gui extends JFrame {

	
	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container C = getContentPane();
		setLayout(new FlowLayout());
		BlueLabel m1 = new BlueLabel("Big Hello");
		BlueLabel m2 = new BlueLabel("Hello");
		
		m1.setFont(new Font("Arial",Font.ITALIC,50));
		m2.setFont(new Font("Arial",Font.PLAIN,10));
		m1.setOpaque(true);
		m2.setForeground(Color.YELLOW);
		m1.setForeground(Color.magenta);
		m2.setOpaque(true);
		C.add(m2);
		C.add(m1);
		
		setSize(300, 300);
		setVisible(true);

	}
	class BlueLabel extends JLabel{
		public BlueLabel(String str) {
			super(str);
		}
		public void setBackground(Color c) {
			super.setBackground(Color.BLUE);
		}
	}
	

	public static void main(String[] args) {
		new Gui();
	}

}