package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//있던 annotations 지워버림. 나중에 web.xml에서 넣어주려고.
//여기에 annotation이 있으면 거기로 들어오는 것만 처리해줌.
//하지만 우리는 들어오는 모든 것들을 처리해주기 위해 지운 것임.
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//여기서 선언해줬고
	private HandlerMapping mapping;
	
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //여기서 오버라이딩해서 초기화 해줬음.
    @Override
    public void init() throws ServletException {
    	mapping = new HandlerMapping();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//메소드 만들어줄 것임.
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		//슬래쉬 마지막에 나오는 걸로부터 경로 추출
		String path = uri.substring(uri.lastIndexOf("/"));
		//여기로 path가 넘어가면 해당 path에 대한 컨트롤러가 리턴되고
		Controller ctrl = mapping.getController(path);
		String viewPage = ctrl.handleRequest(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

}
