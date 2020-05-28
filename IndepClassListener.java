package baekjoon;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;

public class IndepClassListener extends JFrame{
	public IndepClassListener() {
		setTitle("이벤트 리스너 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container C = getContentPane();
		C.setBackground(Color.GRAY);
		C.setLayout(new FlowLayout(30));
		JButton btn = new JButton("Action");
		btn.addActionListener(new MyActionListener());
		C.add(btn);
		
		
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String[] args) throws IOException {
		new IndepClassListener();
	}
}
class MyActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JButton b= (JButton)e.getSource();
		if(b.getText().contentEquals("Action"))
			b.setText("액션");
		else
			b.setText("Action");
	}
}