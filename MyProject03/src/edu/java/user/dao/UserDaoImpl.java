package edu.java.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.java.user.model.Score;
import edu.java.user.model.User;
import oracle.jdbc.OracleDriver;

import static edu.java.user.model.User.Entuty.*;
import static edu.java.user.oracle.Oracle.*;

public class UserDaoImpl implements UserDao {

	// 싱글턴
	private static UserDaoImpl instance = null;

	private UserDaoImpl() {
	}

	public static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	// 오라클 db에 접속한 connection 객체를 리턴하는 메서드 , 만들어두면 자동 로그인이 가능해진다,
	private Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		return conn;
	}

	private void closeResources(Connection conn, PreparedStatement stmt) throws SQLException {
		stmt.close();
		conn.close();
	}

	private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {
		rs.close();
		closeResources(conn, stmt);
	}

	// ===========================================================================
	// static final String 객체 묶기

	private static final String USER_SELECT_ALL = String.format("select * from %s order by %s ", TBL_name, COL_cid);
	
	private static final String USER_SELECT_ONE = String.format("select * from %s where %s = ? ", TBL_name, COL_cid);
	
	
	
	
	private static final String USER_SELECT_LIST = String.format(
			"select * from %s where lower(%s) like lower(?) or lower(%s) like lower(?) or lower(%s) like lower(?) order by %s ",
			TBL_name, Col_name, COL_nickname, COL_id, COL_cid);

	private static final String USER_SELECT_LISTU = String.format(
			"select * from %s where %s = ? and %s =? order by %s desc", TBL_name, Col_name, COL_phone, COL_make_time);
	
	
	
	

	private static final String USER_INSERT_ONE = String.format(
			"insert into %s ( %s, %s, %s, %s, %s, %s, %s, %s, %s ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )", TBL_name,
			COL_id, COL_nickname, COL_top_score, COL_top_time, Col_name, COL_phone, COL_email, COL_make_time, COL_pass);

	private static final String USER_UPDATE_ONE = String.format(
			"update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
			TBL_name, COL_id, COL_nickname, COL_top_score, COL_top_time, Col_name, COL_phone, COL_email, COL_make_time,
			COL_pass, COL_cid);

	private static final String USER_DELETE_ONE = String.format("delete from %s where %s = ?", TBL_name, COL_cid);

	// ===========================================================================
	// 메모리에서 파일로변환시키기
	@Override
	public int create(User u) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = getConnection();

			stmt = conn.prepareStatement(USER_INSERT_ONE);

			stmt.setString(1, u.getId());
			stmt.setString(2, u.getNickName());
			stmt.setInt(3, u.getScore().getTopScpre());
			Timestamp Tts = Timestamp.valueOf(u.getScore().getTime());
			stmt.setTimestamp(4, Tts);
			stmt.setString(5, u.getName());
			stmt.setString(6, u.getPhone());
			stmt.setString(7, u.getEmail());
			Timestamp ts = Timestamp.valueOf(u.getTime());
			stmt.setTimestamp(8, ts);
			stmt.setString(9, u.getPass());

			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 리스트 보내기
	@Override
	public List<User> read() {

		List<User> users = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(USER_SELECT_ALL);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int cid = rs.getInt(COL_cid);
				String id = rs.getString(COL_id);
				String pass = rs.getString(COL_pass);
				String name = rs.getString(Col_name);
				String phone = rs.getString(COL_phone);
				String email = rs.getString(COL_email);
				String nickName = rs.getString(COL_nickname);
				Timestamp ts1 = rs.getTimestamp(COL_make_time);
				LocalDateTime time = ts1.toLocalDateTime();
				int topScpre = rs.getInt(COL_top_score);
				Timestamp ts2 = rs.getTimestamp(COL_top_time);
				LocalDateTime Ttime = ts2.toLocalDateTime();

				Score s = new Score(topScpre, Ttime);

				User u = new User(cid, id, pass, name, phone, email, nickName, time, s);
				users.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	// 개발자 입장에서 특정 유저 검색
	@Override
	public List<User> readDeveloper(String identity) {
		List<User> users = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(USER_SELECT_LIST);

			String ss = "%" + identity + "%";

			stmt.setString(1, ss);
			stmt.setString(2, ss);
			stmt.setString(3, ss);

			rs = stmt.executeQuery();

			while (rs.next()) {
				int cid = rs.getInt(COL_cid);
				String id = rs.getString(COL_id);
				String pass = rs.getString(COL_pass);
				String name = rs.getString(Col_name);
				String phone = rs.getString(COL_phone);
				String email = rs.getString(COL_email);
				String nickName = rs.getString(COL_nickname);
				Timestamp ts1 = rs.getTimestamp(COL_make_time);
				LocalDateTime time = ts1.toLocalDateTime();
				int topScpre = rs.getInt(COL_top_score);
				Timestamp ts2 = rs.getTimestamp(COL_make_time);
				LocalDateTime Ttime = ts2.toLocalDateTime();

				Score s = new Score(topScpre, Ttime);

				User u = new User(cid, id, pass, name, phone, email, nickName, time, s);
				users.add(u);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	// 유저 입장에서 본인 아이디 검색
	@Override
	public List<User> readUser(String name, String phone) {
		List<User> users = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(USER_SELECT_LISTU);

			stmt.setString(1, name);
			stmt.setString(2, phone);

			rs = stmt.executeQuery();

			while (rs.next()) {
				int cid = rs.getInt(COL_cid);
				String id = rs.getString(COL_id);
				String pass = rs.getString(COL_pass);
				String name1 = rs.getString(Col_name);
				String phone1 = rs.getString(COL_phone);
				String email = rs.getString(COL_email);
				String nickName = rs.getString(COL_nickname);
				Timestamp ts1 = rs.getTimestamp(COL_make_time);
				LocalDateTime time = ts1.toLocalDateTime();
				int topScpre = rs.getInt(COL_top_score);
				Timestamp ts2 = rs.getTimestamp(COL_make_time);
				LocalDateTime Ttime = ts2.toLocalDateTime();

				Score s = new Score(topScpre, Ttime);

				User u = new User(cid, id, pass, name1, phone1, email, nickName, time, s);
				users.add(u);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	// 정보수정 기능
	@Override
	public int update(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		// update 문장
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(USER_UPDATE_ONE);


			stmt.setInt(10, user.getCid());

			stmt.setString(1, user.getId());
			stmt.setString(2, user.getNickName());
			stmt.setInt(3, user.getScore().getTopScpre());
			Timestamp ts1 = Timestamp.valueOf(user.getScore().getTime());
			stmt.setTimestamp(4, ts1);
			stmt.setString(5, user.getName());
			stmt.setString(6, user.getPhone());
			stmt.setString(7, user.getEmail());
			Timestamp ts2 = Timestamp.valueOf(user.getTime());
			stmt.setTimestamp(8, ts2);
			stmt.setString(9, user.getPass());

			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int delete(int cid) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(USER_DELETE_ONE);

			stmt.setInt(1, cid);

			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public User readCid(int cid) {

		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(USER_SELECT_ONE);

			stmt.setInt(1, cid);

			rs = stmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString(COL_id);
				String pass = rs.getString(COL_pass);
				String name = rs.getString(Col_name);
				String phone = rs.getString(COL_phone);
				String email = rs.getString(COL_email);
				String nickName = rs.getString(COL_nickname);
				Timestamp ts1 = rs.getTimestamp(COL_make_time);
				LocalDateTime time = ts1.toLocalDateTime();
				int topScpre = rs.getInt(COL_top_score);
				Timestamp ts2 = rs.getTimestamp(COL_make_time);
				LocalDateTime Ttime = ts2.toLocalDateTime();

				Score s = new Score(topScpre, Ttime);

				user = new User(cid, id, pass, name, phone, email, nickName, time, s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return user;
	}

}
