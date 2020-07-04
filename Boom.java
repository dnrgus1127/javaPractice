import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.*;

public class Boom extends JFrame { // ���� ������
	private MyPanel Mp = new MyPanel(); // Game�гΰ� Ui�г��� ���� JPanel ����
	private SettingDialog Sd = new SettingDialog(this, "����"); // ���� ������ ���� ���� ���̾�α� ��ü ����

	public Boom() {
		setTitle("����ã�� ����!");
		setContentPane(Mp);
		CreateMenu(); // �޴� ���� �Լ�
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setVisible(true);
		setLocation(500, 200);
		setResizable(false); // ������ ũ�� ����
	}

	private void CreateMenu() { // �޴� ���� �Լ�
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("Game");
		JMenuItem ReSet = new JMenuItem("NewGame");
		ReSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // NewGame�޴� ���ý� ������ �ʱ�ȭ

				String str = "User: " + Mp.getUser() + " ����: " + Mp.getGP().getWorL() + " Time: "
						+ Mp.getGP().GetTimer() + "��"; // Score.txt ���Ͽ� �Էµ� ���� ���� �α�
				Mp.getGP().GameReSet(); // ���� ��ź ��ġ �� ���� ���� ����
				Mp.getUi().ScoreWrite(str); // Score.txt���Ͽ� ���� �α� �Է�
				Mp.getUi().ScoreRead(); // Score.txt ������ �ٽ� �о��
				Mp.getUi().ScoreRefresh(); //�о��� �ִ� Score.txt���� �����
			}

		});
		JMenu Setting = new JMenu("Setting");
		JMenuItem sett = new JMenuItem("����");
		sett.addActionListener(new ActionListener() { // ���� ��ư ������ ���� ���̾�α� ����

			@Override
			public void actionPerformed(ActionEvent e) { 
				Sd.setVisible(true); //���� ���̾�α� ���̰Լ���

			}

		});
		Setting.add(sett);

		screenMenu.add(ReSet);
		mb.add(screenMenu);
		mb.add(Setting);
		setJMenuBar(mb); //mb�� �޴��� ����
	}

	class SettingDialog extends JDialog { // ��ź ���� ���� ���̾�α�
		private JFrame j;
		private String title;
		private int BoomNum = 5; // ��ź���� 
		
		public SettingDialog(JFrame J, String title) { 
			super(J, title, true);
			this.j = J;
			this.title = title;
			setSize(400, 150);
			setLocation(700, 400);
			setLayout(null);
			JLabel NoBLabel = new JLabel("��ź�� ����");
			NoBLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); //3D�׵θ� ����
			NoBLabel.setHorizontalAlignment(JLabel.CENTER); // ��� ����
			NoBLabel.setBackground(Color.MAGENTA); //����
			NoBLabel.setSize(80, 20);
			NoBLabel.setLocation(50, 20);
			add(NoBLabel);
			ButtonGroup NoB = new ButtonGroup(); // ��ź ���� ����

			JRadioButton N5 = new JRadioButton("15"); //��ź ���� 15��
			N5.setLocation(150, 20);
			N5.setSize(40, 20);
			JRadioButton N20 = new JRadioButton("20", true);//��ź ���� 20��, �⺻���� ����
			N20.setLocation(200, 20);
			N20.setSize(40, 20);
			JRadioButton N30 = new JRadioButton("30");//��ź ���� 30��
			N30.setLocation(250, 20);
			N30.setSize(40, 20);
			JRadioButton N50 = new JRadioButton("50");//��ź ���� 50��
			N50.setLocation(300, 20);
			N50.setSize(40, 20);

			NoB.add(N5);
			NoB.add(N20);
			NoB.add(N30);
			NoB.add(N50);

			add(N5);
			add(N20);
			add(N30);
			add(N50);
			N5.addItemListener(new ItemListener() { // ������ư�� Ŭ���Ǹ� ������ư�� �ؽ�Ʈ�� �о� ��ź�� ������ ���� �ϴ� �̺�Ʈ ������
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});
			N20.addItemListener(new ItemListener() {// ������ư�� Ŭ���Ǹ� ������ư�� �ؽ�Ʈ�� �о� ��ź�� ������ ���� �ϴ� �̺�Ʈ ������
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});
			N30.addItemListener(new ItemListener() {// ������ư�� Ŭ���Ǹ� ������ư�� �ؽ�Ʈ�� �о� ��ź�� ������ ���� �ϴ� �̺�Ʈ ������
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});
			N50.addItemListener(new ItemListener() {// ������ư�� Ŭ���Ǹ� ������ư�� �ؽ�Ʈ�� �о� ��ź�� ������ ���� �ϴ� �̺�Ʈ ������
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});

			JButton setBtn = new JButton("����");
			setBtn.setSize(80, 25);
			setBtn.setLocation(160, 60);
			setBtn.addActionListener(new ActionListener() { //���� ���̾�α� �� ���� ��ư ������ ��ź�� ���� �����ϰ� ������ ���½�Ű�� �̺�Ʈ ������

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton jbtn = (JButton) e.getSource();

					Mp.getGP().setBoomSize(BoomNum);
					String str = "User: " + Mp.getUser() + " ����: " + Mp.getGP().getWorL() + " Time: "
							+ Mp.getGP().GetTimer() + "��"; //���� �α�
					Mp.getGP().GameReSet(); // ��ź�� �ֺ� ���� �缳��
					Mp.getUi().ScoreWrite(str); // ���� �α� ���
					Mp.getUi().ScoreRead(); // Score.txt �ҷ�����
					Mp.getUi().ScoreRefresh(); // ���ھ��� �����
					setVisible(false); // ���̾�α� ����
				}

			});
			add(setBtn);

		}

	}

	public static void main(String[] args) {
		new Boom();
	}
}

