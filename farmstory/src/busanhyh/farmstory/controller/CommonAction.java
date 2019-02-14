package busanhyh.farmstory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonAction {

	public abstract String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	//인터페이스는 클래스를 만드는게 아니라 형식을 만드는것!
	//~~Service.java에서 throws를 하므로 얘도 하게됨
	
}