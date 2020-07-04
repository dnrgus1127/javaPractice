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

public class Boom extends JFrame { // 메인 프레임
	private MyPanel Mp = new MyPanel(); // Game패널과 Ui패널을 담을 JPanel 생성
	private SettingDialog Sd = new SettingDialog(this, "설정"); // 설정 누를시 나올 세팅 다이얼로그 객체 생성

	public Boom() {
		setTitle("지뢰찾기 게임!");
		setContentPane(Mp);
		CreateMenu(); // 메뉴 생성 함수
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setVisible(true);
		setLocation(500, 200);
		setResizable(false); // 프레임 크기 고정
	}

	private void CreateMenu() { // 메뉴 생성 함수
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("Game");
		JMenuItem ReSet = new JMenuItem("NewGame");
		ReSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // NewGame메뉴 선택시 게임판 초기화

				String str = "User: " + Mp.getUser() + " 승패: " + Mp.getGP().getWorL() + " Time: "
						+ Mp.getGP().GetTimer() + "초"; // Score.txt 파일에 입력될 게임 승패 로그
				Mp.getGP().GameReSet(); // 게임 폭탄 위치 및 게임 숫자 설정
				Mp.getUi().ScoreWrite(str); // Score.txt파일에 승패 로그 입력
				Mp.getUi().ScoreRead(); // Score.txt 파일을 다시 읽어옴
				Mp.getUi().ScoreRefresh(); //읽어져 있는 Score.txt파일 재출력
			}

		});
		JMenu Setting = new JMenu("Setting");
		JMenuItem sett = new JMenuItem("설정");
		sett.addActionListener(new ActionListener() { // 설정 버튼 눌리면 세팅 다이얼로그 실행

			@Override
			public void actionPerformed(ActionEvent e) { 
				Sd.setVisible(true); //세팅 다이얼로그 보이게설정

			}

		});
		Setting.add(sett);

		screenMenu.add(ReSet);
		mb.add(screenMenu);
		mb.add(Setting);
		setJMenuBar(mb); //mb를 메뉴로 설정
	}

	class SettingDialog extends JDialog { // 폭탄 개수 설정 다이얼로그
		private JFrame j;
		private String title;
		private int BoomNum = 5; // 폭탄개수 
		
		public SettingDialog(JFrame J, String title) { 
			super(J, title, true);
			this.j = J;
			this.title = title;
			setSize(400, 150);
			setLocation(700, 400);
			setLayout(null);
			JLabel NoBLabel = new JLabel("폭탄의 개수");
			NoBLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); //3D테두리 설정
			NoBLabel.setHorizontalAlignment(JLabel.CENTER); // 가운데 정렬
			NoBLabel.setBackground(Color.MAGENTA); //배경색
			NoBLabel.setSize(80, 20);
			NoBLabel.setLocation(50, 20);
			add(NoBLabel);
			ButtonGroup NoB = new ButtonGroup(); // 폭탄 개수 설정

			JRadioButton N5 = new JRadioButton("15"); //폭탄 개수 15개
			N5.setLocation(150, 20);
			N5.setSize(40, 20);
			JRadioButton N20 = new JRadioButton("20", true);//폭탄 개수 20개, 기본으로 선택
			N20.setLocation(200, 20);
			N20.setSize(40, 20);
			JRadioButton N30 = new JRadioButton("30");//폭탄 개수 30개
			N30.setLocation(250, 20);
			N30.setSize(40, 20);
			JRadioButton N50 = new JRadioButton("50");//폭탄 개수 50개
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
			N5.addItemListener(new ItemListener() { // 라디오버튼이 클릭되면 라디오버튼의 텍스트를 읽어 폭탄의 개수로 저장 하는 이벤트 리스너
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});
			N20.addItemListener(new ItemListener() {// 라디오버튼이 클릭되면 라디오버튼의 텍스트를 읽어 폭탄의 개수로 저장 하는 이벤트 리스너
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});
			N30.addItemListener(new ItemListener() {// 라디오버튼이 클릭되면 라디오버튼의 텍스트를 읽어 폭탄의 개수로 저장 하는 이벤트 리스너
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});
			N50.addItemListener(new ItemListener() {// 라디오버튼이 클릭되면 라디오버튼의 텍스트를 읽어 폭탄의 개수로 저장 하는 이벤트 리스너
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton j = (JRadioButton) e.getSource();
					BoomNum = Integer.parseInt(j.getText());
				}
			});

			JButton setBtn = new JButton("설정");
			setBtn.setSize(80, 25);
			setBtn.setLocation(160, 60);
			setBtn.addActionListener(new ActionListener() { //설정 다이얼로그 속 설정 버튼 눌리면 폭탄의 개수 설정하고 게임판 리셋시키는 이벤트 리스너

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton jbtn = (JButton) e.getSource();

					Mp.getGP().setBoomSize(BoomNum);
					String str = "User: " + Mp.getUser() + " 승패: " + Mp.getGP().getWorL() + " Time: "
							+ Mp.getGP().GetTimer() + "초"; //승패 로그
					Mp.getGP().GameReSet(); // 폭탄과 주변 숫자 재설정
					Mp.getUi().ScoreWrite(str); // 승패 로그 기록
					Mp.getUi().ScoreRead(); // Score.txt 불러오기
					Mp.getUi().ScoreRefresh(); // 스코어판 재출력
					setVisible(false); // 다이얼로그 종료
				}

			});
			add(setBtn);

		}

	}

	public static void main(String[] args) {
		new Boom();
	}
}

