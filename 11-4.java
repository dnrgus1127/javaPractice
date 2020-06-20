package javaP;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {

	private String[] money = { "오만원", "만원", "천원", "500원", "100원", "50원", "10원", "1원" }; // JLabel 이름
	private int[] coin = { 50000, 10000, 1000, 500, 100, 50, 10, 1 }; // 나눌 돈 단위
	private JTextField[] tf = new JTextField[8]; // TextField 배열
	private int inMoney = 0; // Text필드에 입력된 돈
	private JCheckBox[] cb = new JCheckBox[8]; // 체크박스 배열
	private boolean[] ch = { false, false, false, false, false, false, false, true }; // ch박스 상태에 따라 나눌지 말지 T/F여부

	public Gui() {
		setTitle("Money Changer");
		Container Ct = getContentPane();
		setLayout(null);

		JLabel la = new JLabel("금액"); //금액라벨
		la.setLocation(40, 20);
		la.setSize(30, 20);
		Ct.add(la);

		JTextField moneyText = new JTextField(20); // 금액 입력 필드
		moneyText.setLocation(80, 20);
		moneyText.setSize(120, 20);
		Ct.add(moneyText);

		JButton btn = new JButton("계산"); // 계산 버튼
		btn.setLocation(210, 20);
		btn.setSize(60, 20);
		Ct.add(btn);

		btn.addActionListener(new ActionListener() { // 계산 버튼 입력시 ch의 T/F여부에 따라 돈 계산
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
		JLabel[] jl = new JLabel[8]; // money배열 원소를 이름으로 라벨 배치
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

		MyItemListener listener = new MyItemListener(); // 체크박스 cb에 공통으로 들어갈 이벤트 리스너
		for (int i = 0; i < 7; i++) {  // 체크박스 배열과 리스너 연결
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
			if (e.getStateChange() == ItemEvent.SELECTED) { // 체크박스 선택시 ch를 True로 미선택시 ch를 False로 설정
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