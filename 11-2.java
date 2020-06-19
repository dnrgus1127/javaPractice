package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {

	private Vector<String> str = new Vector<String>();
	public Gui() {
		setTitle("CheckBox");
		Container Ct = getContentPane();
		Ct.setLayout(new FlowLayout());
		
		JTextField input = new JTextField(10);
		JComboBox<String> CBox = new JComboBox<String>(str);
		CBox.setSize(50,50);
		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField j = (JTextField)e.getSource();
				CBox.addItem(j.getText());
				j.setText("");
				
			}
			
		});

		
		
		
		
		
		Ct.add(input);
		Ct.add(CBox);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		// Ct.setFocusable(true);
		// Ct.requestFocus();
	}

	public static void main(String[] args) {
		new Gui();
	}

}
