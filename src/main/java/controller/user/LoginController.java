package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

import biz.user.UserDAO;
import biz.user.UserVO;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// 일단 간단하게 id만 넣어보고.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// 사용자가 로그인할때 입력한 값을 VO에 담아주기
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPw(pw);

		UserDAO dao = new UserDAO();
		
		// VO에 담아준 걸로 DAO에서 비교해서 일치하는 사용자 정보 가져온게 user
		UserVO user = dao.getUser(vo);
		
		if(user != null ) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "getBoardList.do";
		} else {
			return "login.html";
		}
	}
}