class MyPanel extends JPanel { // �����гΰ� ���ھ�(UI) �г��� �ö� �г� 
	private int BoomSize = 50; // �ʱ� ��ź ����
	private GamePanel Gp = new GamePanel(20, 20, BoomSize); // 20*20�� ������ �� BoomSize�� ��ź ���� ������ �����г� ��ü ����
	private UiPanel Up = new UiPanel(); // Ui�г� ��ü ����
	private String UserName = "�͸�"; // ���� �̸�, �÷��̾� �̸� �Է� ���ҽ� ����Ʈ�� �͸�

	public MyPanel() {
		setSize(800, 670);
		setLayout(null);

		Gp.setBounds(0, 0, getWidth() * 7 / 10, getHeight()); // �������� 7/10��ŭ �����г� 

		Up.setBounds(getWidth() * 7 / 10, 0, getWidth() * 3 / 10, getHeight()); // �������� ������ 3/10��ŭ Ui�г�
		add(Gp);
		add(Up);

		UserName = JOptionPane.showInputDialog("�÷��̾� �̸��� �Է��ϼ���!"); //�÷��̾� �̸� �Է¹޴� �˾� ���̾�α�

	}

	public GamePanel getGP() { // �����г� ��ü ��ȯ
		return Gp;
	}

	public UiPanel getUi() { // Ui�г� ��ü ��ȯ
		return Up;
	}

	public String getUser() { //���� �̸� ��ȯ
		return UserName;
	}

}

class GamePanel extends JPanel { // ����ã�� ������ ����Ǵ� ���� �г�
	private Box Btn = new Box();
	private int[][] BoomBox;// ��ź�� �ֺ� ��ź �� ���� üũ�ϴ� 2���� �迭
	private int BoomSize; // ��ź�� ����
	private int row; // ���� �� ��
	private int col; // ���� �� ��
	private Box[][] Jbt; // BoxŸ�� 2���� �迭 Box�� JLabel�� ��ӹ���
	private Boolean IngGame = false; // ������ ���������� üũ
	private JLabel PlayTime = new JLabel("�÷��̽ð�:"); //�÷��� �ð�
	private JLabel PlayTime2 = new JLabel("0 ��"); // �÷��� �ð� ��� JPanel
	private Timer TimerTh; // Ÿ�̸� ������
	private int clickBox = 0; // Ŭ���� ���� ���� üũ�� ���� ���� �Ǵ��ϱ� ���� ����
	private String WorL = "�й�"; // �� �� ���� Ȯ���ϴ� ����
	private JLabel BoomLabel = new JLabel("Mine Sweeper Game!"); // ����ã�� ���� Ÿ��Ʋ