class MyPanel extends JPanel { // 게임패널과 스코어(UI) 패널이 올라갈 패널 
	private int BoomSize = 50; // 초기 폭탄 개수
	private GamePanel Gp = new GamePanel(20, 20, BoomSize); // 20*20의 게임판 및 BoomSize의 폭탄 수를 가지는 게임패널 객체 생성
	private UiPanel Up = new UiPanel(); // Ui패널 객체 생성
	private String UserName = "익명"; // 유저 이름, 플레이어 이름 입력 안할시 디폴트값 익명

	public MyPanel() {
		setSize(800, 670);
		setLayout(null);

		Gp.setBounds(0, 0, getWidth() * 7 / 10, getHeight()); // 프레임의 7/10만큼 게임패널 

		Up.setBounds(getWidth() * 7 / 10, 0, getWidth() * 3 / 10, getHeight()); // 프레임의 나머지 3/10만큼 Ui패널
		add(Gp);
		add(Up);

		UserName = JOptionPane.showInputDialog("플레이어 이름을 입력하세요!"); //플레이어 이름 입력받는 팝업 다이얼로그

	}

	public GamePanel getGP() { // 게임패널 객체 반환
		return Gp;
	}

	public UiPanel getUi() { // Ui패널 객체 반환
		return Up;
	}

	public String getUser() { //유저 이름 반환
		return UserName;
	}

}

class GamePanel extends JPanel { // 지뢰찾기 게임이 실행되는 메인 패널
	private Box Btn = new Box();
	private int[][] BoomBox;// 폭탄과 주변 폭탄 수 값을 체크하는 2차원 배열
	private int BoomSize; // 폭탄의 개수
	private int row; // 가로 열 수
	private int col; // 세로 행 수
	private Box[][] Jbt; // Box타입 2차원 배열 Box는 JLabel을 상속받음
	private Boolean IngGame = false; // 게임이 진행중인지 체크
	private JLabel PlayTime = new JLabel("플레이시간:"); //플레이 시간
	private JLabel PlayTime2 = new JLabel("0 초"); // 플레이 시간 출력 JPanel
	private Timer TimerTh; // 타이머 스레드
	private int clickBox = 0; // 클릭된 라벨의 수를 체크해 게임 종료 판단하기 위한 변수
	private String WorL = "패배"; // 승 패 여부 확인하는 변수
	private JLabel BoomLabel = new JLabel("Mine Sweeper Game!"); // 지뢰찾기 게임 타이틀

