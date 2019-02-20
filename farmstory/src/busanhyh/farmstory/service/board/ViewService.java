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

public class ViewService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		String seq = req.getParameter("seq");
		
		req.setAttribute("cate", cate);
		req.setAttribute("gr", gr);
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_VIEW_WIHT_FILE);
		psmt.setString(1, seq);//string으로 파라미터가 들어가므로 ?seq='1' 이 주소가 된다. int든 String이든 SELECT에선 같음
		
		ResultSet rs = psmt.executeQuery();
		BoardVO vo = new BoardVO();
		
		if(rs.next()){
			vo.setSeq(rs.getInt(1));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setCate(rs.getString(4));
			vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));
			vo.setOldName(rs.getString(14));
			vo.setNewName(rs.getString(15));
			vo.setDownload(rs.getInt(16));
		}
		
		
		rs.close();
		psmt.close();
		conn.close();
		
		req.setAttribute("vo", vo);
		
		return "/board/view.jsp";
	}

}
