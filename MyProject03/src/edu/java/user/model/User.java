package edu.java.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User {
	
	private int cid;
	private String id;
	private String pass;
	private String name;
	private String phone;
	private String email;
	private String NickName;
	private LocalDateTime time;
	private Score score;

	
	public interface Entuty {
		// 테이블,컬럼 상수
		String TBL_name = "user_table";
		String COL_cid = "cid";
		String COL_id = "id";
		String Col_name = "name";
		String COL_pass= "pass";
		String COL_phone = "phone";
		String COL_email = "email";
		String COL_nickname = "nickname";
		String COL_make_time = "make_time";
		String COL_top_score = "top_score";
		String COL_top_time = "top_time";
	}
	


	public User(int cid) {
		this.cid = cid;
	}

	public User(String id, String pass, String name, String phone, String email, String nickName, LocalDateTime time, Score score) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.NickName = nickName;
		this.time = time;
		this.score = score;
	}
	
	public User(int cid, String id, String pass, String name, String phone, String email, String nickName, LocalDateTime time, Score score) {
		this.cid = cid;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.NickName = nickName;
		this.time = time;
		this.score = score;
	}

	// 메서드 게터세터 투스트링

	@Override
	public String toString() {

		return String.format("User (id = %s), (pass = %s), (name = %s), (phone = %s), (email = %s), (nickName = %s)",
				this.id, this.pass, this.name, this.phone, this.email, this.NickName);
	}
	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Score getScore() {
		return score;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getCid() {
		return cid;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

}
