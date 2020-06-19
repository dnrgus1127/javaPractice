package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {

	private String[] money = { "오만원", "만원", "천원", "500원", "100원", "50원", "10원", "1원" };
	private int[] coin = { 50000, 10000, 1000, 500, 100, 50, 10, 1 };
	private JTextField[] tf = new JTextField[8];
	private int inMoney = 0;

	public Gui() {
		setTitle("Money Changer");
		Container Ct = getContentPane();
		setLayout(null);

		JLabel la = new JLabel("금액");
		la.setLocation(40, 20);
		la.setSize(30, 20);
		Ct.add(la);

		JTextField moneyText = new JTextField(20);
		moneyText.setLocation(80, 20);
		moneyText.setSize(120, 20);
		Ct.add(moneyText);

		JButton btn = new JButton("계산");
		btn.setLocation(210, 20);
		btn.setSize(60, 20);
		Ct.add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inMoney = Integer.parseInt(moneyText.getText());
				//moneyText.setText("");
				int mod=inMoney;
				for(int i=0;i<8;i++)
				{
					tf[i].setText(Integer.toString(mod/coin[i]));
					mod = mod%coin[i];
				}
			}

		});
		JLabel[] jl = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			jl[i] = new JLabel(money[i]);
			jl[i].setLocation(60, 60 + i * 22);
			jl[i].setSize(70, 20);
			tf[i] = new JTextField(7);
			tf[i].setLocation(120, 60 + i * 22);
			tf[i].setSize(70, 20);
			jl[i].setHorizontalAlignment(JTextField.CENTER);
			tf[i].setHorizontalAlignment(JTextField.CENTER);
			Ct.add(jl[i]);
			Ct.add(tf[i]);
		}

		Ct.setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		// Ct.setFocusable(true);
		// Ct.requestFocus();
	}

	public static void main(String[] args) {
		new Gui();
	}

}