package busanhyh.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;

public class WriteService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		
		req.setAttribute("cate", cate);
		req.setAttribute("gr", gr);
		
		return "/board/write.jsp";

	}

}
