package javaP;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {

	private String str = "jhghg";
	private int length = str.length();
	JSlider Js = new JSlider(JSlider.HORIZONTAL, 0, 100, length);
	private JTextArea jta = new JTextArea(str, 20, 5);

	public Gui() {
		setTitle("TextArea Practice Frame");
		Container Ct = getContentPane();
		setLayout(null);

		jta.addKeyListener(new MyKey());
		jta.setSize(260, 100);
		jta.setLocation(10, 10);

		Js.setPaintLabels(true);
		Js.setPaintTicks(true);
		Js.setMajorTickSpacing(20);
		Js.setMinorTickSpacing(5);
		Js.setLocation(10, 130);
		Js.setSize(260, 50);
		Js.addChangeListener(new MyChangeListener());

		Ct.add(jta);
		Ct.add(Js);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		// Ct.setFocusable(true);
		// Ct.requestFocus();
	}

	class MyKey extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			JTextArea a = (JTextArea) e.getSource();
			Js.setValue(a.getText().length());
			if(a.getText().length() >= 100)
			{
				a.setText(a.getText().substring(0,100));
			}

		}
	}

	class MyChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider j = (JSlider) e.getSource();
			if (jta.getText().length() <= j.getValue()) {
				j.setValue(jta.getText().length());
			} else {
				jta.setText(jta.getText().substring(0, jta.getText().length() - 1));
			}
		}

	}

	public static void main(String[] args) {
		new Gui();
	}

}