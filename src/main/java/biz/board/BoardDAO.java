package biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import biz.common.JDBCUtil;
import controller.board.UpdateBoardController;

public class BoardDAO {

	// DB연결용
	private Connection conn;
	// 쿼리 실행용
	private PreparedStatement stmt;
	// 쿼리 결과받아오기
	private ResultSet rs;
	
	
	
	// 게시글을 쓸 때
	private static String BOARD_INSERT = "insert into board(seq, title, writer, content, hit) "
			+ "values((select nvl(max(seq),0)+1 from board), ?, ?, ?, ?)";

	public void insertBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.setInt(4, vo.getHit());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	
	
	
	
	
	
	
	
	
	// 게시글 업데이트 할 때
	private static String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, CONTENT=?, HIT=? WHERE SEQ=? ";

	public void updateBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getHit());
			stmt.setInt(4, vo.getSeq());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	
	
	
	
	// 게시글 삭제할 때
	private static String BOARD_DELETE = "DELETE BOARD WHERE SEQ=? ";

	public void deleteBoard(BoardVO vo) {
		BoardVO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	
	
	
	
	
	

	// 단일 게시글 가져오기
	private static String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";

	public BoardVO getBoard(BoardVO board) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, board.getSeq());

			rs = stmt.executeQuery();

			if (rs.next()) {
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setHit(rs.getInt("HIT")+1);
			}
			
			//DB에 반영할 코드가 요기!!.

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		updateBoard(board);
		return board;
	}
	
	
	

	// 전체 게시글 리스트 가져오기
	private static String BOARD_LIST = "select * from board";

	public List<BoardVO> getBoardList(BoardVO vo) {
		// BoardVO를 집어넣을 리스트를 미리 만들어놓고~~
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setHit(rs.getInt("HIT"));

				// 다 받아오고나면 리스트로 추가! = 하나의 온전한 게시글!
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;
	}
	
	
	
	
	
	
	
	// 검색한 게시글 리스트 가져오기
		private static String BOARD_SEARCH = "SELECT * FROM BOARD WHERE WRITER=? ";

		public List<BoardVO> searchBoardList(BoardVO vo) {
			// BoardVO를 집어넣을 리스트를 미리 만들어놓고~~
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_SEARCH);
				stmt.setString(1, vo.getWriter());
				rs = stmt.executeQuery();

				while (rs.next()) {
					BoardVO board = new BoardVO();
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegDate(rs.getDate("REGDATE"));

					// 다 받아오고나면 리스트로 추가! = 하나의 온전한 게시글!
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
