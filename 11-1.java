package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {


	private JButton btn = new JButton("��ư");
	public Gui() {
		setTitle("CheckBox");
		Container Ct = getContentPane();
		Ct.setLayout(new FlowLayout());
		JCheckBox ButtonOff = new JCheckBox("��ư ��Ȱ��ȭ");
		JCheckBox ButtonUnVisiable = new JCheckBox("��ư ���߱�");
		
		ButtonOff.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					btn.setEnabled(false);
				}
				else
				{
					btn.setEnabled(true);
				}
			}
		});
		ButtonUnVisiable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					btn.setVisible(false);
				}
				else
				{
					btn.setVisible(true);
				}
			}
		});
		Ct.add(ButtonOff);
		Ct.add(ButtonUnVisiable);
		Ct.add(btn);

		// Ct.setFocusable(true);
		// Ct.requestFocus();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 200);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Gui();
	}

}
