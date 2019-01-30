package board2.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.controller.CommonAction;

public class ListService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/list.jsp";
	}
	
}
