package controller.board;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.board.BoardDAO;
import biz.board.BoardVO;
import controller.Controller;

public class InsertBoardController implements Controller {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		int seq = Integer.parseInt(request.getParameter("SEQ"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
//		//날짜 변환 증말...
//		Date regDate = null;
//		try {
//			regDate = sdf.parse(request.getParameter("SEQ"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		//사용자가 입력한 데이터를 VO에 저장
		BoardVO vo = new BoardVO();
//		vo.setSeq(seq);
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
//		vo.setRegDate(regDate);
		
		
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(vo);
		
		//게시판리스트 화면으로?
		//아니면 업로드완료 화면으로?
//		return "board.html";
//		return "uploadCompleted.html";
		return "getBoardList.do";
	}
}
