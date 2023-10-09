package edu.java.app.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.java.user.dao.UserDaoImpl;
import edu.java.user.model.Score;
import edu.java.user.model.User;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static edu.java.user.model.User.Entuty.*;

import javax.swing.SwingConstants;
import java.awt.Color;

public class DeveloperPage extends JFrame {

	private static final String[] USERS_COLUM = { "아이디", "닉네임", "최고점수", "최고점수갱신일", "이름", "연락처", "이메일", "아이디생성일",
			"비밀번호" };
	private DefaultTableModel model;

	private JPanel contentPane;
	private Component parent;
	private JTable tableUsers;
	private JScrollPane scrollPane;
	private JButton btnExit;
	private JTextField textSearch;
	private int makeSecretId;
	private JPanel panel;
	private JPanel panel_1;
	private UserDaoImpl dao = UserDaoImpl.getInstance();
	Object[][] date = {};
	private JButton btnBan;
	private JButton btnEdit;
	private JButton btnSerch;
	private JButton btnreadAll;
	private List<User> userlist; // DB 에서 저장할 리스트
	private JButton btnExit_1;

	/**
	 * Launch the application.
	 */
	public static void showDeveloperPage(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeveloperPage frame = new DeveloperPage(parent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void notifyUserUpdate() {
		resetTableModel();
		JOptionPane.showMessageDialog(contentPane, "수정성공", "성공", JOptionPane.INFORMATION_MESSAGE);
	}

	public void resetTableModel() {
		model = new DefaultTableModel(null, USERS_COLUM);// TavleModel 객체 생성
		loadUserData(); // 새롭게 저장된거 표현
		tableUsers.setModel(model);
	}

	public DeveloperPage(Component parent) {
		this.parent = parent;
		ainti();
	}

	private void loadUserData() {
		userlist = dao.read(); 

		for (User u : userlist) {
			Object[] row = { u.getId(), u.getNickName(), u.getScore().getTopScpre(), u.getScore().getTime(),
					u.getName(), u.getPhone(), u.getEmail(), u.getTime() };
			model.addRow(row);
		}
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */
	public void ainti() {
		makeSecretId = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		int x = 100;
		int y = 100;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}

		setBounds(x, y, 1000, 600);
		contentPane = new JPanel();
		setTitle("secret page");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSecretId++;
				if (makeSecretId == 15) {

				}

			}

		});
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("- 관리자 페이지 -");
		lblNewLabel.setFont(new Font("D2Coding", Font.BOLD, 30));
		panel.add(lblNewLabel);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("D2Coding", Font.PLAIN, 20));
		panel_1.add(btnExit);

		btnExit_1 = new JButton("테스트 아이디 20개 생성");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 1; i <= 20; i++) {

					String id = "test" + i;
					String pass = "test" + i;
					String name = "test" + i;
					String phone = "01012341234";
					String email = "test" + i;
					String nickName = "test" + i;
					LocalDateTime Mt = LocalDateTime.now();
					int point = 0;
					LocalDateTime St = LocalDateTime.now();

					Score s = new Score(point, St);
					User u = new User(id, pass, name, phone, email, nickName, Mt, s);

					dao.create(u);
				}
				// 기존테이블 청소
				model = new DefaultTableModel(date, USERS_COLUM);
				tableUsers.setModel(model);

				// 테이블에 생성
				isertAllUserInfo();
			}
		});
		btnExit_1.setFont(new Font("D2Coding", Font.PLAIN, 20));
		panel_1.add(btnExit_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 53, 950, 432);
		panel_2.add(scrollPane);

		tableUsers = new JTable();
		model = new DefaultTableModel(date, USERS_COLUM);
		isertAllUserInfo();

		tableUsers.setModel(model);

		tableUsers.getColumnModel().getColumn(3).setPreferredWidth(103);
		tableUsers.getColumnModel().getColumn(7).setPreferredWidth(90);
		tableUsers.setFont(new Font("D2Coding", Font.PLAIN, 12));

		scrollPane.setViewportView(tableUsers);

		JLabel lblNewLabel_1 = new JLabel("유저정보:");
		lblNewLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 10, 106, 33);
		panel_2.add(lblNewLabel_1);

		textSearch = new JTextField();
		textSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		textSearch.setFont(new Font("D2Coding", Font.ITALIC, 15));
		textSearch.setColumns(10);
		textSearch.setBounds(384, 15, 227, 28);
		panel_2.add(textSearch);

		btnreadAll = new JButton("모든 유저보기");
		btnreadAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 기존 테이블 청소
				model = new DefaultTableModel(date, USERS_COLUM);
				tableUsers.setModel(model);

				// 모든 유저정보 표시
				isertAllUserInfo();
			}

		});
		btnreadAll.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnreadAll.setBounds(130, 10, 242, 33);
		panel_2.add(btnreadAll);

		btnSerch = new JButton("검색");
		btnSerch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 검색어 읽음
				String search = textSearch.getText();

				// 검색어 빈값일때 처리
				if (search.equals(null) || search.equals("")) {
					model = new DefaultTableModel(date, USERS_COLUM);
					tableUsers.setModel(model);
					JOptionPane.showMessageDialog(contentPane, "검색어를 확인해 주세요", "검색어 수정", 2);
					textSearch.setText("");
					return;
				}

				// 기존 테이블 청소
				model = new DefaultTableModel(date, USERS_COLUM);
				
				tableUsers.setModel(model);

				// 검색어 읽고 리스트에 출력
				List<User> user = dao.readDeveloper(search);

				for (User u : user) {

					LocalDateTime MT = u.getTime();
					LocalDateTime ST = u.getScore().getTime();

					String makeTime = MT.format(DateTimeFormatter.ofPattern("YYYY/MM/dd E HH:mm"));
					String scoreTime = ST.format(DateTimeFormatter.ofPattern("YYYY/MM/dd E HH:mm"));

					Object[] row = { u.getId(), u.getNickName(), u.getScore().getTopScpre(), scoreTime, u.getName(),
							u.getPhone(), u.getEmail(), makeTime, u.getPass() };

					model.addRow(row);
					tableUsers.setModel(model);
				}
			}
		});
		btnSerch.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnSerch.setBounds(623, 10, 105, 33);
		panel_2.add(btnSerch);

		btnEdit = new JButton("수정");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				updateSelectedRow();
			}
		});
		btnEdit.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnEdit.setBounds(740, 10, 105, 33);
		panel_2.add(btnEdit);

		btnBan = new JButton("삭제");
		btnBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				deleteSelectedRow();
			}
		});
		btnBan.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnBan.setBounds(857, 10, 105, 33);
		panel_2.add(btnBan);
	}

	// 전체보기 메서드
	private void isertAllUserInfo() {

		try {
			List<User> user = dao.read();

			for (User u : user) {
				LocalDateTime MT = u.getTime();
				LocalDateTime ST = u.getScore().getTime();

				String makeTime = MT.format(DateTimeFormatter.ofPattern("YYYY/MM/dd E HH:mm"));
				String scoreTime = ST.format(DateTimeFormatter.ofPattern("YYYY/MM/dd E HH:mm"));
				Object[] row = { u.getId(), u.getNickName(), u.getScore().getTopScpre(), scoreTime, u.getName(),
						u.getPhone(), u.getEmail(), makeTime, u.getPass() };

				model.addRow(row);
				tableUsers.setModel(model);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "오류발생 점수를 확인 후 다시 입력해 주세요 \n 사유 : " + e.getMessage(), "애러", 0);
		}

	}

	// 지우기 메서드
	private void deleteSelectedRow() {
		int row = tableUsers.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "삭제할할 행을 선택하세요", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(contentPane, "정말 삭제합니까?", "삭제", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			int banCid = dao.read().get(row).getCid();
			dao.delete(banCid);
			// 테이블 세팅
			model.removeRow(row);
			JOptionPane.showMessageDialog(contentPane, "아이디 영구벤 처리 되었습니다.", "삭제완료", 1);
		}
	}

	// 업데이트 기능
	private void updateSelectedRow() {
		int row = tableUsers.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "업데이트할 행을 선택하세요", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int changeCid = dao.read().get(row).getCid();
		UserInfoUpdateFram.showTheUpdatepage(contentPane, changeCid, DeveloperPage.this);
	}

}
