package edu.java.app.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.java.user.dao.UserDaoImpl;
import edu.java.user.model.Score;
import edu.java.user.model.User;
import static edu.java.user.model.User.Entuty.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FinderIdOrPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPhone;
	private JTable IdTable;
	private Object[][] date = {};
	private List<User> userList;
	private static final String[] ID_COLUM = { "아이디", "아이디생성일" };
	private DefaultTableModel model;
	private UserDaoImpl dao = UserDaoImpl.getInstance();
	private Component parent;
	private JButton btnEditPass;
	private JButton btnSerch;

	/**
	 * Launch the application.
	 */
	public static void showFinderIdOrPasswordPage(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinderIdOrPassword frame = new FinderIdOrPassword(parent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FinderIdOrPassword(Component parent) {
		this.parent = parent;
		anti();
	}

	/**
	 * Create the frame.
	 */
	public void anti() {

		int x = 100;
		int y = 100;

		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("아이디/비밀번호 찾기");
		setBounds(x, y, 315, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("이름 :");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblName.setBounds(12, 23, 102, 29);
		contentPane.add(lblName);

		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.RIGHT);
		textName.setFont(new Font("D2Coding", Font.PLAIN, 14));
		textName.setBounds(127, 23, 160, 29);
		contentPane.add(textName);
		textName.setColumns(10);

		JLabel lblName_1 = new JLabel("전화번호 :");
		lblName_1.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblName_1.setBounds(12, 62, 102, 29);
		contentPane.add(lblName_1);

		textPhone = new JTextField();
		textPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 14));
		textPhone.setColumns(10);
		textPhone.setBounds(127, 62, 160, 29);
		contentPane.add(textPhone);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 137, 275, 204);
		contentPane.add(scrollPane);

		IdTable = new JTable();
		model = new DefaultTableModel(date, ID_COLUM);
		IdTable.setModel(model);

		IdTable.setFont(new Font("D2Coding", Font.BOLD, 16));
		scrollPane.setViewportView(IdTable);

		btnSerch = new JButton("검색");

		btnSerch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 0 검색결과 없거나 이상한거 검색시 필터(즉시리턴)
				if (checkTheTextBox() == 1) {
					JOptionPane.showMessageDialog(contentPane, "이름과, 전화번호를 입력후 눌러주세요", "검색실패",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				// 1 기존테이블청소,
				model = new DefaultTableModel(date, ID_COLUM);
				IdTable.setModel(model);

				// 2 검색결과 테이블 보여주기,
				userList = dao.readUser(textName.getText(), textPhone.getText());
				for (User u : userList) {
					Object[] row = { u.getId(), u.getTime() };
					model.addRow(row);
				}
				btnEditPass.setEnabled(true);

				
				
			}
		});

// TODO 리스트가 없을때 안눌리는 비밀번호 재설정 버튼
//		if (model.getRowCount() > 0) {
//			btnEditPass.setEnabled(true);}

		btnSerch.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnSerch.setBounds(12, 98, 102, 29);
		contentPane.add(btnSerch);

		btnEditPass = new JButton("비밀번호 재설정");
		btnEditPass.setEnabled(false);


		btnEditPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 0 선택 행 없을때 안읽히기

				if (model.getRowCount() <= 0) {
					JOptionPane.showMessageDialog(contentPane, "아이디 선택 후 시도하시오", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (checkTheTextBox() == 1) {

					return;
				}
				// 1. 선택행 인덱스 가져오기
				int row = IdTable.getSelectedRow();
				// 2. 선택행 유저의 cid 가져오기
				int changeCid = dao.read().get(row).getCid();

//				dao.readCid(changeCid);

				// 3. 해당 유저의 비밀번호를 재설정하기
				String pass = null;
				pass = JOptionPane.showInputDialog(contentPane, "변경할 비밀번호를 입력하세요");
				// 3-0 바꿀 비번 체크
				if (checkThePass(pass) == 1) {
					return;
				}
				// 3-1 바꿀 비밀번호 받기,

				User BU = dao.readCid(changeCid);
				int cid = BU.getCid();
				String id = BU.getId();

				String name = BU.getName();
				String phone = BU.getPhone();
				String email = BU.getEmail();
				String nick = BU.getNickName();
				LocalDateTime U_T = BU.getTime();

				LocalDateTime S_T = BU.getScore().getTime();
				int S_S = BU.getScore().getTopScpre();

				Score s = new Score(S_S, S_T);
				User u = new User(cid, id, pass, name, phone, email, nick, U_T, s);

				int result = dao.update(u);
				System.out.println("result = " + result);
				dispose();
				JOptionPane.showMessageDialog(contentPane, "재설정 성공", "성공", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnEditPass.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnEditPass.setBounds(127, 98, 160, 29);
		contentPane.add(btnEditPass);

		JButton btnSerch_1 = new JButton("종료");
		btnSerch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		btnSerch_1.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnSerch_1.setBounds(12, 351, 275, 30);
		contentPane.add(btnSerch_1);

	}


	protected int checkTheTextBox() {
		int i = 0;
		if (textName.getText().equals("") || textPhone.getText().equals("")) {
			i = 1;
		}
		return i;
	}

	protected int checkThePass(String pass) {
		if (pass.length() < 4 || pass.equals(null)) {
			JOptionPane.showMessageDialog(contentPane, "4글자 이상으로 적어주세요", "경고", JOptionPane.WARNING_MESSAGE);
			return 1;
		} else if (pass.contains(" ")) {
			JOptionPane.showMessageDialog(contentPane, "비밀번호에는 공백이 있을수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return 1;
		} else {
			return 0;
		}
	}

}
