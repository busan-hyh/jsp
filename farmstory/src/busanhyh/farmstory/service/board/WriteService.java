package busanhyh.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;
import busanhyh.farmstory.vo.MemberVO;

public class WriteService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		
		String method = req.getMethod();
		
		if(method.equals("POST")) {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			String uid = member.getUid();
			String title	= req.getParameter("subject");
			String content	= req.getParameter("content");
			String regip   	= req.getRemoteAddr();
			int file		= 0;
			
			Connection conn = DBConfig.getConnection();
			
			String sql = "INSERT INTO `JSP_BOARD` SET ";
			sql += "cate=?, ";
			sql += "title=?, ";
			sql += "content=?, ";
			sql += "file=?, ";
			sql += "uid=?, ";
			sql += "regip=?";
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, cate);
			psmt.setString(2, title);
			psmt.setString(3, content);
			psmt.setInt(4, file);
			psmt.setString(5, uid);
			psmt.setString(6, regip);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			return "redirect:/farmstory/board/list.do?gr="+gr+"&cate="+cate;
		} else {
			req.setAttribute("cate", cate);
			req.setAttribute("gr", gr);
			
			return "/board/write.jsp";
		}
	}
}
