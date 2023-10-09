package edu.java.app.game;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.java.user.dao.UserDaoImpl;
import edu.java.user.model.Score;
import edu.java.user.model.User;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.Random;

public class GameApp {

	// 프레임
	private JFrame frame;

	private JLabel imgLabel;

	// 사용된 라벨들
	private JLabel lblNewLabel_1;
	private JLabel lblUserNickname;
	private JLabel lblThisScore;
	private JLabel lblTopScore;
	private JLabel lblPlayerstopscore;
	private JLabel lblTitle;
	private JLabel lblPressSpace;

	// 그래픽으로 사용될 판넬들
	private JLabel panelObstacle;
	private JLabel panelPlayer;
	private JPanel graound;

	// 점프시 사용될 필드
	// 초기 판넬 y 좌표
	private int playerY = 315;
	// y 최고 높이
	private int maxY = 150;
	// y 최저 높이
	private int minY = 315;

	// 장애물의 x좌표
	private int obstacleY = 315;
	private int obstacleX = 310;

	// 게임 진행중 여부를 체크할 필드
	// 게임진행중
	private boolean playing;
	private boolean gameOver;
	// 점프진행중
	boolean jumpping;

	// 점수를 체크하는 필드
	private int jumpcout;

	// 인게임의 정보를 저장할 필드
	// 게임의 점수를 체크할 필드
	private int score = 0;

	// player 이미지1
	private static ImageIcon icongPlaier = new ImageIcon("image/player1.png");
	private static Image imagePlaier1 = icongPlaier.getImage();
	Image imagePlaier = imagePlaier1.getScaledInstance(50, 60, Image.SCALE_SMOOTH);
	ImageIcon IconPlaier1 = new ImageIcon(imagePlaier);

	// player 이미지2
	private static ImageIcon icongPlaier2 = new ImageIcon("image/player2.png");
	private static Image imagePlaier2 = icongPlaier2.getImage();
	Image imageplaier = imagePlaier2.getScaledInstance(50, 60, Image.SCALE_SMOOTH);
	ImageIcon IconPlaier2 = new ImageIcon(imageplaier);

	// 장애물 이미지
	private static ImageIcon icongObject = new ImageIcon("image/object.jpg");
	private static Image imageObject = icongObject.getImage();
	Image imageobect = imageObject.getScaledInstance(50, 60, Image.SCALE_SMOOTH);
	ImageIcon IconObject = new ImageIcon(imageobect);

	
	
	// TODO 유저 정보읽기(맨 마지막 해야할 일)
	private int cid;
	private UserDaoImpl dao = UserDaoImpl.getInstance();

	/**
	 * Launch the application.
	 */
	public static void DINIgame(int cid) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameApp window = new GameApp(cid);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameApp(int cid) {
		this.cid = cid;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setLocationRelativeTo(null);

		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setTitle("게임");

		frame.addKeyListener(new KeyListener() {
			// 키를 누를때
			public void keyPressed(KeyEvent event) {
				startOrJump(event);
			}

			// 키를 땔때
			public void keyReleased(KeyEvent event) {
			}

			// 키의 타입 구분
			public void keyTyped(KeyEvent event) {
			}
		});

		// TODO 판넬이 이미지 삽입

		User u = dao.readCid(this.cid);
		String Nick = u.getNickName();
		int topscore = u.getScore().getTopScpre();

		panelPlayer = new JLabel(IconPlaier2);
		panelPlayer.setText("");
		panelPlayer.setBackground(new Color(0, 255, 255));
		panelPlayer.setBounds(95, 315, 50, 60);
		frame.getContentPane().add(panelPlayer);

		panelObstacle = new JLabel(IconObject);
		panelObstacle.setBackground(new Color(255, 0, 0));
		panelObstacle.setBounds(310, 315, 50, 60);
		frame.getContentPane().add(panelObstacle);
		
		
		

		graound = new JPanel();
		graound.setBackground(Color.GRAY);
		graound.setBounds(-21, 343, 752, 142);
		frame.getContentPane().add(graound);

		lblTitle = new JLabel("Jump Dinosaur");
		lblTitle.setFont(new Font("Impact", Font.PLAIN, 55));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 45, 660, 113);
		frame.getContentPane().add(lblTitle);

		lblPressSpace = new JLabel("- press space to play -");
		lblPressSpace.setHorizontalAlignment(SwingConstants.CENTER);
		lblPressSpace.setFont(new Font("Impact", Font.PLAIN, 30));
		lblPressSpace.setBounds(12, 135, 660, 38);
		frame.getContentPane().add(lblPressSpace);

