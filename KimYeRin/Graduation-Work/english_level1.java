package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DB.DBConnection;
import util.BFS;
import util.MakeSound;
import util.Miro;
import util.Util;

public class english_level1 extends JFrame {

	// 말 위치
	private int chrX = 0;
	private int chrY = 0;

	// 미로 상자크기
	int rectWitdh = 75;
	int rectHeight = 75;

	// 단어
	String word = "";

	// 이미지
	String filePath = "";

	// 미로 리스트
	private List<List<Integer>> getMiroList = new ArrayList<>();
	// 단어 좌표
	private List<Map<String, Object>> getWordList = new ArrayList<>();
	// blank 단어 저장
	private List<Map<String, Object>> blankWordList = new ArrayList<>();
	// gerbage 단어 저장 Index
	private List<Map<String, Object>> garbageWordIndexList = new ArrayList<>();
	// gerbage 단어
	private List<String> garbageWordList = new ArrayList<>();
	// 잘린단어 위치
	private List<Integer> cuttingWordIndex = new ArrayList<>();
	// 잘린 단어
	private List<String> cuttingWordList = new ArrayList<>();
	// garbege print 리스트
	private List<String> garbagePrintList = new ArrayList<>();

	// 이동경로 담고있는 list
	List<Map<String, Object>> goList = new ArrayList<>();

	// blank count
	int blankCnt = 1;

	// 정답 담고있는 정적배열 선언
	String[] answerList = new String[blankCnt];

	int correctCnt = 0;

	int goListSize = 0;

	int enterChk = 0;

	int moveCnt = 0;

	Thread thread; // 엔터키 쓰레드

	Thread thread2; // 최적경로 쓰레드

	int toggle = 0; // 0이면 이동, 1이면 최적경로

	List<String> getBFSList = new ArrayList<>();// 최적경로 담고있는 리스트

	private JPanel contentPane;

	JButton btn_bfs;
	
	JButton btn_reback;
	
	Image img_bfs; //paint 문제다시풀기, 최적경로 
	
	String musicPath;
	
