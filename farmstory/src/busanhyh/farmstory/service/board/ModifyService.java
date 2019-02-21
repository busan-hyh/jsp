package busanhyh.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;
import busanhyh.farmstory.vo.BoardVO;


public class ModifyService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		String seq = req.getParameter("seq");
		
		String method = req.getMethod();
		if(method.equals("POST")) {
			req.setCharacterEncoding("UTF-8");
			String title = req.getParameter("subject");
			String content = req.getParameter("content");
			
			Connection conn = DBConfig.getConnection();

			PreparedStatement psmt = conn.prepareStatement(SQL.UPDATE_POST);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, seq);
			
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			return "redirect:/farmstory/board/view.do?gr="+gr+"&cate="+cate+"&seq="+seq;
		} else {
			req.setAttribute("cate", cate);
			req.setAttribute("gr", gr);
			req.setAttribute("seq", seq);
			
			Connection conn = DBConfig.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_VIEW);
			psmt.setString(1, seq);
			ResultSet rs = psmt.executeQuery();
			
			BoardVO vo = new BoardVO();
			
			if(rs.next()){
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
			}

			rs.close();
			psmt.close();
			conn.close();
			
			req.setAttribute("vo", vo);
			
			return "/board/modify.jsp";
		}
		
		
	}

}
