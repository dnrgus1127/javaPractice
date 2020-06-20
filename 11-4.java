package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {

	private String[] money = { "������", "����", "õ��", "500��", "100��", "50��", "10��", "1��" }; // JLabel �̸�
	private int[] coin = { 50000, 10000, 1000, 500, 100, 50, 10, 1 }; // ���� �� ����
	private JTextField[] tf = new JTextField[8]; // TextField �迭
	private int inMoney = 0; // Text�ʵ忡 �Էµ� ��
	private JCheckBox[] cb = new JCheckBox[8]; // üũ�ڽ� �迭
	private boolean[] ch = { false, false, false, false, false, false, false, true }; // ch�ڽ� ���¿� ���� ������ ���� T/F����

	public Gui() {
		setTitle("Money Changer");
		Container Ct = getContentPane();
		setLayout(null);

		JLabel la = new JLabel("�ݾ�"); //�ݾ׶�
		la.setLocation(40, 20);
		la.setSize(30, 20);
		Ct.add(la);

		JTextField moneyText = new JTextField(20); // �ݾ� �Է� �ʵ�
		moneyText.setLocation(80, 20);
		moneyText.setSize(120, 20);
		Ct.add(moneyText);

		JButton btn = new JButton("���"); // ��� ��ư
		btn.setLocation(210, 20);
		btn.setSize(60, 20);
		Ct.add(btn);

		btn.addActionListener(new ActionListener() { // ��� ��ư �Է½� ch�� T/F���ο� ���� �� ���
			@Override
			public void actionPerformed(ActionEvent e) {
				inMoney = Integer.parseInt(moneyText.getText());
				// moneyText.setText("");
				int mod = inMoney;
				for (int i = 0; i < 8; i++) {
					if (ch[i] == true) {
						tf[i].setText(Integer.toString(mod / coin[i]));
						mod = mod % coin[i];
					} else {
						tf[i].setText("0");
					}
				}
			}

		});
		JLabel[] jl = new JLabel[8]; // money�迭 ���Ҹ� �̸����� �� ��ġ
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

		MyItemListener listener = new MyItemListener(); // üũ�ڽ� cb�� �������� �� �̺�Ʈ ������
		for (int i = 0; i < 7; i++) {  // üũ�ڽ� �迭�� ������ ����
			cb[i] = new JCheckBox();
			cb[i].setLocation(200, 60 + i * 22);
			cb[i].setSize(20, 20);
			cb[i].setBackground(Color.PINK);
			cb[i].addItemListener(listener);
			Ct.add(cb[i]);
		}

		Ct.setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		// Ct.setFocusable(true);
		// Ct.requestFocus();
	}

	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) { // üũ�ڽ� ���ý� ch�� True�� �̼��ý� ch�� False�� ����
				for (int i = 0; i < 8; i++) {
					if (e.getItem() == cb[i]) {
						ch[i] = true;
					}
				}
			} else {
				for (int i = 0; i < 8; i++) {
					if (e.getItem() == cb[i]) {
						ch[i] = false;
					}
				}
			}

		}

	}

	public static void main(String[] args) {
		new Gui();
	}

}