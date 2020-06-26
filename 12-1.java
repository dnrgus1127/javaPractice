package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class Gui extends JFrame {

	private drawPanel dp = new drawPanel();
	private boolean Hide = false;
	public Gui() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(dp);

		
		setSize(300, 300);
		setVisible(true);

	}

	class drawPanel extends JPanel {
		private ImageIcon imgIcon = new ImageIcon("images/back.jpg");
		private Image img = imgIcon.getImage();
		private JButton jb = new JButton("Hide/Show");
		
		public drawPanel() {
			setLayout(new FlowLayout());
			add(jb);
			jb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton Jbut = (JButton)e.getSource();
					if(Hide == false)
					{
						Hide = true;
					}
					else
					{
						Hide = false;
					}
					
					Jbut.getParent().repaint();
					
				}
				
			});
			
			
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(Hide == false) {
				g.drawImage(img, 0, 0,getWidth(),getHeight(), this);
			}
			
		}
		
	}

	public static void main(String[] args) {
		new Gui();
	}

}