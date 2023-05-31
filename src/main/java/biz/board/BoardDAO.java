package biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import biz.common.JDBCUtil;

public class BoardDAO {

		//DB연결용
		private Connection conn;
		//쿼리 실행용
		private PreparedStatement stmt;
		//쿼리 결과받아오기
		private ResultSet rs;
		
		//USERS 테이블에 사용자 추가하는 쿼리
		//함수 하나 추가. (SELECT NVL(MAX(SEQ), 0)+1), FROM BOARD
		private static String BOARD_INSERT = "INSERT INTO BOARDS (SEQ, TITLE, WRITER, CONTENT, REGDATE) "
														+ ("VALUES ((SELECT NVL(MAX(SEQ), 0)+1 FROM BOARD), ?, ?, ?) ");
		
		private static String BOARD_LIST = "SELECT * FROM BOARD";
		
		//게시글을 쓸 때
		public void insertBoard(BoardVO vo) {
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_INSERT);
				stmt.setString(1, vo.getTitle());
				stmt.setString(2, vo.getWriter());
				stmt.setString(3, vo.getContent());
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		public List<BoardVO> getBoardList(BoardVO vo) {
			//BoardVO를 집어넣을 리스트를 미리 만들어놓고~~
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_INSERT);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					BoardVO board = new BoardVO();
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegDate(rs.getDate("REGDATE"));
					
					//다 받아오고나면 리스트로 추가! = 하나의 온전한 게시글!
					boardList.add(board);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return boardList;
		}
}