	public GamePanel(int row, int col, int Boomsize) { // 게임판 크기, 폭탄 개수 받아서 초기화
		this.row = row;
		this.col = col;
		this.BoomSize = Boomsize;
		BoomBox = new int[row + 2][col + 2]; // 게임판 위아래로 한칸씩 부여하여 클릭한 칸 주변 8칸 검사시 배열 인덱스 범위 벗어난 오류 방지

		setSize(700, 700);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setLocation(0, 0);

		PlayTime.setSize(80, 20);
		PlayTime.setLocation(400, 70);
		add(PlayTime); //플레이타임 라벨

		PlayTime2.setSize(80, 20);
		PlayTime2.setLocation(480, 70);
		add(PlayTime2); //플레이타임 라벨2

		BoomLabel.setSize(500, 50);
		BoomLabel.setLocation(35, 20);
		BoomLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		BoomLabel.setFont(new Font("Arial", Font.BOLD, 30));
		BoomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(BoomLabel); // 지뢰찾기 타이틀 라벨

		GameSet(); //게임 세팅 (폭탄의 위치 및 주변 폭탄의 수) 
		Jbt = new Box[row + 2][col + 2]; // Box타입을 가지는 배열 ,게임판의 크기보다 크게 선언하여 동서남북으로 한줄씩은 오류 방지를 위해 사용하지 않는다.
		for (int i = 1; i < row + 1; i++) { 
			for (int j = 1; j < col + 1; j++) {
				Jbt[i][j] = new Box();
				Jbt[i][j].setP(i, j);
				Jbt[i][j].setLocation(10 + j * 25, 70 + i * 25);
				Jbt[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));
				if (BoomBox[i][j] == 9) { // BoomBox를 체크하여 9이면 B(폭탄) 라벨으로 나머지 숫자는 그대로 텍스트 설정 
					Jbt[i][j].setText("B"); //9이면 B로 텍스트 변경(폭탄)
				} else {
					Jbt[i][j].setText(Integer.toString(BoomBox[i][j])); // 숫자는 문자열로 형변환하여 라벨의 텍스트로 설정
				}
				Jbt[i][j].setForeground(Color.LIGHT_GRAY); 
				Jbt[i][j].addMouseListener(new MouseAdapter() { // 지뢰찾기 칸 클릭시 발생하는 이벤트

					public void mouseClicked(MouseEvent e) { 
						Box B = (Box) e.getSource();
						if (IngGame == false) { //게임 실행중이 아니라면 타이머 스레드 실행하여 시간측정 시작
							TimerTh = new Timer(PlayTime2);

							TimerTh.start();
							IngGame = true;
						}
						char ch = B.getText().charAt(0);
						if (ch == 66) { // 폭탄 클릭시 클릭한 폭탄은 붉은색으로 나타내고, 나머지 폭탄들 위치를 표시 후 게임 종료
							for (int i = 1; i < row + 1; i++) {
								for (int j = 1; j < col + 1; j++) {
									if (Jbt[i][j].getText().equals("B")) {
										Jbt[i][j].setForeground(Color.black);
									}
								}
							}
							B.setForeground(Color.RED);
							B.setBackground(Color.GRAY);
							TimerTh.interrupt(); //타이머 스레드 종료
							IngGame = false; //게임이 끝났으므로 게임 실행중 여부 false
							JOptionPane.showMessageDialog(null, "게임 패배!", "게임 패배!", JOptionPane.PLAIN_MESSAGE);
							//게임 패배 메시지 출력
							WorL = "패배"; //승패 여부 패배로 저장

						} else if (ch == '0') { //선택한 라벨의 숫자가 0일시 ,즉 주변에 폭탄이 한개도 없을 시 
							FindZero(B.getP()); // 0주변의 라벨을 검색하여 0이면 전부 클릭되게 하는 함수
						} else { // 0이나 폭탄이 아닐 경우
							B.setForeground(Color.black);
							B.setBackground(Color.GRAY);
							B.setClick(); // 이미 클릭된 라벨이라고 설정해주는 함수

						}
						for (int i = 1; i < row + 1; i++) { // 라벨의 처음부터 끝까지 검사하여 클릭되어있는 라벨 수 체크
							for (int j = 1; j < col + 1; j++) {
								if (Jbt[i][j].getClick() == true) 
									clickBox += 1;
								
							}
						}
					
						if (clickBox + BoomSize == row * col) { //클릭된 라벨 수 + 폭탄의 수  = 게임판의 수이면
							GameWin(); // 게임 승리
						
						} else {
							clickBox = 0; // 아니라면 클릭된  박스 수 초기화 
						}

					}

				});
				add(Jbt[i][j]);
			}
		}

	}

	public String getWorL() { // 게임 승 패 반환
		return WorL;
	}

	public void GameWin() { //게임 승리 
		JOptionPane.showMessageDialog(null, "게임 승리!", "게임 승리!", JOptionPane.PLAIN_MESSAGE);
		//게임 승리 메시지 출력
		WorL = "승리";
		//게임 승패 여부 승리로 설정
	}

	public int GetTimer() { //타이머의 측정한 시간 반환
		return TimerTh.getTimer();
	}

	public void FindZero(Point p) { // 0인 라벨 주변 8칸의 0을 찾는 함수 
		int x = p.x; // 클릭된 라벨이 Jbt 이차원배열의 몇 행인지  
		int y = p.y; // 클릭된 라벨이 Jbt 이차원배열의 몇 열인지
		for (int i = x - 1; i < x + 2; i++) {  // 클릭된 라벨의 주변 8칸을 검사
			for (int j = y - 1; j < y + 2; j++) {
				if (BoomBox[i][j] == 0 && Jbt[i][j] != null && Jbt[i][j].getClick() != true) {
					//주변 폭탄 수 값이  0이고 , 라벨이 null값(즉 이차원 배열의 동서남북 첫줄)이 아니고 , 클릭되지 않았으면
					Jbt[i][j].setBackground(Color.GRAY); // 클릭된 라벨은 GRAY색
					Jbt[i][j].setForeground(Color.GRAY); // 클릭된 라벨은 GRAY색
					Jbt[i][j].setClick(); 

					Point t = new Point(i, j);
					FindZero(t); // 재귀호출하여 0인 라벨 클릭시 주변 0인 라벨들 전부 클릭되도록 하는 재귀함수
				} else if (BoomBox[i][j] != 9 && Jbt[i][j] != null && Jbt[i][j].getClick() != true) {
					//주변폭탄 수가 0이 아니고 폭탄도 아니며 라벨이 null값(이차원 배열의 동서남북 첫줄)도 아니고, 클릭되어 잇지 않으면
					//즉 1,2,3,4,5,6,7,8이면
					Jbt[i][j].setBackground(Color.GRAY); //선택된라벨 으로 배경은 GRAY  
					Jbt[i][j].setForeground(Color.BLACK); // 글자 색은 BLACK으로 하여 나타냄
					Jbt[i][j].setClick();
					//0이 아니므로 재귀호출은 하지 않음

				}

			}
		}

	}

	public void GameSet() {
		for (int i = 0; i < BoomSize; i++) { // 폭탄의 개수만큼 땅에 지뢰 설치
			int x = 1 + (int) (Math.random() * row); // 랜덤한 폭탄의 x좌표
			int y = 1 + (int) (Math.random() * col); // 랜덤한 폭탄의 y좌표
			if (BoomBox[x][y] != 9) { // 폭탄(9) 가 아니라면 폭탄 설치 하고 BoomSide()함수 이용해 폭탄 체크
				BoomBox[x][y] = 9;
				BoomSide(x, y); // 배열에서 x,y의위치에서 8방향으로 폭탄의 수를 체크하여 폭탄 하나당 +1 하여 주변 폭탄 수 값 설정 
			}

		}

	}
	public void BoomSide(int x, int y) { // 폭탄 주변 8칸의 숫자를 +1 하여 주변 지뢰의 개수 확인
		int sx = x - 1;
		int sy = y - 1;
		for (int i = sx; i < x + 2; i++) {
			for (int j = sy; j < y + 2; j++) {
				if (BoomBox[i][j] != 9) // 9=폭탄 이면 +1
					BoomBox[i][j] += 1;
			}
		}
	}

	public void GameReSet() { //게임 리셋
		TimerTh.setFFlag(); // 타이머 설정값 초기화
		IngGame = false; //게임 실행중 false로 설정
		WorL = "패배"; // 중간에 리셋하여 패배로 변경
		for (int i = 1; i < row+1; i++) {
			for (int j = 1; j < col+1; j++) {
				BoomBox[i][j] = 0; // 폭탄 수와 주변 폭탄수 재설정하기위해 기본값으로 재설정
				Jbt[i][j].setNClick(); // 모든 라벨이 클릭되지 않음으로 재설정
			}
		}
		GameSet(); // 게임 세팅 (폭탄 위치 주변 폭탄 수 값 )
		for (int i = 1; i < row + 1; i++) {
			for (int j = 1; j < col + 1; j++) {
				if (BoomBox[i][j] == 9) {
					Jbt[i][j].setText("B");
				} else {
					Jbt[i][j].setText(Integer.toString(BoomBox[i][j]));
				}
				Jbt[i][j].setForeground(Color.LIGHT_GRAY); // 배경색과 전경생 모두 LIGHT_GRAY로 설정
				Jbt[i][j].setBackground(Color.LIGHT_GRAY); // 
			}
		}

	}



	public void setBoomSize(int boomsize) { // 폭탄의 개수 세팅
		BoomSize = boomsize;
	}

	class Box extends JLabel { // JLabel을 상속바든 지뢰 가 담길수 있는 Box 라벨
		private int x; // 라벨의 인자값
		private int y; // 라벨의 인자값
		private Boolean isClicked = false; //라벨의 클릭 유무

		public Box() {
			setSize(25, 25);
			setText("1");
			setFont(new Font("Arial", Font.BOLD, 20));
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.LIGHT_GRAY);
			setOpaque(true);
		}

		public void setP(int x, int y) { //라벨의 인자값 설정
			this.x = x;
			this.y = y;
		}

		public Point getP() { // 라벨의 인자값 반환
			Point p = new Point(x, y);
			return p;
		}

		public void setClick() { //라벨을 클릭되었다고설정
			isClicked = true;
		}

		public void setNClick() { //라벨이 클릭되지 않았다고 설정
			isClicked = false;
		}

		public Boolean getClick() { //라벨의 클릭 여부를 반환
			return isClicked;
		}

	}

	class Timer extends Thread { //시간측정 타이머 스레드
		JLabel J;
		private int timer = 0; //타이머 초 
		private Boolean Flag = true; //새게임 혹은 설정 으로 새 게임이 시작될때 스레드를 종료시키기 위한 플래그

		public Timer(JLabel J) {
			this.J = J;

		}

		public void run() {
			while (true) {
				J.setText(Integer.toString(timer) + "초"); //라벨을 경과된 시간으로 재설정
				try {
					if (Flag == false) { //플래그가 false이면 스레드 종료
						return;
					}
					timer += 1;
					Thread.sleep(1000); //스레드 1초 씩 잠들어 1초 측정
				} catch (InterruptedException e) { 
					return;
				}

			}
		}

		public int getTimer() { //경과 시간 반환
			return timer;
		}

		public void setFFlag() { // 플래그를 false로 설정
			Flag = false;
		}

	}

}

