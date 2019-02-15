package busanhyh.farmstory.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import busanhyh.farmstory.controller.CommonAction;



public class LogoutService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		session.invalidate();//세션값 삭제

		return "redirect:/farmstory/index.do";
	}
	
}
