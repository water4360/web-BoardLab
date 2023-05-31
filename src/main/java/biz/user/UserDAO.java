package biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import biz.common.JDBCUtil;

public class UserDAO {
	
	//DB연결용
	private Connection conn;
	//쿼리 실행용
	private PreparedStatement stmt;
	//쿼리 결과받아오기
	private ResultSet rs;
	
	//USERS 테이블에 사용자 추가하는 쿼리
	private static String USER_INSERT = "INSERT INTO USERS (ID, PASSWORD, NAME, ROLE) "
									+ "VALUES (?, ?, ?, ?) ";
	
	//UserVO 클래스 자체를 매개변수로 받아와서.
	public void insertUsers(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPw());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	
	
	
	
	
	
	
	private static String USER_GET = "SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
	
	//ID로 user를 찾고 PW로 같이 체크!
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPw());
			
			//쿼리를 실행하고
			rs = stmt.executeQuery();
			
			//실행된 결과가 있으면
			if(rs.next()) {
				//VO로 넘겨서 세션에 저장해주려고 함.
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPw(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		//데이터베이스에서 구한 user 값을 반환!
		return user;
	}

}