class UiPanel extends JPanel { //점수를 표시할 ui패널
	JLabel Score = new JLabel("SCORE"); 
	String[] ScoreStr = new String[20]; // 20줄의 스코어 기록을 Score.txt 파일에서 불러서 저장할 문자열 배열 
	JLabel[] ScoreLabel = new JLabel[20]; //읽어온 스코어 기록을 표현할 J라벨 배열

	public UiPanel() {
		for (int i = 0; i < 20; i++) {
			ScoreStr[i] = ""; // null참조 방지위해 초기화
		}
		setSize(100, 700);
		setBorder(new BevelBorder(BevelBorder.RAISED));// SCORE 라벨 테두리 설정
		setLocation(700, 0);
		setLayout(null);
		Score.setSize(200, 100);// SCORE 라벨 설정
		Score.setFont(new Font("Arial", Font.BOLD, 50));// SCORE 라벨 설정
		Score.setLocation(20, 10);// SCORE 라벨 설정
		add(Score);
		ScoreRead();
		for (int i = 0; i < 20; i++) { // 
			ScoreLabel[i] = new JLabel(ScoreStr[i]);
			ScoreLabel[i].setLocation(10, 90 + i * 30); //위치 거리 띄워서 설정
			ScoreLabel[i].setSize(200, 50);
			ScoreLabel[i].setFont(new Font("HY견고딕", Font.PLAIN, 11)); 
			add(ScoreLabel[i]);
		}

	}

