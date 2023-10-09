package edu.java.user.dao;

import java.util.List;

import edu.java.user.model.User;

//crud 기능 인터페이스
public interface UserDao {

	/**
	 * 새연락처 생성 기능.
	 * User타입 아규먼츠가 들어오면 해당 객체를 추가함
	 * 추가성공시 0리턴, 실패시 1리턴
	 * @param User타입 객체
	 * @return 0 or 1
	 */
	int create(User u);
	
	/**
	 * 목록읽어오기 기능.
	 * 해당 메서드가 불리면 그 동안 저장된 User타입의 객체들을 모두 담은 리스트 출력
	 * @return List<User> 타입의 전체목록
	 */
	List<User> read();
	
	/**
	 * 개발자 검색 기능.
	 * 해당 메서드가 불리면 아규먼트에 해당하는 이름, 닉네임, 아이디를 가진 유저가 출력됨.
	 * 검색값이 없을땐 빈 List<User> 리스트 출력
	 * (추가로 같은 글자가 2개 이상 있는 모든 정보가 출력된다.)
	 * @param String 타입 검색할 이름/닉네임
	 * @return List<User> 타입에 검색된 모든 목록
	 */
	List<User> readDeveloper(String something);
	
	/**
	 * Cid를 받아 User를 리턴하는 기능.
	 * @param User 필드의 정수 cid
	 * @return 아규먼트의 cid값을 가진 유저타입 유저를 리턴
	 */
	User readCid(int cid);
	
	/**
	 * 유저 검색 기능.
	 * 유저가 본인의 아이디나, 비밀번호를 잊어버렸을때, 이름과 전화번호를 통해
	 * 아이디를 찾고, 비밀번호를 재설정하는 과정의 기능.
	 * (같은이름 번호의 모든 정보가 부분적으로 표시된다.)
	 * @param String 타입 검색할 이름/닉네임
	 * @return List<User> 타입에 검색된 모든 목록
	 */
	List<User> readUser(String name, String phone);
	
	/**
	 * 정보 수정 기능. 
	 * 개발자 입장에선 모든 정보를 수정할 수 있는기능.(cid 제외)
	 * 유저 입장에선 User의 비밀번호 재설정/닉네임을 변경할 수 있는 기능.
	 * 수정 성공시 0, 실패시 1출력
	 * @param User 변경될 User 타입의 객체
	 * @return 0 or 1
	 */
	int update(User user);
	
	/**
	 * 정보 삭제 기능.
	 * 삭제할 유저의 cid 정보를 받아, 계정을 삭제하는 기능
	 * 개발자 입장에서는 모든 계정을 삭제 가능하고,
	 * 유저 입장에서는 본인의 정보만을 삭제 가능합니다.
	 * @param 삭제할 
	 * @return
	 */
	int delete(int cid);
	
	
	
}
