package busanhyh.farmstory.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import busanhyh.farmstory.controller.CommonAction;

public class HelloService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String gr = req.getParameter("gr");
		req.setAttribute("gr", gr);
		
		return "/introduction/hello.jsp";
	}

}
