package javaP;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

	public Gui() {
		setTitle("���� ������");
		Container Ct = getContentPane();
		// Ct.add(new JButton("click"), BorderLayout.NORTH);
		Ct.add(new North(), BorderLayout.NORTH);
		Ct.add(new Center(), BorderLayout.CENTER);
		Ct.add(new South(),BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 300);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Gui();
	}

}

class North extends JPanel {
	public North() {
		setLayout(new FlowLayout());
		add(new JLabel("�����Է�"));
		add(new JTextField(20));
		setBackground(Color.lightGray);

	}
}

class Center extends JPanel {
	public Center() {
		String str = "";
		setLayout(new GridLayout(4, 4, 5, 5));
		for (int i = 0; i < 10; i++) {
			str = Integer.toString(i);
			add(new JButton(str));
		}
		add(new JButton("CE"));
		add(new JButton("���"));
		JButton[] Jb = {new JButton("+"),new JButton("-"),new JButton("x"),new JButton("/")};
		for(int i =0;i<4;i++)
		{
			Jb[i].setBackground(Color.CYAN);
			add(Jb[i]);
		}
	}
}

class South extends JPanel {
	public South() {
		setLayout(new FlowLayout());
		add(new JLabel("��� ���"));
		add(new JTextField(20));
		setBackground(Color.YELLOW);
	}
}