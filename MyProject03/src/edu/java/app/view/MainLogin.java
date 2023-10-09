package edu.java.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import edu.java.app.game.ClickedGame;
import edu.java.app.game.GameApp;
import edu.java.app.game.GameApp2;
import edu.java.user.dao.UserDaoImpl;
import edu.java.user.model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Color;

public class MainLogin {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	private JTextField textId;
	private JTextField textPass;
	private JButton btnLogin;
	private JButton btnFindIdPassword;
	private JButton btnNewId;
	private JPanel panelS;
	private JButton btnGest;
	private JPanel panelN;
	private JLabel lblTitle;
	private JLabel lblJump;
	
	public static final String DEVELOPER_ID = "java";
	private static final String DEVELOPER_PASS = "1234";
	private static final String JUMP = "JUMP = ";
	
	private int jump;
	private List<User> userList;
	private UserDaoImpl dao = UserDaoImpl.getInstance();
	private static MainLogin window;
	private JLabel lblDINO;
	private int DinoY = 12;


	private static ImageIcon icongPlaier = new ImageIcon("image\\Login.png");
	private static Image imagePlaier1 = icongPlaier.getImage();
	Image imagePlaier = imagePlaier1.getScaledInstance(50, 60, Image.SCALE_SMOOTH);
	ImageIcon IconPlaier1 = new ImageIcon(imagePlaier);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainLogin();
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
	public MainLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jump = 0;
		frame = new JFrame();
		frame.setTitle("로그인");
		frame.setBounds(100, 100, 462, 310);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		panelN = new JPanel();
		panelN.setBackground(new Color(255, 255, 255));
		panelN.setBounds(0, 0, 446, 69);
		panelN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 1 && DinoY == 12) {
					jump++;
					lblJump.setText(JUMP + jump);
					new Thread() {
						@Override
						public void run() {

							// 점프중 체크

							// 초단위로 판넬의 위치 인식 올라감
							boolean jumpping = true;
							int maxY = -8;
							int minY = 12;
							while (jumpping) {
								lblDINO.setBounds(90, DinoY, 50, 60);

								// 잠시 대기
								try {
									Thread.sleep(2);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								// 높이 올리기(점프중)
								DinoY--;
								if (DinoY == maxY) {
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
								DinoY++;
								lblDINO.setBounds(90, DinoY, 50, 60);

								if (DinoY == minY) {

									jumpping = false;
								}
							}
						}
					}.start();
				}
					

				

			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panelN);
		panelN.setLayout(null);

		lblTitle = new JLabel("DINO JUMP");
		lblTitle.setBounds(163, 36, 271, 44);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("D2Coding", Font.BOLD, 50));
		panelN.add(lblTitle);

		lblJump = new JLabel("jump");
		lblJump.setBounds(12, 5, 143, 24);
		lblJump.setHorizontalAlignment(SwingConstants.LEFT);
		lblJump.setFont(new Font("D2Coding", Font.BOLD, 20));
		panelN.add(lblJump);

		lblDINO = new JLabel(IconPlaier1);
		lblDINO.setHorizontalAlignment(SwingConstants.CENTER);
		lblDINO.setBounds(90, 12, 50, 60);
		panelN.add(lblDINO);

		JPanel panelC = new JPanel();
		panelC.setBackground(new Color(128, 128, 128));
		panelC.setBounds(0, 69, 446, 165);
		frame.getContentPane().add(panelC);
		panelC.setLayout(null);

		JLabel lblId = new JLabel("아이디  :");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("D2Coding", Font.BOLD, 18));
		lblId.setBounds(12, 28, 116, 33);
		panelC.add(lblId);

		textId = new JTextField();
		textId.setHorizontalAlignment(SwingConstants.RIGHT);
		textId.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textId.setBounds(140, 28, 178, 33);
		panelC.add(textId);
		textId.setColumns(10);

		textPass = new JPasswordField();
		textPass.setHorizontalAlignment(SwingConstants.RIGHT);
		textPass.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textPass.setColumns(10);
		textPass.setBounds(140, 71, 178, 33);
		panelC.add(textPass);

		JLabel lblId_1 = new JLabel("비밀번호 : ");
		lblId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblId_1.setFont(new Font("D2Coding", Font.BOLD, 18));
		lblId_1.setBounds(12, 71, 116, 33);
		panelC.add(lblId_1);

		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressTheButton(e);
			}
		});
		btnLogin.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnLogin.setBounds(337, 28, 97, 76);
		panelC.add(btnLogin);

		btnGest = new JButton("게스트 접속");
		btnGest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jump == 3 && textId.getText().equals(DEVELOPER_ID) && textPass.getText().equals(DEVELOPER_PASS)) {
					DeveloperPage.showDeveloperPage(frame);
				} else if (jump == 10) {
					ClickedGame.showGamePageGest(frame);
				} else {
					pressTheButton(e);
				}

			}
		});
		btnGest.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnGest.setBounds(12, 128, 422, 27);
		panelC.add(btnGest);

		panelS = new JPanel();
		panelS.setBackground(new Color(128, 128, 128));
		panelS.setBounds(0, 234, 446, 37);
		frame.getContentPane().add(panelS);

		btnNewId = new JButton("회원가입");
		btnNewId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressTheButton(e);
			}
		});
		btnNewId.setFont(new Font("D2Coding", Font.BOLD, 15));
		panelS.add(btnNewId);

		btnFindIdPassword = new JButton("아이디/비밀번호 찾기");
		btnFindIdPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressTheButton(e);
			}
		});
		btnFindIdPassword.setFont(new Font("D2Coding", Font.BOLD, 15));
		panelS.add(btnFindIdPassword);
	}

	public void pressTheButton(ActionEvent event) {
		Object source = event.getSource();
		if (source == btnLogin) {

			// 1 아이디 존재유무 확인
			// 2 비밀번호 매칭 확인
			if (checkIdPassRight() == 0) {
				JOptionPane.showMessageDialog(frame, "아이디/비밀번호 를 확인해 주세요", "로그인실패", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 아규먼트 있는
			GameApp.DINIgame(takeUserCid());


			// 3 게임인 페이지
		} else if (source == btnGest) {
			// 1 게임인 페이지 아규먼트 없는

			GameApp2.gestgame();

			// GamePage.showGamepage();
		} else if (source == btnNewId) {
			MakeNewIdPage.showTheMakeNewIdPage(frame);
		} else if (source == btnFindIdPassword) {
			FinderIdOrPassword.showFinderIdOrPasswordPage(frame);
		}
	}

	public int checkIdPassRight() {
		String id = textId.getText();
		String pass = textPass.getText();
		userList = dao.read();
		User nullUser = null;

		for (User u : userList) {
			if (u.getId().equals(id)) {
				if (u.getPass().equals(pass)) {
					return 1;
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "아이디/비밀번호를 확인해 주세요", "실패", JOptionPane.WARNING_MESSAGE);
		return 0;
	}

	public int takeUserCid() {

		String id = textId.getText();
		String pass = textPass.getText();
		userList = dao.read();
		User nullUser = null;

		for (User u : userList) {
			if (u.getId().equals(id)) {
				if (u.getPass().equals(pass)) {
					return u.getCid();
				}
			}
		}

		return nullUser.getCid();
	}
}
