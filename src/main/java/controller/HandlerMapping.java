package controller;

import java.util.HashMap;
import java.util.Map;

//클라이언트에서 Controller요청이 들어오면 얘가 상속받아서 넘겨주는...??...  
public class HandlerMapping {
	//선언. 여기서의 Controller = 만들어둔 인터페이스.
	private Map<String, Controller> mappings;
	//초기화
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
	}
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
