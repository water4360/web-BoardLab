package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class LogoutController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//현재 세션을 가져오고
		HttpSession session = request.getSession();
		//가져온 세션을 무효화하는 것으로 로그아웃 처리
		session.invalidate();
		
		//끝나면 다시 로긴화면으로 이동되도록.
		return "login.html";
	}
}
