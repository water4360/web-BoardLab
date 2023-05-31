package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.user.UserDAO;
import biz.user.UserVO;
import controller.Controller;

public class InsertUserController implements Controller{
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//일단 간단하게 id만 넣어보고.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		
		//파라미터로 전달받은 데이터를 VO에 담아주기
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setRole(role);
		
		//VO에 담아준 건 DAO를 통해서 넘기면 회원가입 완료.
		UserDAO dao = new UserDAO();
		dao.insertUsers(vo);
		
		//끝나면 다시 처음의 login 페이지로 돌아갈 수 있도록.
		return "login.html";
	}

}
