package javaP;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Gui extends JFrame {

	private ButtonGroup group = new ButtonGroup();
	private File dir = new File("C:\\Users\\WIN10\\eclipse-workspace\\javaP\\images");
	private String[] str = dir.list();
	private JLabel jl = new JLabel("");
	private Vector<ImageIcon> imagV = new Vector<ImageIcon>();
	private int start = 0;
	private boolean rightCk = true;
	private JRadioButton left = new JRadioButton("left");
	private JRadioButton right = new JRadioButton("right", true);

	public Gui() {
		setTitle("TextArea Practice Frame");
		Container Ct = getContentPane();
		setLayout(new BorderLayout());

		System.out.println(str[0]);
		left.addItemListener(new MyItemListener());
		right.addItemListener(new MyItemListener());
		group.add(left);
		group.add(right);

		for (int i = 0; i < str.length; i++) {
			imagV.add(new ImageIcon("images/" + str[i]));
		}
		jl.setIcon(imagV.elementAt(start));
		jl.setHorizontalAlignment(JLabel.CENTER);

		JPanel jp = new JPanel();
		jp.add(left);
		jp.add(right);

		jl.addMouseListener(new MyMouseListener());

		Ct.add(jp, BorderLayout.NORTH);
		Ct.add(jl, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		// Ct.setFocusable(true);
		// Ct.requestFocus();
	}

	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.DESELECTED)
				return;
			if (right.isSelected()) {
				rightCk = true;
			} else if (left.isSelected()) {
				rightCk = false;
			}

		}

	}

	class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			JLabel j = (JLabel) e.getSource();
			if (rightCk == true) {
				start += 1;
				System.out.println("1");
				j.setIcon(imagV.elementAt(start % str.length));
			} else {
				if(start == 0) {
					start = 7;
				}
				else
				{
					start -= 1;
				}
				
				System.out.println("0");
				j.setIcon(imagV.elementAt(start % str.length));
			}

		}
	}

	public static void main(String[] args) {
		new Gui();
	}

}