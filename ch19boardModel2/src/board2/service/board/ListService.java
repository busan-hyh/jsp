package board2.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board2.controller.CommonAction;

public class ListService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("member") != null) {
			return "/list.jsp";
		} else {
			return "redirect:/ch19boardModel2/member/login.do";
		}
		
		
	}
	
}
