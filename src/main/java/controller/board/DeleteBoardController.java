package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.board.BoardDAO;
import biz.board.BoardVO;
import controller.Controller;

public class DeleteBoardController implements Controller{
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//파라미터로 받은 값, BoardVO의 seq는 int타입인데 String으로 넘어오니까 int로 미리 바꿔주기~
		int seq = Integer.parseInt(request.getParameter("seq"));
		BoardVO vo = new BoardVO();
		
		//vo에 seq 세팅해주기
		vo.setSeq(seq);
		
		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(vo);
		
		//끝나면 돌아갈 이정표 적어주기~~
		return "getBoardList.do";
	}

}
