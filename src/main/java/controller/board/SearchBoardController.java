package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.board.BoardDAO;
import biz.board.BoardVO;
import controller.Controller;

public class SearchBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		//글쓴이로 검색하는 거라고 치자.
		String writer = request.getParameter("writer");
		BoardVO vo = new BoardVO();
		vo.setWriter(writer);

		// DAO로 넘겨서 게시글 가져오고
		BoardDAO dao = new BoardDAO();
		List<BoardVO> boardList = dao.searchBoardList(vo);

		request.setAttribute("boardList", boardList);

		return "getBoardList.jsp";
	}

}
