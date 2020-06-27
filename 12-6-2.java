package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Gui extends JFrame {

	private MyPanel mp = new MyPanel();

	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);

		setSize(300, 300);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			int[] x = { 0, getWidth() / 2, getWidth(), getWidth() / 2 };
			int[] y = { getHeight() / 2, 0, getHeight() / 2, getHeight() };
			super.paintComponent(g);
			g.drawPolygon(x, y, 4);
			for (int i = 1; i < 10; i++) {
				x[0] += 10;
				y[1] += 10;
				x[2] -= 10;
				y[3] -= 10;
				g.drawPolygon(x, y, 4);
			}

		}
	}

	public static void main(String[] args) {
		new Gui();
	}

}