	public GamePanel(int row, int col, int Boomsize) { // ������ ũ��, ��ź ���� �޾Ƽ� �ʱ�ȭ
		this.row = row;
		this.col = col;
		this.BoomSize = Boomsize;
		BoomBox = new int[row + 2][col + 2]; // ������ ���Ʒ��� ��ĭ�� �ο��Ͽ� Ŭ���� ĭ �ֺ� 8ĭ �˻�� �迭 �ε��� ���� ��� ���� ����

		setSize(700, 700);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setLocation(0, 0);

		PlayTime.setSize(80, 20);
		PlayTime.setLocation(400, 70);
		add(PlayTime); //�÷���Ÿ�� ��

		PlayTime2.setSize(80, 20);
		PlayTime2.setLocation(480, 70);
		add(PlayTime2); //�÷���Ÿ�� ��2

		BoomLabel.setSize(500, 50);
		BoomLabel.setLocation(35, 20);
		BoomLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		BoomLabel.setFont(new Font("Arial", Font.BOLD, 30));
		BoomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(BoomLabel); // ����ã�� Ÿ��Ʋ ��

		GameSet(); //���� ���� (��ź�� ��ġ �� �ֺ� ��ź�� ��) 
		Jbt = new Box[row + 2][col + 2]; // BoxŸ���� ������ �迭 ,�������� ũ�⺸�� ũ�� �����Ͽ� ������������ ���پ��� ���� ������ ���� ������� �ʴ´�.
		for (int i = 1; i < row + 1; i++) { 
			for (int j = 1; j < col + 1; j++) {
				Jbt[i][j] = new Box();
				Jbt[i][j].setP(i, j);
				Jbt[i][j].setLocation(10 + j * 25, 70 + i * 25);
				Jbt[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));
				if (BoomBox[i][j] == 9) { // BoomBox�� üũ�Ͽ� 9�̸� B(��ź) ������ ������ ���ڴ� �״�� �ؽ�Ʈ ���� 
					Jbt[i][j].setText("B"); //9�̸� B�� �ؽ�Ʈ ����(��ź)
				} else {
					Jbt[i][j].setText(Integer.toString(BoomBox[i][j])); // ���ڴ� ���ڿ��� ����ȯ�Ͽ� ���� �ؽ�Ʈ�� ����
				}
				Jbt[i][j].setForeground(Color.LIGHT_GRAY); 
				Jbt[i][j].addMouseListener(new MouseAdapter() { // ����ã�� ĭ Ŭ���� �߻��ϴ� �̺�Ʈ

					public void mouseClicked(MouseEvent e) { 
						Box B = (Box) e.getSource();
						if (IngGame == false) { //���� �������� �ƴ϶�� Ÿ�̸� ������ �����Ͽ� �ð����� ����
							TimerTh = new Timer(PlayTime2);

							TimerTh.start();
							IngGame = true;
						}
						char ch = B.getText().charAt(0);
						if (ch == 66) { // ��ź Ŭ���� Ŭ���� ��ź�� ���������� ��Ÿ����, ������ ��ź�� ��ġ�� ǥ�� �� ���� ����
							for (int i = 1; i < row + 1; i++) {
								for (int j = 1; j < col + 1; j++) {
									if (Jbt[i][j].getText().equals("B")) {
										Jbt[i][j].setForeground(Color.black);
									}
								}
							}
							B.setForeground(Color.RED);
							B.setBackground(Color.GRAY);
							TimerTh.interrupt(); //Ÿ�̸� ������ ����
							IngGame = false; //������ �������Ƿ� ���� ������ ���� false
							JOptionPane.showMessageDialog(null, "���� �й�!", "���� �й�!", JOptionPane.PLAIN_MESSAGE);
							//���� �й� �޽��� ���
							WorL = "�й�"; //���� ���� �й�� ����

						} else if (ch == '0') { //������ ���� ���ڰ� 0�Ͻ� ,�� �ֺ��� ��ź�� �Ѱ��� ���� �� 
							FindZero(B.getP()); // 0�ֺ��� ���� �˻��Ͽ� 0�̸� ���� Ŭ���ǰ� �ϴ� �Լ�
						} else { // 0�̳� ��ź�� �ƴ� ���
							B.setForeground(Color.black);
							B.setBackground(Color.GRAY);
							B.setClick(); // �̹� Ŭ���� ���̶�� �������ִ� �Լ�

						}
						for (int i = 1; i < row + 1; i++) { // ���� ó������ ������ �˻��Ͽ� Ŭ���Ǿ��ִ� �� �� üũ
							for (int j = 1; j < col + 1; j++) {
								if (Jbt[i][j].getClick() == true) 
									clickBox += 1;
								
							}
						}
					
						if (clickBox + BoomSize == row * col) { //Ŭ���� �� �� + ��ź�� ��  = �������� ���̸�
							GameWin(); // ���� �¸�
						
						} else {
							clickBox = 0; // �ƴ϶�� Ŭ����  �ڽ� �� �ʱ�ȭ 
						}

					}

				});
				add(Jbt[i][j]);
			}
		}

	}

	public String getWorL() { // ���� �� �� ��ȯ
		return WorL;
	}

	public void GameWin() { //���� �¸� 
		JOptionPane.showMessageDialog(null, "���� �¸�!", "���� �¸�!", JOptionPane.PLAIN_MESSAGE);
		//���� �¸� �޽��� ���
		WorL = "�¸�";
		//���� ���� ���� �¸��� ����
	}

	public int GetTimer() { //Ÿ�̸��� ������ �ð� ��ȯ
		return TimerTh.getTimer();
	}

	public void FindZero(Point p) { // 0�� �� �ֺ� 8ĭ�� 0�� ã�� �Լ� 
		int x = p.x; // Ŭ���� ���� Jbt �������迭�� �� ������  
		int y = p.y; // Ŭ���� ���� Jbt �������迭�� �� ������
		for (int i = x - 1; i < x + 2; i++) {  // Ŭ���� ���� �ֺ� 8ĭ�� �˻�
			for (int j = y - 1; j < y + 2; j++) {
				if (BoomBox[i][j] == 0 && Jbt[i][j] != null && Jbt[i][j].getClick() != true) {
					//�ֺ� ��ź �� ����  0�̰� , ���� null��(�� ������ �迭�� �������� ù��)�� �ƴϰ� , Ŭ������ �ʾ�����
					Jbt[i][j].setBackground(Color.GRAY); // Ŭ���� ���� GRAY��
					Jbt[i][j].setForeground(Color.GRAY); // Ŭ���� ���� GRAY��
					Jbt[i][j].setClick(); 

					Point t = new Point(i, j);
					FindZero(t); // ���ȣ���Ͽ� 0�� �� Ŭ���� �ֺ� 0�� �󺧵� ���� Ŭ���ǵ��� �ϴ� ����Լ�
				} else if (BoomBox[i][j] != 9 && Jbt[i][j] != null && Jbt[i][j].getClick() != true) {
					//�ֺ���ź ���� 0�� �ƴϰ� ��ź�� �ƴϸ� ���� null��(������ �迭�� �������� ù��)�� �ƴϰ�, Ŭ���Ǿ� ���� ������
					//�� 1,2,3,4,5,6,7,8�̸�
					Jbt[i][j].setBackground(Color.GRAY); //���õȶ� ���� ����� GRAY  
					Jbt[i][j].setForeground(Color.BLACK); // ���� ���� BLACK���� �Ͽ� ��Ÿ��
					Jbt[i][j].setClick();
					//0�� �ƴϹǷ� ���ȣ���� ���� ����

				}

			}
		}

	}

	public void GameSet() {
		for (int i = 0; i < BoomSize; i++) { // ��ź�� ������ŭ ���� ���� ��ġ
			int x = 1 + (int) (Math.random() * row); // ������ ��ź�� x��ǥ
			int y = 1 + (int) (Math.random() * col); // ������ ��ź�� y��ǥ
			if (BoomBox[x][y] != 9) { // ��ź(9) �� �ƴ϶�� ��ź ��ġ �ϰ� BoomSide()�Լ� �̿��� ��ź üũ
				BoomBox[x][y] = 9;
				BoomSide(x, y); // �迭���� x,y����ġ���� 8�������� ��ź�� ���� üũ�Ͽ� ��ź �ϳ��� +1 �Ͽ� �ֺ� ��ź �� �� ���� 
			}

		}

	}
	public void BoomSide(int x, int y) { // ��ź �ֺ� 8ĭ�� ���ڸ� +1 �Ͽ� �ֺ� ������ ���� Ȯ��
		int sx = x - 1;
		int sy = y - 1;
		for (int i = sx; i < x + 2; i++) {
			for (int j = sy; j < y + 2; j++) {
				if (BoomBox[i][j] != 9) // 9=��ź �̸� +1
					BoomBox[i][j] += 1;
			}
		}
	}

	public void GameReSet() { //���� ����
		TimerTh.setFFlag(); // Ÿ�̸� ������ �ʱ�ȭ
		IngGame = false; //���� ������ false�� ����
		WorL = "�й�"; // �߰��� �����Ͽ� �й�� ����
		for (int i = 1; i < row+1; i++) {
			for (int j = 1; j < col+1; j++) {
				BoomBox[i][j] = 0; // ��ź ���� �ֺ� ��ź�� �缳���ϱ����� �⺻������ �缳��
				Jbt[i][j].setNClick(); // ��� ���� Ŭ������ �������� �缳��
			}
		}
		GameSet(); // ���� ���� (��ź ��ġ �ֺ� ��ź �� �� )
		for (int i = 1; i < row + 1; i++) {
			for (int j = 1; j < col + 1; j++) {
				if (BoomBox[i][j] == 9) {
					Jbt[i][j].setText("B");
				} else {
					Jbt[i][j].setText(Integer.toString(BoomBox[i][j]));
				}
				Jbt[i][j].setForeground(Color.LIGHT_GRAY); // ������ ����� ��� LIGHT_GRAY�� ����
				Jbt[i][j].setBackground(Color.LIGHT_GRAY); // 
			}
		}

	}



	public void setBoomSize(int boomsize) { // ��ź�� ���� ����
		BoomSize = boomsize;
	}

	class Box extends JLabel { // JLabel�� ��ӹٵ� ���� �� ���� �ִ� Box ��
		private int x; // ���� ���ڰ�
		private int y; // ���� ���ڰ�
		private Boolean isClicked = false; //���� Ŭ�� ����

		public Box() {
			setSize(25, 25);
			setText("1");
			setFont(new Font("Arial", Font.BOLD, 20));
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.LIGHT_GRAY);
			setOpaque(true);
		}

		public void setP(int x, int y) { //���� ���ڰ� ����
			this.x = x;
			this.y = y;
		}

		public Point getP() { // ���� ���ڰ� ��ȯ
			Point p = new Point(x, y);
			return p;
		}

		public void setClick() { //���� Ŭ���Ǿ��ٰ���
			isClicked = true;
		}

		public void setNClick() { //���� Ŭ������ �ʾҴٰ� ����
			isClicked = false;
		}

		public Boolean getClick() { //���� Ŭ�� ���θ� ��ȯ
			return isClicked;
		}

	}

	class Timer extends Thread { //�ð����� Ÿ�̸� ������
		JLabel J;
		private int timer = 0; //Ÿ�̸� �� 
		private Boolean Flag = true; //������ Ȥ�� ���� ���� �� ������ ���۵ɶ� �����带 �����Ű�� ���� �÷���

		public Timer(JLabel J) {
			this.J = J;

		}

		public void run() {
			while (true) {
				J.setText(Integer.toString(timer) + "��"); //���� ����� �ð����� �缳��
				try {
					if (Flag == false) { //�÷��װ� false�̸� ������ ����
						return;
					}
					timer += 1;
					Thread.sleep(1000); //������ 1�� �� ���� 1�� ����
				} catch (InterruptedException e) { 
					return;
				}

			}
		}

		public int getTimer() { //��� �ð� ��ȯ
			return timer;
		}

		public void setFFlag() { // �÷��׸� false�� ����
			Flag = false;
		}

	}

}