		lblNewLabel_1 = new JLabel("User : ");
		lblNewLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 10, 97, 25);
		frame.getContentPane().add(lblNewLabel_1);

		lblUserNickname = new JLabel("someone");
		lblUserNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserNickname.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblUserNickname.setBounds(84, 10, 128, 25);
		frame.getContentPane().add(lblUserNickname);
		lblUserNickname.setText(Nick);

		lblTopScore = new JLabel("Top Score : ");
		lblTopScore.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblTopScore.setBounds(441, 10, 128, 25);
		frame.getContentPane().add(lblTopScore);

		lblPlayerstopscore = new JLabel("Players TopScore");
		lblPlayerstopscore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerstopscore.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblPlayerstopscore.setBounds(558, 10, 114, 25);
		frame.getContentPane().add(lblPlayerstopscore);

		lblPlayerstopscore.setText(topscore + "");

		lblThisScore = new JLabel("This Score");
		lblThisScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisScore.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblThisScore.setBounds(275, 10, 128, 25);
		lblThisScore.setVisible(false);
		frame.getContentPane().add(lblThisScore);
	}

	protected void startOrJump(KeyEvent event) {


		// 어떤 버튼이 눌렸는지 체크 하는곳

		// 첫 시작 페이지
		if (event.getKeyCode() == KeyEvent.VK_SPACE && playing == false) {

			// 현재 점수 표시 << 점프 카운트값
			lblThisScore.setVisible(true);

			// 타이틀, 시작가이드 숨기기
			lblTitle.setVisible(false);
			lblPressSpace.setVisible(false);
			jumpcout = 0;

			playing = true;

			new Thread() {
				@Override
				public void run() {

					// 장애물 무한 이동코드
					while (playing) {

						panelObstacle.setBounds(obstacleX, 315, 50, 60);

						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						obstacleX = obstacleX - 1;
						if (obstacleX <= -51) {
							obstacleX = 750;
						}

						// 게임오버 조건
//						panelObstacle 장애물 x 범위
						// 초기 x 310
						// 최대 45~145
//						panelPlayer 플레이어 y 범위
						// 초기 y 315
						// 범위 y 254~ 316
						if ((panelObstacle.getX() > 45 && panelObstacle.getX() < 145)
								&& (panelPlayer.getY() > 254 && panelPlayer.getY() < 316)) {

							// 게임오버
							playing = false;
							obstacleX = 310;
							// 게임오버시 UI 표시
							lblTitle.setText("Game Over");
							lblPressSpace.setText("- press space to replay -");

							lblTitle.setVisible(true);
							lblPressSpace.setVisible(true);

							if (lblThisScore.getText() != "This Score") {
								score = Integer.parseInt(lblThisScore.getText());
							}

							lblThisScore.setText(score + "");

							getNewUser();

							return;
						}
					}
				}
			}.start();

		}

		// 게임중일때 스페이스바를 누르면 (+ 판넬의 위치가 땅일때 체크)
		if (event.getKeyCode() == KeyEvent.VK_SPACE && panelPlayer.getY() == 315) {
			jumpcout++;
			lblThisScore.setText(jumpcout + "");
			new Thread() {
				@Override
				public void run() {
					// 점프중 체크
					// 초단위로 판넬의 위치 인식 올라감
					jumpping = true;
					while (jumpping) {
						// 공룡 세팅
						panelPlayer.setBounds(95, playerY, 50, 60);
						// 점프모션1
						panelPlayer.setIcon(IconPlaier2);
						// 잠시 대기
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 착지모션2
						panelPlayer.setIcon(IconPlaier1);
						// 높이 올리기(점프중)
						playerY--;
						if (playerY == maxY) {
							break;
						}
					}
					// 초단위로 판넬의 위치 인식 내려감
					while (jumpping) {
						// 잠시 대기
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 높이 내리기(하강중)
						playerY++;
						panelPlayer.setBounds(95, playerY, 50, 59);

						if (playerY == minY) {

							jumpping = false;
						}
					}
				}
			}.start();
		}
	}

	// TODO 점프중일때 s 버튼누르면 빠르게 하강
	public boolean cancleJump(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_S && jumpping) {
			while (jumpping) {

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 높이 내리기(하강중)
				playerY = playerY + 4;
				panelPlayer.setBounds(95, playerY, 50, 59);

				if (playerY >= 315) {
					playerY = 315;
					jumpping = false;
				}
				return true;
			}
		}
		return false;
	}

	public User getNewUser() {
		// 기존 유저값
		User u = dao.readCid(this.cid);

		// 기존 점수와 비교해서 현점수가 더크면 변경
		if (u.getScore().getTopScpre() < score) {
			LocalDateTime now = LocalDateTime.now();
			Score s = new Score(score, now);
			u.setScore(s);
			// 테이블값도 변경
			dao.update(u);
			// 현재 인게임값도 변경
			lblPlayerstopscore.setText(score + "");
		}

		return u;
	}

	// cid 받아와서 유저정보 읽고 내보내기

}
