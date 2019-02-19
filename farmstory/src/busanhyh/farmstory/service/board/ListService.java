package busanhyh.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;
import busanhyh.farmstory.vo.BoardVO;

public class ListService implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		
		req.setAttribute("cate", cate);
		req.setAttribute("gr", gr);
		
		return "/board/list.jsp";

	}

}
