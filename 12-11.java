package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Gui extends JFrame {

	private int[] star = { 20, 20, 20, 20 };
	private float[] per = { 25, 25, 25, 25 };
	private String[] str = { "apple", "cherry", "strawberry", "prune" };
	public JTextField[] Jf = new JTextField[4];
	private MyPanel mp = new MyPanel();
	private centerP cp = new centerP();
	
	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		add(mp, BorderLayout.NORTH);
		add(cp, BorderLayout.CENTER);

		setSize(600, 300);
		setVisible(true);

	}

	class MyPanel extends JPanel {

	

	MyActionListener m1 = new MyActionListener();
		public MyPanel() {
			setLayout(new FlowLayout());
			setBackground(Color.LIGHT_GRAY);
			for (int i = 0; i < 4; i++) {
				add(new JLabel(str[i]));
				Jf[i] = new JTextField(7);
				Jf[i].setText(String.valueOf(star[i]));
				Jf[i].addActionListener(m1);
				add(Jf[i]);
			}

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

		}
	}
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField j = (JTextField)e.getSource();
			for(int i =0;i<4;i++) {
				star[i] = Integer.parseInt(Jf[i].getText());
			}
			float prusum = star[0] + star[1] + star[2] + star[3];
			for (int i = 0; i < 4; i++) {
				
				per[i] = star[i] * 100 / prusum;
			}
			
			repaint();
		}
		
	}

	class centerP extends JPanel {
		public float prusum = star[0] + star[1] + star[2] + star[3];

		public centerP() {
			setLayout(new FlowLayout());
			for (int i = 0; i < 4; i++) {
				per[i] = star[i] * 100 / prusum;
			}

			for (int i = 0; i < 4; i++) {
				String s = str[i] + " " + per[i] + "%   ";
				add(new JLabel(s));
			}

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			float sum = 0;
			float i = 360 * per[0] / 100;
			g.fillArc(220, 40, 170, 170, 0, (int) i);
			sum += (int) i;
			i = 360 * per[1] / 100;
			g.setColor(Color.BLUE);
			g.fillArc(220, 40, 170, 170, (int) sum, (int) i);
			sum += (int) i;
			i = 360 * per[2] / 100;
			g.setColor(Color.YELLOW);
			g.fillArc(220, 40, 170, 170, (int) sum, (int) i);
			sum += (int) i;
			i = 360 * per[3] / 100;
			g.setColor(Color.GREEN);
			g.fillArc(220, 40, 170, 170, (int) sum, (int) i);
		}
	}

	public static void main(String[] args) {
		new Gui();
	}

}