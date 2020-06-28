package javaP;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {


	MyPanel JP = new MyPanel();
	public Gui() {
		setTitle("실습 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(JP);

		setSize(300, 200);
		setVisible(true);

	}
	public void createMenu(MyPanel c) {
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("파일");
		JMenuItem fop = new JMenuItem("열기");
		
		fop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG images","jpg");
				chooser.setFileFilter(filter);
				
				int ret = chooser.showOpenDialog(null);
				if(ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.ERROR_MESSAGE);
				}
				else {
					String filePath = chooser.getSelectedFile().getPath();
					c.setPath(filePath);
					repaint();
				}
				return;
				
			}
			
		});
		
		screenMenu.add(fop);
		mb.add(screenMenu);
		
		setJMenuBar(mb);
		
		
	}

	class MyPanel extends JPanel{
		private String Path;
		public MyPanel() {
			createMenu(this);
			
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon imagI = new ImageIcon(Path);
			Image img = imagI.getImage();
			g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
			
		}
		public void setPath(String str) {
			this.Path = str;
		}
	}
	public static void main(String[] args) {
		new Gui();
	}
}