class UiPanel extends JPanel { //������ ǥ���� ui�г�
	JLabel Score = new JLabel("SCORE"); 
	String[] ScoreStr = new String[20]; // 20���� ���ھ� ����� Score.txt ���Ͽ��� �ҷ��� ������ ���ڿ� �迭 
	JLabel[] ScoreLabel = new JLabel[20]; //�о�� ���ھ� ����� ǥ���� J�� �迭

	public UiPanel() {
		for (int i = 0; i < 20; i++) {
			ScoreStr[i] = ""; // null���� �������� �ʱ�ȭ
		}
		setSize(100, 700);
		setBorder(new BevelBorder(BevelBorder.RAISED));// SCORE �� �׵θ� ����
		setLocation(700, 0);
		setLayout(null);
		Score.setSize(200, 100);// SCORE �� ����
		Score.setFont(new Font("Arial", Font.BOLD, 50));// SCORE �� ����
		Score.setLocation(20, 10);// SCORE �� ����
		add(Score);
		ScoreRead();
		for (int i = 0; i < 20; i++) { // 
			ScoreLabel[i] = new JLabel(ScoreStr[i]);
			ScoreLabel[i].setLocation(10, 90 + i * 30); //��ġ �Ÿ� ����� ����
			ScoreLabel[i].setSize(200, 50);
			ScoreLabel[i].setFont(new Font("HY�߰��", Font.PLAIN, 11)); 
			add(ScoreLabel[i]);
		}

	}

