package busanhyh.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;


public class DeleteService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		
		req.setCharacterEncoding("UTF-8");
		String seq = req.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();

		PreparedStatement psmt = conn.prepareStatement(SQL.DELETE_POST);
		psmt.setString(1, seq);
		
		psmt.executeUpdate();
		
		
		psmt.close();
		conn.close();
		
		return "redirect:/farmstory/board/list.do?gr="+gr+"&cate="+cate;
	}

}
