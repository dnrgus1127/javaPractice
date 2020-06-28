package javaP;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {

	private String[] str = { "File", "Game", "Page", "Tool" };

	public Gui() {
		setTitle("실습 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		Container c = getContentPane();

		createpage();
		setVisible(true);
		super.setLocation(800, 400);
	}

	public void createpage() {
		JLabel mai = new JLabel("가나다라마바사");
		JMenuBar jb = new JMenuBar();
		JMenu[] jm = new JMenu[4];
		for (int i = 0; i < 4; i++) {
			jm[i] = new JMenu(str[i]);
			jb.add(jm[i]);
		}
		JMenuItem load = new JMenuItem("Load"); // 파일 불러오기
		JMenuItem Num = new JMenuItem("Num"); // 숫자게임
		JMenuItem javaPage = new JMenuItem("JavaPage");// 자바소개 아이템
		JMenuItem MyPage = new JMenuItem("MyPage");// 자기소개 아이템
		JMenuItem Drawing = new JMenuItem("Drawing"); // 그림판 메뉴아이템
		JMenuItem Text = new JMenuItem("Text"); // 메모장 아이템
		jm[0].add(load); // File 메뉴 Load 아이템

		jm[1].add(Num); // Game 메뉴 Num

		jm[2].add(javaPage);
		jm[2].addSeparator(); // 분리선
		jm[2].add(MyPage);

		jm[3].add(Drawing);
		jm[3].addSeparator();
		jm[3].add(Text);

		setJMenuBar(jb); // jb를 메뉴바로 설정
		add(mai);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg images", "jpg");
				chooser.setFileFilter(filter);

				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);

				}
				String filePath = chooser.getSelectedFile().getPath();
				System.out.println(filePath);

			}

		});
		DrawingPan Dp = new DrawingPan(this, "그림판");
		Drawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dp.setVisible(true);
			}
		});
		NumGame ng = new NumGame(this, "game");
		Num.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ng.setVisible(true);

			}

		});
	}

	class DrawingPan extends JDialog {
		Vector<Point> startP = new Vector<Point>();
		Vector<Point> EndP = new Vector<Point>();
		Vector<Color> lineColor = new Vector<Color>();
		Color selec;

		public DrawingPan(JFrame j, String str) {
			super(j, str, true);
			setSize(300, 300);
			setLayout(new BorderLayout());
			JButton clr = new JButton("SET COLOR");
			clr.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					getSelec();

				}

			});
			add(clr, BorderLayout.NORTH);

			MyPanel mp = new MyPanel();
			mp.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					startP.add(e.getPoint());

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					EndP.add(e.getPoint());
					lineColor.add(selec);
					mp.repaint();

				}

			});
			add(mp);

		}

		public void getSelec() {
			selec = JColorChooser.showDialog(null, "Color", Color.red);
			if (selec == null) {
				selec = Color.blue;
			}

		}

		class MyPanel extends JPanel {
			public MyPanel() {

			}

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 0; i < startP.size(); i++) {
					g.setColor(lineColor.get(i));
					g.drawLine(startP.get(i).x, startP.get(i).y, EndP.get(i).x, EndP.get(i).y);
				}

			}
		}

	}

	class NumGame extends JDialog {
		JLabel Time = new JLabel("Time : 0");
		int[] number = new int[30];
		Vector<Point> numPoint = new Vector<Point>();
		JLabel[] num = new JLabel[30];
		int nowNum = 1;
		timer gameTh = new timer(Time);

		public NumGame(JFrame j, String str) {
			super(j, str, true);
			setSize(300, 300);
			setLayout(null);
			Time.setSize(100, 40);
			Time.setLocation(40, 220);
			Time.setOpaque(true);
			Time.setBackground(Color.LIGHT_GRAY);
			Time.setHorizontalAlignment(JLabel.CENTER);
			Time.setFont(new Font("Arial",Font.PLAIN,20));
			add(Time);
			for (int i = 0; i < 30; i++) {
				number[i] = i + 1;
				Point ran = new Point(0, 0);
				ran.x = (int) (Math.random() * 250);
				ran.y = (int) (Math.random() * 200);
				numPoint.add(ran);
			}

			for (int i = 0; i < 30; i++) {
				num[i] = new JLabel(Integer.toString(number[i]));
				num[i].setLocation(numPoint.get(i));
				num[i].setSize(20, 20);
				num[i].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						JLabel j = (JLabel) e.getSource();
						if (j.getText().equals("1")) {
							gameTh.start();
						}
						if (j.getText().equals("30")) {
							gameTh.setEnd();
						}
						if (j.getText().equals(Integer.toString(nowNum))) {
							nowNum += 1;
							j.setSize(0, 0);
						}

					}
				});
				add(num[i]);
			}

		}

		class timer extends Thread {
			JLabel j;
			boolean gamEnd = false;

			public timer(JLabel j) {
				this.j = j;
			}

			@Override
			public void run() {
				int i = 0;
				while (true) {
					j.setText("Time: " + Integer.toString(i));
					if (gamEnd == true) {
						return;
					}
					try {
						i++;
						sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
				}

			}

			public void setEnd() {
				gamEnd = true;
			}

		}
	}

	public static void main(String[] args) {
		new Gui();
	}

}
