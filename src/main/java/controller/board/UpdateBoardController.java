package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.board.BoardDAO;
import biz.board.BoardVO;
import controller.Controller;

public class UpdateBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//글을 수정할 때에 얘가 받는 parameter는 뭐뭐뭐가 있찌?
		//글번호, 제목, 내용!
		int seq = Integer.parseInt(request.getParameter("seq"));
		int hit = Integer.parseInt(request.getParameter("hit"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		//uri에서 받아온 정보를 VO에 저장
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setHit(hit);
		
		//DAO로 넘겨서 게시글 가져오고
		BoardDAO dao = new BoardDAO();
		dao.updateBoard(vo);
		
//		request.setAttribute("board", dao);
		
		return "getBoardList.do";
	}

}