	public void ScoreRefresh() { // ���ھ� ���ΰ�ħ
		ScoreRead();
		for (int i = 0; i < 20; i++) {
			ScoreLabel[i].setText(ScoreStr[i]); // ���ھ� ������ ���ھ��� ���ΰ�ħ

		}
	}

	public void ScoreRead() { // Score.txt���Ͽ��� ��ϵǾ��ִ� ���ھ �ҷ����� �Լ�
		try {
			File file = new File("C:\\Users\\WIN10\\eclipse-workspace\\Booom\\score\\Score.txt"); // ���� ��ü
			FileReader fileReader = new FileReader(file); // ���� �Է� ��Ʈ��
			BufferedReader bufReader = new BufferedReader(fileReader); // ���� �Է� ����
			String ScoreLine = "";
			for (int i = 0; i < 20; i++) {
				if ((ScoreLine = bufReader.readLine()) != null) { // ���� ���� ���ڿ��� null���� �ƴ϶�� ScoreLine�迭�� ����
					ScoreStr[i] = ScoreLine;
				}
			}
			bufReader.close(); //�Է¹��� ����

		} catch (FileNotFoundException e) { //������ ������� ����ó��

		} catch (IOException e) { // ����� ����ó��

		}
	}

	public void ScoreWrite(String str) { //���ھ���str�� �޾Ƽ� Score.txt�� ���� �Լ�
		try {
			File file = new File("C:\\Users\\WIN10\\eclipse-workspace\\Booom\\score\\Score.txt"); // ���ھ� ���� ��ü
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file, true)); // ���� ���� ���� true�� ���� ���뿡 �̾ �Է�
			if (file.isFile()) { //�����̸�
				bufWriter.write(str); // ���ڷ� ���� ���ڿ� ���Ͽ� ����
				bufWriter.newLine(); // ���๮�� �Է�
				bufWriter.close(); //������� ����
			}

		} catch (IOException e) { //����� ���� ó��

		}

	}

	public void ClearTxt(String str) { // ���ھ 20���� ������� ����Ǿ��ִ� ������ ������ ���� ���� �Լ�
		try {
			File file = new File("C:\\Users\\WIN10\\eclipse-workspace\\Booom\\score\\Score.txt"); // ���ھ� ���� ��ü
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file)); // ���� ���� ���� false�� ���� ���� ���� �Ŀ� �̾ �Է�
			if (file.isFile()) { //�����̸�
				bufWriter.write(str); // ���ڷ� ���� ���ڿ� ���Ͽ� ����
				bufWriter.newLine(); // ���๮�� �Է�
				bufWriter.close();
			}

		} catch (IOException e) { //����� ���� ó�� 

		}
	}
}