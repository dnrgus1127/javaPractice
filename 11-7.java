package javaP;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {

	
	private JSlider Js = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
	private JLabel jl = new JLabel("I Love Java");
	
	public Gui() {
		setTitle("TextArea Practice Frame");
		Container Ct = getContentPane();
		setLayout(new BorderLayout());
		
		Js.setMajorTickSpacing(20);
		Js.setMinorTickSpacing(5);
		Js.setPaintLabels(true);
		Js.setPaintTicks(true);
		Js.addChangeListener(new MyChangeListener());
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setFont(new Font("Arial",Font.PLAIN,50));
		Ct.add(Js,BorderLayout.NORTH);
		Ct.add(jl,BorderLayout.CENTER);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		// Ct.setFocusable(true);
		// Ct.requestFocus();
	}

	class MyChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider j = (JSlider) e.getSource();
			jl.setFont(new Font("Arial",Font.PLAIN,j.getValue()));
		}

	}

	public static void main(String[] args) {
		new Gui();
	}

}