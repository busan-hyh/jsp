package busanhyh.farmstory.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import busanhyh.farmstory.controller.CommonAction;

public class OnetooneService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		return "/community/onetoone.jsp";
	}

}
