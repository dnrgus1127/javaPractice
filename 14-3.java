package javaP;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {


	
	public Gui() {
		setTitle("�ǽ� 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();
		
		setSize(300, 200);
		setVisible(true);

	}
	public void createMenu() {
		JToolBar toolBar = new JToolBar("����");
		toolBar.setBackground(Color.LIGHT_GRAY);
		
		JButton btn = new JButton("����");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null,"���� �����Ͻðڽ��ϱ�?","����?",JOptionPane.YES_NO_OPTION);
				if( res != JOptionPane.YES_OPTION) {
					return;
				}
				else {
					System.exit(0);
				}
				
			}

		});
		toolBar.add(btn);
		add(toolBar,BorderLayout.NORTH);
		
		
		
	}
	public static void main(String[] args) {
		new Gui();
	}


}
