package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.board.BoardDAO;
import biz.board.BoardVO;
import controller.Controller;

public class GetBoardListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//얘는 뭐 리퀘스트를 받고 DB에 저장하고 그런게 아니기 때문에 별게 없음
		BoardVO vo = new BoardVO();
		BoardDAO dao = new BoardDAO();
		
		//DAO에서 이미 게시글들을 담아왔고
		List<BoardVO> boardList = dao.getBoardList(vo);
		
		//그걸 가지고 여기서 뭘 하는게 아니라 view로 넘겨줘야 하기 때문
		request.setAttribute("boardList", boardList);
		
		return "getBoardList.jsp";
	}
}
