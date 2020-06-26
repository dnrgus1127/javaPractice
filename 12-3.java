package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class Gui extends JFrame {
	
	private Point Start = new Point();
	private ImageIcon imgI = new ImageIcon("images/apple.jpg");
	private JLabel jl = new JLabel(imgI);
	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container C = getContentPane();
		setLayout(null);
		jl.setLocation(30,30);
		jl.setSize(imgI.getIconWidth(),imgI.getIconHeight());
		MyMouseListener m1 = new MyMouseListener();
		jl.addMouseMotionListener(m1);
		jl.addMouseListener(m1);
		
		add(jl);

		
		setSize(300, 300);
		setVisible(true);

	}
	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			Start = e.getPoint();
		}
		/*public void mouseReleased(MouseEvent e) {
			Point endP = e.getPoint();
			Component la = (JComponent)(e.getSource());
			Point p = la.getLocation();
			la.setLocation(p.x + endP.x - Start.x, p.y + endP.y - Start.y);
			la.getParent().repaint(); // 움직인 la의 위치에 다시 그리기
		}*/
		
		public void mouseDragged(MouseEvent e) {
			Point End = e.getPoint();
			Point p = jl.getLocation();
			jl.setLocation(p.x + End.x - Start.x,p.y + End.y - Start.y);
			jl.getParent().repaint();
		}
	}
	public static void main(String[] args) {
		new Gui();
	}

}