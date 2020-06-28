
package javaP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {

	MyPanel mp = new MyPanel();
	Thread th;
	public Gui() {
		setTitle("실습 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);
		mp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				th = new Thread(mp);
				th.start();
				mp.gameStart();
			}
		});
		setSize(300, 200);
		setVisible(true);

	}

	class MyPanel extends JPanel implements Runnable {
		
		boolean game = false;
		JLabel[] num = new JLabel[3];
		JLabel msg = new JLabel("마우스를 클릭할 때 마다 게임합니다.");
		int[] iNum = new int[3];
		public MyPanel() {
			setLayout(null);
			for(int i=0;i<3;i++) {
				num[i] = new JLabel("0");
				num[i].setFont(new Font("Arial",Font.ITALIC,20));
				num[i].setSize(70,40);
				num[i].setHorizontalAlignment(JLabel.CENTER);
				num[i].setLocation(30 + 80* i ,40);
				num[i].setOpaque(true);
				num[i].setBackground(Color.magenta);
				add(num[i]);
			}
			msg.setLocation(25,75);
			msg.setSize(250,100);
			msg.setHorizontalAlignment(JLabel.CENTER);
			add(msg);

			
		}

		synchronized public void gamePause() {
			if(!game) {
				try {
					this.wait();
				}catch(InterruptedException e) {return;}
			}
				
		}

		synchronized public void gameStart() {
			game = true;
			this.notify();
		}

		public void run() {
			while(true) {
				gamePause();
				for(int i=0;i<3;i++) {
					iNum[i] = (int)(Math.random()*4);
					num[i].setText(Integer.toString(iNum[i]));
					try {
						Thread.sleep(200);
					}catch (InterruptedException e) {return;}
				}
				if(iNum[0] == iNum[1] && iNum[1] == iNum[2]) {
					msg.setText("축하합니다!");
				}
				else {
					msg.setText("아쉽군요!");
				}
				game = false;
			}
			
		}
	}

	public static void main(String[] args) {
		new Gui();
	}
}
