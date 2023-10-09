package edu.java.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.user.dao.UserDaoImpl;
import edu.java.user.model.Score;
import edu.java.user.model.User;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MakeNewIdPage extends JFrame {

	private JPanel contentPane;
	private JButton btnCancle;
	private JButton btnCreate;
	private JTextField textId;
	private JTextField textPass;
	private JTextField textPassA;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField textNickName;
	private JLabel lblNickName;
	private JLabel lblEmail;
	private Component parent;
	private String checkedId = null;
	private final UserDaoImpl dao = UserDaoImpl.getInstance();

	/**
	 * Launch the application.
	 */
	public static void showTheMakeNewIdPage(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeNewIdPage frame = new MakeNewIdPage(parent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MakeNewIdPage(Component parent) {
		this.parent = parent;
		anti();
	}

	/**
	 * Create the frame.
	 */
	public void anti() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("회원가입");

		int x = 100;
		int y = 100;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}

		setBounds(x, y, 410, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelN = new JPanel();
		contentPane.add(panelN, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("- 회원가입 -");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 18));
		panelN.add(lblTitle);

		JPanel panelS = new JPanel();
		contentPane.add(panelS, BorderLayout.SOUTH);

		btnCreate = new JButton("아이디 생성");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ok = checkTheIdOk();
				if (ok == 0) {
					if (dao.create(makeUser()) == 1) {
						JOptionPane.showMessageDialog(contentPane, "아이디생성이 완료되었습니다.", "생성 완료", 1);
						dispose();
					} else {
						JOptionPane.showMessageDialog(contentPane, "아이디생성 실패.", "실패", 2);
					}
				}
			}
		});
		btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 12));
		panelS.add(btnCreate);

		btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnCancle.setFont(new Font("D2Coding", Font.PLAIN, 12));
		panelS.add(btnCancle);

		JPanel panelC = new JPanel();
		panelC.setBackground(new Color(192, 192, 192));
		contentPane.add(panelC, BorderLayout.CENTER);
		panelC.setLayout(null);

		JLabel lblId = new JLabel("아이디 입력 : ");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblId.setBounds(12, 29, 115, 24);
		panelC.add(lblId);

		textId = new JTextField();
		textId.setHorizontalAlignment(SwingConstants.RIGHT);
		textId.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textId.setBounds(139, 29, 132, 26);
		panelC.add(textId);
		textId.setColumns(10);

		JButton btnChackId = new JButton("중복확인");
		btnChackId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				checkIsExistiId();

			}
		});
		btnChackId.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnChackId.setBounds(283, 30, 89, 23);
		panelC.add(btnChackId);

		JLabel lblPass = new JLabel("비밀번호 입력 : ");
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPass.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblPass.setBounds(12, 63, 115, 24);
		panelC.add(lblPass);

		textPass = new JTextField();
		textPass.setHorizontalAlignment(SwingConstants.RIGHT);
		textPass.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textPass.setColumns(10);
		textPass.setBounds(139, 63, 132, 26);
		panelC.add(textPass);

		textPassA = new JTextField();
		textPassA.setHorizontalAlignment(SwingConstants.RIGHT);
		textPassA.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textPassA.setColumns(10);
		textPassA.setBounds(139, 97, 132, 26);
		panelC.add(textPassA);

		JLabel lblPassA = new JLabel("비밀번호 확인 : ");
		lblPassA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassA.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblPassA.setBounds(12, 97, 115, 24);
		panelC.add(lblPassA);

		JLabel lblName = new JLabel("이름 : ");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblName.setBounds(12, 133, 115, 24);
		panelC.add(lblName);

		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.RIGHT);
		textName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textName.setColumns(10);
		textName.setBounds(139, 133, 132, 26);
		panelC.add(textName);

		JLabel lblPhone = new JLabel("연락처 : ");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblPhone.setBounds(12, 167, 115, 24);
		panelC.add(lblPhone);

		textPhone = new JTextField();
		textPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textPhone.setColumns(10);
		textPhone.setBounds(139, 167, 132, 26);
		panelC.add(textPhone);

		textEmail = new JTextField();
		textEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		textEmail.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textEmail.setColumns(10);
		textEmail.setBounds(139, 201, 132, 26);
		panelC.add(textEmail);

		lblEmail = new JLabel("이메일 : ");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblEmail.setBounds(12, 201, 115, 24);
		panelC.add(lblEmail);

		lblNickName = new JLabel("닉네임 : ");
		lblNickName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNickName.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNickName.setBounds(12, 237, 115, 24);
		panelC.add(lblNickName);

		textNickName = new JTextField();
		textNickName.setHorizontalAlignment(SwingConstants.RIGHT);
		textNickName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textNickName.setColumns(10);
		textNickName.setBounds(139, 237, 132, 26);
		panelC.add(textNickName);
	}

	// 값 읽어오기, user 하나만들고 리턴
	public User makeUser() {

		String id = textId.getText();
		String pass = textPass.getText();
		String name = textName.getText();
		String phone = textPhone.getText();
		String email = textEmail.getText();
		String NickName = textNickName.getText();
		LocalDateTime now = LocalDateTime.now();
		Score s = new Score(0, now);
		User u = new User(id, pass, name, phone, email, NickName, now, s);

		return u;
	}

	protected int checkTheIdOk() {

		// 아이디 승인 (빈값 확인)
		// 1. 중복확인
		if (((textId.getText().equals(checkedId)) == false) || textId.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "중복확인 후 시도해 주세요.", "중복 확인", 2);
			return 1;
		}
		// 2. 비밀번호 확인
		if (textPass.getText().equals(textPassA.getText()) == false || textId.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "비밀번호를 확인해 주세요.", "비밀번호 확인", 2);
			textPassA.setText("");
			return 1;
		}
		if (textPass.getText().length() <= 3 || textPass.getText().length() >= 21) {
			JOptionPane.showMessageDialog(contentPane, "비밀번호는 4 ~20자로 설정해 주세요.", "비밀번호 확인", 2);
			return 1;
		}
		if (textName.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "이름을 확인해 주세요.", "이름 확인", 2);
			return 1;
		}
		// 3. 전화번호 11자리 체크
		if (textPhone.getText().length() != 11 || textPhone.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "전화번호를 확인해 주세요.\n(-는 생략하고 입력해주세요)", "전화번호 확인", 2);
			textPhone.setText("");
			return 1;
		}
		if (textEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "이메일을 확인해 주세요.", "이메일 확인", 2);
			return 1;
		}
		if (textNickName.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "닉네임을 확인해 주세요.", "닉네임 확인", 2);
			return 1;
		}

		return 0;

	}

	// 중복확인
	public void checkIsExistiId() {

		List<User> u = dao.read();

		for (User u1 : u) {
			if (u1.getId().equals(textId.getText()) || textId.getText().equals(MainLogin.DEVELOPER_ID)) {
				JOptionPane.showMessageDialog(contentPane, "사용 불가능한 아이디 입니다.", "경고", 2);
				textId.setText("");
				return;
			}
		}

		if (textId.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "사용 불가능한 아이디 입니다.", "경고", 2);
			textId.setText("");
			
		} else {
			String[] options = { "사용", "취소" };
			int result = JOptionPane.showOptionDialog(contentPane, "사용 가능한 아이디 입니다.", "승인",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (result == 0) {
				checkedId = textId.getText();
				textId.setText(checkedId);
			} else {
				textId.setText("");
			}
		}

	}

}
