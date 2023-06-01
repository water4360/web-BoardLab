package controller;

import java.util.HashMap;
import java.util.Map;

import controller.board.DeleteBoardController;
import controller.board.GetBoardController;
import controller.board.GetBoardListController;
import controller.board.InsertBoardController;
import controller.board.SearchBoardController;
import controller.board.UpdateBoardController;
import controller.user.InsertUserController;
import controller.user.LoginController;
import controller.user.LogoutController;

//클라이언트에서 Controller요청이 들어오면 얘가 상속받아서 넘겨주는...??...  
public class HandlerMapping {
	//선언. 여기서의 Controller = 만들어둔 인터페이스.
	private Map<String, Controller> mappings;
	//초기화
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		//insertUser.do가 들어오면 InsertUserControlloer()로 안내!
		mappings.put("/insertUser.do", new InsertUserController());
		mappings.put("/login.do", new LoginController());
		
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/searchBoard.do", new SearchBoardController());
		
		mappings.put("/logout.do", new LogoutController());
	}
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
