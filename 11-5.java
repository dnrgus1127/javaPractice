package javaP;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {
		
	private JLabel jl = new JLabel("");
	public Gui() {
		setTitle("Money Changer");
		Container Ct = getContentPane();
		setLayout(new FlowLayout());
		
		JSlider js = new JSlider(JSlider.HORIZONTAL,100,200,130); // �����̴� ���� (����, 100���� 200���� ���� ��ġ 130)
		js.setMajorTickSpacing(20);
		js.setPaintLabels(true);
		js.setPaintTicks(true);
		js.addChangeListener(new MyChangeListener());
		Ct.add(js);
		
		jl.setOpaque(true); // JLabel jl�� ���� ���� ���� ������ ����
		jl.setText(Integer.toString(js.getValue()));
		jl.setBackground(Color.GREEN);
		Ct.add(jl);
		



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		// Ct.setFocusable(true);
		// Ct.requestFocus();
	}
	class MyChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider j = (JSlider)e.getSource();
			jl.setText(Integer.toString(j.getValue()));
		}
		
	}


	public static void main(String[] args) {
		new Gui();
	}

}