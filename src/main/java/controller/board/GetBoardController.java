package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.board.BoardDAO;
import biz.board.BoardVO;
import controller.Controller;

public class GetBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//얘가 받는 parameter는 뭐다? seq 넘버!
		//사용자가 클릭해서 2를 보낸거니까 request로 받는거겠지?
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		//uri에서 받아온 seq 정보를 VO에 저장
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		
		//DAO로 넘겨서 게시글 가져오고
		BoardDAO dao = new BoardDAO();
		BoardVO board = dao.getBoard(vo);
		
		//그리고 이 board를 "board"라는 변수명으로 view에 넘겨주기! 
		request.setAttribute("board", board);
		
		return "getBoard.jsp";
	}

}