	/**
	 * Create the frame.
	 */
	public english_level1() {
		setTitle("English");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.addKeyListener(new MyKeyListener());
		// setSize(300, 300);
		setVisible(true);
		contentPane.requestFocus();

		setBounds(100, 100, 788, 558);

		ImageIcon back_icon = new ImageIcon(main_page.class.getResource("../images/back.png"));
		Image originImg6 = back_icon.getImage();
		Image changedImg6 = originImg6.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon6 = new ImageIcon(changedImg6);
		JButton back = new JButton(newIcon6);
		back.setBounds(0, 0, 52, 51);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new english_level().setVisible(true);
			}
		});

		contentPane.setLayout(null);
		contentPane.add(back);

		ImageIcon Icon7 = new ImageIcon(main_page.class.getResource("../images/exit.png"));
		Image originImg7 = Icon7.getImage();
		Image changedImg7 = originImg7.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon7 = new ImageIcon(changedImg7);
		JButton exit = new JButton(newIcon7);
		exit.setBounds(51, 0, 52, 51);

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		contentPane.add(exit);
		
		/**
		 * 스피커 버튼
		 * */
		ImageIcon Icon11 = new ImageIcon(main_page.class.getResource("../images/speaker.png"));
		Image originImg11 = Icon11.getImage();
		Image changedImg11 = originImg11.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon11 = new ImageIcon(changedImg11);
		JButton sound = new JButton(newIcon11);
		sound.setBounds(102, 0, 52, 51);

		sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeSound makeSound = new MakeSound();
				makeSound.playSound(musicPath);
				contentPane.requestFocus();
			}
		});

		contentPane.add(sound);
		

		/**
		 * 최적경로 버튼(toggle =0 일때), 다시 문제 풀기 버튼, toggle =1일때)
		 */
		
		ImageIcon Icon8 = new ImageIcon(main_page.class.getResource("../images/img_bfs.png"));
		Image originImg8 = Icon8.getImage();
		Image changedImg8 = originImg8.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon8 = new ImageIcon(changedImg8);
		btn_bfs = new JButton(newIcon8);
		btn_bfs.setBounds(450, 425, 100, 50);
		btn_bfs.addMouseListener(new MyBFSListener());
		btn_bfs.setVisible(true);
		contentPane.add(btn_bfs);
		
		
		ImageIcon Icon8_1 = new ImageIcon(main_page.class.getResource("../images/img_reback.png"));
		Image originImg8_1 = Icon8_1.getImage();
		Image changedImg8_1 = originImg8_1.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon8_1 = new ImageIcon(changedImg8_1);
		btn_reback = new JButton(newIcon8_1);
		btn_reback.setBounds(450, 425, 100, 50);
		btn_reback.addMouseListener(new MyBFSListener());
		btn_reback.setVisible(false);
		contentPane.add(btn_reback);

		/**
		 * 다음문제 풀기 버튼
		 */
		ImageIcon Icon9 = new ImageIcon(main_page.class.getResource("../images/img_next.png"));
		Image originImg9 = Icon9.getImage();
		Image changedImg9 = originImg9.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon9 = new ImageIcon(changedImg9);
		JButton btn_next = new JButton(newIcon9);
		btn_next.setBounds(550, 425, 100, 50);
		btn_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new english_level1().setVisible(true);
				return;
			}
		});
		contentPane.add(btn_next);

		/**
		 * LOOP 버튼
		 */
		ImageIcon Icon10 = new ImageIcon(main_page.class.getResource("../images/img_loop.png"));
		Image originImg10 = Icon10.getImage();
		Image changedImg10 = originImg10.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon10 = new ImageIcon(changedImg10);
		JButton btn_loop = new JButton(newIcon10);
		btn_loop.setBounds(650, 425, 100, 50);
		btn_loop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		contentPane.add(btn_loop);
		
		/*
		ImageIcon Icon11 = new ImageIcon(main_page.class.getResource("../images/sepaker.png"));
		Image originImg11 = Icon11.getImage();
		Image changedImg11 = originImg11.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon11 = new ImageIcon(changedImg11);
		JButton btn_speaker = new JButton(newIcon11);
		btn_speaker.setBounds(650, 425, 100, 50);
		btn_speaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		contentPane.add(btn_speaker);
		*/
		
		// 화면 리사이징 false
		super.setResizable(false);

		// 미로판 생성
		Miro miro = new Miro(1);

		getMiroList = miro.getMiro(1);
		getWordList = miro.getWordList(1, 4);

		for (int i = 0; i < blankCnt; i++) {
			blankWordList.add(getWordList.get(i));
			getWordList.remove(i);

		}

		// wordList에 남는곳에 garbageList넣기
		for (int i = 0; i < getWordList.size(); i++) {
			garbageWordIndexList.add(getWordList.get(i));
		}

		// 단어 받아오기(SQL)
		DBConnection dbConnection = new DBConnection();
		String sql = "select seq from TB_EDU_DATA WHERE LEVEL = 1 AND TYPE = 2";

		// seq
		String getString = "seq";
		List<Map<String, Object>> seqList = dbConnection.DBConnection(sql, getString);

		Random random = new Random();
		String seq = seqList.get(random.nextInt(seqList.size()) + 0).get("seq").toString();

		// word, filePath
		String sql2 = "select * from TB_EDU_DATA WHERE SEQ = " + seq;
		String getString2 = "word";
		List<Map<String, Object>> wordList = dbConnection.DBConnection(sql2, getString2);

		word = wordList.get(0).get("word").toString();

		Util util = new Util();
		Map<String, Object> map = util.getEnglishLevel1Setting(word);
		garbageWordList = (List<String>) map.get("garbageWordList");
		String wordType = (String) map.get("wordType");

		if (wordType.equals("L")) {
			cuttingWordList.add(0, word.toUpperCase());
		} else {
			cuttingWordList.add(0, word.toLowerCase());
		}

		List<Integer> dupChk = new ArrayList<>();

		int garbageCnt = garbageWordIndexList.size();
		while (true) {
			Random ran = new Random();
			int ranInx = ran.nextInt(garbageWordList.size());
			int garbageIndexDupChk = 0;
			for (int i = 0; i < dupChk.size(); i++) {
				if (dupChk.get(i) == ranInx) {
					garbageIndexDupChk = 1;
				}
			}
			if (garbageIndexDupChk == 0) {
				garbagePrintList.add(garbageWordList.get(ranInx));
				dupChk.add(ranInx);
				garbageCnt--;
			}
			if (garbageCnt == 0) {
				break;
			}
		}

		String getString3 = "filePath";
		List<Map<String, Object>> filePathList = dbConnection.DBConnection(sql2, getString3);

		filePath = filePathList.get(0).get("filePath").toString();

		System.out.println("seq" + seq);
		System.out.println("filePath" + filePath);
		
		
		String getString4 = "musicPath";
		List<Map<String, Object>> musicPathList = dbConnection.DBConnection(sql2, getString4);
		
		File file = new File("");
		musicPath = file.getAbsolutePath() + "/src" + musicPathList.get(0).get("musicPath").toString();
		
		img_bfs = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/img_bfs.png"));

	}

	/*
	 * * 최적 경로 클릭시 이벤트
	 * 
	 */
	class MyBFSListener implements MouseListener, Runnable {

		@Override
		public void mouseClicked(MouseEvent e) {
			/**
			 * 최적 경로 시작
			 */
			if (toggle == 0) {
				img_bfs = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/img_reback.png"));
				
				repaint();
				BFS bfs = new BFS();
				
				getBFSList = bfs.getBFS(getMiroList, answerList);

				thread2 = new Thread(this);
				thread2.start();
				btn_bfs.setVisible(false);
				btn_reback.setVisible(true);
				
			} else if (toggle == 1) {
				img_bfs = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/img_bfs.png"));
				
				// goList초기화
				goList = new ArrayList<>();

				// 말 좌표 0,0으로 세팅
				chrX = 0;
				chrY = 0;

				// 움직인 카운트 초기화
				moveCnt = 0;
				// toggle값 초기화
				toggle = 0;
				contentPane.requestFocus();
				btn_bfs.setVisible(true);
				btn_reback.setVisible(false);
				
				repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void run() {
			for (int i = 0; i < getBFSList.size(); i++) {
				try {
					thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				chrX = Integer.parseInt(getBFSList.get(i).toString().split(",")[1]);
				chrY = Integer.parseInt(getBFSList.get(i).toString().split(",")[0]);
				repaint();
			}
			toggle = 1;
		}
	}

	/*
	 * 키 이벤트
	 * 
	 */
	class MyKeyListener extends KeyAdapter implements Runnable {
		public void keyPressed(KeyEvent e) {
			// System.out.println("눌렸으");
			Map<String, Object> map = new HashMap<>();
			int keyCode = e.getKeyCode();

			String blankXY = answerList[correctCnt];
			String blankArr[] = blankXY.split(",");

			switch (keyCode) {
			case 10:

				thread = new Thread(this); // Runnable구현한 객체는 = 나
				thread.start();

				for (int i = 0; i < goList.size(); i++) {
					// 폭탄 밟았는지 확인
					for (int a = 0; a < getMiroList.size(); a++) {
						for (int b = 0; b < getMiroList.size(); b++) {
							if (getMiroList.get(b).get(a) == 0) {
								if (Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]) == a
										&& Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]) == b) {
									chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
									chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
									repaint();
									JOptionPane.showMessageDialog(null, "폭탄을 밟았습니다.(game over)", "메시지", JOptionPane.ERROR_MESSAGE);
									// 재시작
									
									goList = new ArrayList<>();

									chrX = 0;
									chrY = 0;

									moveCnt = 0;
									repaint();
									return;
								}
							}
						}
					}

					// 오답 유무 확인
					for (int a = 0; a < garbageWordIndexList.size(); a++) {
						String wordMap = garbageWordIndexList.get(a).get("wordMap").toString();
						String wordMapArr[] = wordMap.split(",");

						if ((wordMapArr[1] + "," + wordMapArr[0]).equals(goList.get(goList.size() - 1).get("go"))) {
							//오답 메시지
							URL p = getClass().getResource("../images/img_noAnswer.jpg" );
							JOptionPane.showMessageDialog(null 
														,"<html>"
														+ 	"<img src='" + p + "' height=100 width=300><br>"
														+ "</html>",
														"메시지" ,JOptionPane.PLAIN_MESSAGE);
							goList = new ArrayList<>();

							chrX = 0;
							chrY = 0;

							moveCnt = 0;
							repaint();

							return;
						}
					}

					if ((blankArr[1] + "," + blankArr[0]).equals(goList.get(goList.size() - 1).get("go"))) {
						goListSize = goList.size();
						if (enterChk == goListSize) {
							return;
						}
						correctCnt++;
						chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						if (correctCnt == blankCnt){
							URL p = getClass().getResource("../images/img_answer.jpg" );
							JOptionPane.showMessageDialog(null 
														,"<html>"
														+ 	"<img src='" + p + "' height=100 width=300><br>"
														+ "</html>",
														"메시지" ,JOptionPane.PLAIN_MESSAGE);
							dispose();
							new english_level1().setVisible(true);
							return;
						}
						//몇개 맞춘지 확인...필요
						URL p = getClass().getResource("../images/img_answer.jpg" );
						JOptionPane.showMessageDialog(null 
													,"<html>"
													+ 	"<img src='" + p + "' height=100 width=300><br>"
													+ "</html>",
													"메시지" ,JOptionPane.PLAIN_MESSAGE);
						enterChk = goList.size();
						repaint();
						return;
					} else if (Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]) <= -1) {
						chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "X좌표 경로 이탈.", "메시지" ,JOptionPane.ERROR_MESSAGE);
						// 재시작
						goList = new ArrayList<>();

						chrX = 0;
						chrY = 0;

						moveCnt = 0;
						repaint();
						return;
					} else if (Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]) <= -1) {
						chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "Y좌표 경로 이탈.", "메시지" ,JOptionPane.ERROR_MESSAGE);
						// 재시작
						goList = new ArrayList<>();

						chrX = 0;
						chrY = 0;

						moveCnt = 0;
						repaint();
						return;
					} else if (Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]) > 3) {
						chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "Y좌표 경로 이탈.", "메시지" ,JOptionPane.ERROR_MESSAGE);
						// 재시작
						goList = new ArrayList<>();

						chrX = 0;
						chrY = 0;

						moveCnt = 0;
						repaint();
						return;
					} else if (Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]) > 3) {
						chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "X좌표 경로 이탈.", "메시지" ,JOptionPane.ERROR_MESSAGE);
						// 재시작
						goList = new ArrayList<>();

						chrX = 0;
						chrY = 0;

						moveCnt = 0;
						repaint();
						return;
					} else {// 엔터키 눌렀을때 말 위치 변경
						System.out.println(goList.toString());
						// chrX =
						// Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						// chrY =
						// Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						// repaint();
					}
				}

				break;
			case KeyEvent.VK_UP:

				chrY = chrY - 1;

				map.put("go", chrX + "," + chrY);
				goList.add(map);

				break;
			case KeyEvent.VK_DOWN:

				chrY = chrY + 1;

				map.put("go", chrX + "," + chrY);
				goList.add(map);

				break;
			case KeyEvent.VK_LEFT:

				chrX = chrX - 1;

				map.put("go", chrX + "," + chrY);
				goList.add(map);

				break;
			case KeyEvent.VK_RIGHT:
				chrX = chrX + 1;

				map.put("go", chrX + "," + chrY);
				goList.add(map);

				break;
			}
		}

		@Override
		public void run() {
			for (int i = moveCnt; i < goList.size(); i++) {
				try {
					thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
				chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
				repaint();

				moveCnt = goList.size();
			}
		}
	}

	public void paint(Graphics g) {
		// System.out.println(toggle);
		// backGround
		Image backImage = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/english_background.png"));
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

		// drawBackClose
		Image drawBackClose = Toolkit.getDefaultToolkit()
				.getImage(main_page.class.getResource("../images/drawBackClose.png"));
		g.drawImage(drawBackClose, 2, 27, 102, 50, this);
		
		
		//스피커 버튼
		Image btn_speaker = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/speaker.png"));
		g.drawImage(btn_speaker, 104, 27, 50, 50, this);
		

		/*
		 * * 최적경로 버튼
		 */
		
		g.drawImage(img_bfs, 450, 450, 100, 50, this);

		/*
		 * * 다음문제 풀기 버튼
		 */
		Image btn_next = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/img_next.png"));
		g.drawImage(btn_next, 550, 450, 100, 50, this);

		/*
		 * * LOOP 버튼
		 */
		Image btn_loop = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/img_loop.png"));
		g.drawImage(btn_loop, 650, 450, 100, 50, this);

		// 단어 이미지
		if (filePath != null) {
			Image wordImage = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource(filePath));
			g.drawImage(wordImage, 75, 150, 300, 300, this);
		} else {
			JOptionPane.showMessageDialog(null, "이미지가 없거나 이미지 경로가 잘못됬습니다.", "메시지", JOptionPane.PLAIN_MESSAGE);
		}

		// 미로 단어 font
		Font font = new Font("font", Font.BOLD, 50);
		// 단어 font
		Font font2 = new Font("font", Font.BOLD, 35);
		int startX = 6;
		int startY = 2;

		// 미로판
		for (int x = 0; x < 4; x++) {// level 1,2->4, level 3,4->5, level 5->6
			for (int y = 0; y < 4; y++) {// level 1,2->4, level 3,4->5, level
											// 5->6
				g.setColor(Color.BLACK);
				g.drawRect((x + startX) * rectWitdh, (y + startY) * rectHeight, rectWitdh, rectHeight);
			}
		}

		// 폭탄 & 단어
		// blank단어 리스트 인덱스
		int inx = 0;
		// garbage 단어 리스트 인덱스
		int inx2 = garbagePrintList.size() - 1;
		for (int a = 0; a < getMiroList.size(); a++) {
			for (int b = 0; b < getMiroList.size(); b++) {
				if (getMiroList.get(b).get(a) == 0) {
					g.clearRect((a + startX) * rectWitdh, (b + startY) * rectHeight, rectWitdh, rectHeight);
					g.fillRect((a + startX) * rectWitdh, (b + startY) * rectHeight, rectWitdh, rectHeight);
				} else {
					for (int d = 0; d < garbageWordIndexList.size(); d++) {
						String str = garbageWordIndexList.get(d).get("wordMap").toString();
						if (str.equals(b + "," + a)) {
							if (inx2 > -1) {
								g.setFont(font);
								g.setColor(Color.white);
								g.drawString(garbagePrintList.get(inx2), (a + startX) * (rectWitdh + 2),(b + startY + 1) * (rectHeight - 3));
								inx2--;
							}
						}
					}
					if (toggle == 1) {
						for (int i = 0; i < getBFSList.size(); i++) {
							if (getBFSList.get(i).equals(b + "," + a)) {
								g.clearRect((a + startX) * rectWitdh, (b + startY) * rectHeight, rectWitdh, rectHeight);
							}
						}
					}
					for (int c = 0; c < blankWordList.size(); c++) {
						String str = blankWordList.get(c).get("wordMap").toString();
						if (str.equals(b + "," + a)) {
							if (inx < cuttingWordList.size()) {
								g.setFont(font);
								g.setColor(Color.white);
								g.drawString(cuttingWordList.get(inx), (a + startX) * (rectWitdh + 2),
										(b + startY + 1) * (rectHeight - 3));
								answerList[inx] = b + "," + a;
								inx++;
							}
						}

					}
				}
			}
		}

		// 단어
		int wordX = (word.length() - 2) * 20;
		int wordY = 130;
		g.setFont(font2);
		g.setColor(Color.white);
		g.drawString(word, 190 - wordX, wordY);

		// 말
		Image chrImage = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/chr.jpg"));
		g.drawImage(chrImage, rectWitdh * (startX + chrX), rectHeight * (startY + chrY), rectWitdh, rectHeight, this);

	}
}