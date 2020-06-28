package javaP;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {


	
	public Gui() {
		setTitle("실습 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLayout(new FlowLayout());
		
		createpage();
		setVisible(true);
		super.setLocation(800,400);
	}
	public void createpage() {
		JButton btn = new JButton("calculate");
		JLabel jl = new JLabel("계산 결과 출력");
		jl.setOpaque(true);
		jl.setBackground(Color.green);
		Calculater CDial = new Calculater(this,"calculate");
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CDial.setVisible(true);
				
				int sum = CDial.getSum();
				jl.setText(Integer.toString(sum));
				
				
				
				
			}
			
		});
		add(btn);
		add(jl);
		
	}
	class Calculater extends JDialog{
		JTextField first_num = new JTextField(11);
		JTextField sec_num = new JTextField(11);
		JButton btn = new JButton("Add");
		private int sum;
		public Calculater(JFrame f , String str) {
			super(f,str,true);
			setTitle(str);
			setSize(200,200);
			super.setLocation(880, 450);
			setLayout(new FlowLayout());
			add(new JLabel("두 수를 더합니다."));
			add(first_num);
			add(sec_num);
			add(btn);
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					sum = Integer.parseInt(first_num.getText()) + Integer.parseInt(sec_num.getText());
					setVisible(false);
				}
				
			});
			
			
		}
		public int getSum() {
			return sum; 
		}
	}
	public static void main(String[] args) {
		new Gui();
	}


}
