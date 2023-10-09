package edu.java.app.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.app.view.MainLogin;
import edu.java.user.model.Score;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.Random;
import java.awt.event.ActionEvent;

public class ClickedGame extends JFrame {

	private JPanel contentPane;
	private JFrame frame1;

	private JLabel lblScoreRec;
	private JButton btnStartButton;
	private JPanel RedPoint;
	private JLabel lblCommentary;
	private JLabel lbliFScoreIsHight;
	private JLabel lblScore;
	private JLabel lblCommentary_1_2_1;
	private JButton btnNewButton_1;
	private JLabel lblCount;
	private JButton btnGameing;
	private long satrttime = 0;
	private long endtime = 0;
	private long dueringtime = 0;
	private long hightScore;
	private MainLogin app;

	/**
	 * Launch the application.
	 */
	public static void showGamePageGest(JFrame frame1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClickedGame frame = new ClickedGame(frame1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public ClickedGame(JFrame frame1) {
		this.frame1 = frame1;
		anti(frame1);
	}

	public void anti(JFrame frame1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 424);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnStartButton = new JButton("시작");
		btnStartButton.setFont(new Font("D2Coding", Font.BOLD, 12));
		btnStartButton.addActionListener(new ActionListener() {

//			public void windowClosed(WindowEvent e) {
//				
//				app.
//			}

			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						RedPoint.setVisible(false);
						lblScoreRec.setText("00000");
						// 해설구문 잠시 가리기

						lbliFScoreIsHight.setVisible(false);
						lblCount.setVisible(true);
						lblCommentary.setVisible(false);

						// 시작 버튼 기능상실
						btnStartButton.setEnabled(false);
						btnStartButton.setVisible(false);
						// 게임버튼 보이기
						btnGameing.setVisible(true);

						// 화면 중앙 321,start 표시
						lblCount.setText("3");
						waitOneSecound();
						lblCount.setText("2");
						waitOneSecound();
						lblCount.setText("1");
						waitOneSecound();
						lblCount.setText("START");
						waitOneSecound();

						// 설명 구문 표시
						lblCommentary.setText("위에 빨간점이 뜨면 클릭하세요");
						lblCommentary.setVisible(true);
						lblCount.setVisible(false);

						// 게임버튼 활성화
						btnGameing.setEnabled(true);

						Random ra = new Random();
						int randomTime = 1000 + ra.nextInt(10000);

						try {
							Thread.sleep(randomTime);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

						RedPoint.setVisible(true);
						satrttime = System.currentTimeMillis();

					}
				}.start();

			}
		});
		btnStartButton.setBounds(12, 228, 448, 54);
		contentPane.add(btnStartButton);

		lblCommentary = new JLabel("시작버튼을 누르세요");

		lblCommentary.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblCommentary.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommentary.setBounds(12, 200, 448, 18);
		contentPane.add(lblCommentary);

		JLabel lblCommentary_1 = new JLabel("기록 :");
		lblCommentary_1.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblCommentary_1.setBounds(12, 321, 85, 54);
		contentPane.add(lblCommentary_1);

		JLabel lblCommentary_1_1 = new JLabel("ms");
		lblCommentary_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCommentary_1_1.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblCommentary_1_1.setBounds(308, 321, 27, 54);
		contentPane.add(lblCommentary_1_1);

		lblScoreRec = new JLabel("0000000");
		lblScoreRec.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScoreRec.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblScoreRec.setBounds(109, 321, 187, 54);
		contentPane.add(lblScoreRec);

		lbliFScoreIsHight = new JLabel("NEW");
		lbliFScoreIsHight.setForeground(new Color(255, 0, 0));
		lbliFScoreIsHight.setHorizontalAlignment(SwingConstants.CENTER);
		lbliFScoreIsHight.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lbliFScoreIsHight.setBounds(347, 321, 113, 54);
		lbliFScoreIsHight.setVisible(false);
		contentPane.add(lbliFScoreIsHight);