	public void ScoreRefresh() { // 스코어 새로고침
		ScoreRead();
		for (int i = 0; i < 20; i++) {
			ScoreLabel[i].setText(ScoreStr[i]); // 스코어 변동시 스코어판 새로고침

		}
	}

	public void ScoreRead() { // Score.txt파일에서 기록되어있는 스코어를 불러오는 함수
		try {
			File file = new File("C:\\Users\\WIN10\\eclipse-workspace\\Booom\\score\\Score.txt"); // 파일 객체
			FileReader fileReader = new FileReader(file); // 파일 입력 스트림
			BufferedReader bufReader = new BufferedReader(fileReader); // 파일 입력 버퍼
			String ScoreLine = "";
			for (int i = 0; i < 20; i++) {
				if ((ScoreLine = bufReader.readLine()) != null) { // 한줄 읽은 문자열이 null값이 아니라면 ScoreLine배열에 저장
					ScoreStr[i] = ScoreLine;
				}
			}
			bufReader.close(); //입력버퍼 닫음

		} catch (FileNotFoundException e) { //파일이 없을경우 에러처리

		} catch (IOException e) { // 입출력 에러처리

		}
	}

	public void ScoreWrite(String str) { //스코어기록str을 받아서 Score.txt에 쓰는 함수
		try {
			File file = new File("C:\\Users\\WIN10\\eclipse-workspace\\Booom\\score\\Score.txt"); // 스코어 파일 객체
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file, true)); // 파일 쓰기 버퍼 true로 기존 내용에 이어서 입력
			if (file.isFile()) { //파일이면
				bufWriter.write(str); // 인자로 받은 문자열 파일에 쓰기
				bufWriter.newLine(); // 개행문자 입력
				bufWriter.close(); //쓰기버퍼 닫음
			}

		} catch (IOException e) { //입출력 에러 처리

		}

	}

	public void ClearTxt(String str) { // 스코어가 20줄이 넘을경우 저장되어있는 내용을 날리고 새로 쓰는 함수
		try {
			File file = new File("C:\\Users\\WIN10\\eclipse-workspace\\Booom\\score\\Score.txt"); // 스코어 파일 객체
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file)); // 파일 쓰기 버퍼 false로 기존 내용 삭제 후에 이어서 입력
			if (file.isFile()) { //파일이면
				bufWriter.write(str); // 인자로 받은 문자열 파일에 쓰기
				bufWriter.newLine(); // 개행문자 입력
				bufWriter.close();
			}

		} catch (IOException e) { //입출력 에러 처리 

		}
	}
}