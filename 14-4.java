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
		super.setLocation(500,500);
	}
	public void createMenu() {
		JToolBar toolBar = new JToolBar("����");
		JTextField jf = new JTextField();
		toolBar.setBackground(Color.LIGHT_GRAY);
		
		Container c = getContentPane();
		toolBar.add(new JLabel("�й� : "));
		jf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char code = e.getKeyChar();
				if(code<48 || code >57) {
					JOptionPane.showMessageDialog(c,code + "���� ���� �ƴմϴ�. \n ���ڸ� �Է��ϼ��� ","���",JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
				
			}
		});
		toolBar.add(jf);
		add(toolBar,BorderLayout.SOUTH);
		
		
		
	}
	public static void main(String[] args) {
		new Gui();
	}


}