		JLabel lblCommentary_1_2 = new JLabel("User :");
		lblCommentary_1_2.setFont(new Font("D2Coding", Font.PLAIN, 16));
		lblCommentary_1_2.setBounds(12, 10, 66, 18);
		contentPane.add(lblCommentary_1_2);

		lblCommentary_1_2_1 = new JLabel("guest");
		lblCommentary_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCommentary_1_2_1.setFont(new Font("D2Coding", Font.PLAIN, 16));
		lblCommentary_1_2_1.setBounds(71, 10, 66, 18);
		contentPane.add(lblCommentary_1_2_1);

		JLabel lblCommentary_1_2_2 = new JLabel("Top Score :");
		lblCommentary_1_2_2.setFont(new Font("D2Coding", Font.PLAIN, 16));
		lblCommentary_1_2_2.setBounds(224, 10, 135, 18);
		contentPane.add(lblCommentary_1_2_2);

		lblScore = new JLabel("000000");
		lblScore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScore.setFont(new Font("D2Coding", Font.PLAIN, 16));
		lblScore.setBounds(347, 10, 113, 18);
		contentPane.add(lblScore);

		RedPoint = new JPanel();
		RedPoint.setBackground(new Color(255, 0, 0));
		RedPoint.setBounds(213, 81, 54, 54);
		RedPoint.setVisible(false);
		contentPane.add(RedPoint);

		btnNewButton_1 = new JButton("종료");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnNewButton_1.setBounds(190, 292, 85, 23);
		contentPane.add(btnNewButton_1);

		lblCount = new JLabel("반응속도 게임");
		lblCount.setFont(new Font("D2Coding", Font.PLAIN, 54));
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setBounds(22, 38, 438, 152);
		lblCount.setVisible(true);
		contentPane.add(lblCount);

		btnGameing = new JButton("클릭");
		btnGameing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						if (satrttime > 0) {
							endtime = System.currentTimeMillis();
							long dueringtime = endtime - satrttime;
							lblScoreRec.setText(dueringtime + "");

							// 게임 버튼 비활성화
							btnGameing.setVisible(false);
							btnGameing.setEnabled(false);

							// 다시하기 버튼 활성화, 해설 on
							btnStartButton.setText("다시 하기");
							btnStartButton.setVisible(true);
							btnStartButton.setEnabled(true);

							// 신기록 달성시
							// TODO 메서드 인
							checkBestScore(dueringtime);

						} else {
							// 포인트 바로 보이기
							RedPoint.setEnabled(true);

							// 게임 버튼 비활성화
							btnGameing.setVisible(false);
							btnGameing.setEnabled(false);

							// 다시하기 버튼 활성화, 해설 on
							lblCommentary.setText("포인트가 나오기전에 클릭했습니다.");
							btnStartButton.setText("다시 하기");
							btnStartButton.setVisible(true);
							btnStartButton.setEnabled(true);
						}
					}
				}.start();

			}
		});
		btnGameing.setFont(new Font("D2Coding", Font.BOLD, 12));
		btnGameing.setBounds(12, 228, 448, 54);
		btnGameing.setEnabled(false);
		btnGameing.setVisible(false);
		contentPane.add(btnGameing);
	}

	public void waitOneSecound() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
	}

	public void checkBestScore(long x) {

		if (x < 10000) {

			if (hightScore == 0) {
				hightScore = x;
				lblCommentary.setText("신기록 달성!");
				lbliFScoreIsHight.setVisible(true);
				lblScore.setText(hightScore + "");
				return;
			}

			if (hightScore > x) {
				hightScore = x;
				lbliFScoreIsHight.setVisible(true);
				lblScore.setText(hightScore + "");
				lblCommentary.setText("신기록 달성!");
				return;

			} else {
				lblCommentary.setText("게임 종료");
				return;
			}

		} else {
			btnStartButton.setText("기록 달성 불가, 다시 하기");
			return;
		}
	}

}
