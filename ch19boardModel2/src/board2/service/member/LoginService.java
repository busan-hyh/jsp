package board2.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.controller.CommonAction;

public class LoginService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/login.jsp";
	}
	
}
