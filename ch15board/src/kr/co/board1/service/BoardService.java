package kr.co.board1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.board1.config.DBConfig;
import kr.co.board1.config.SQL;
import kr.co.board1.vo.BoardVO;
import kr.co.board1.vo.MemberVO;

public class BoardService {
	private static BoardService service = new BoardService();
	public static BoardService getInstance() {
		return service;
	}
	private BoardService() {}
	
	public void insertBoard() throws Exception {}
	public void list() throws Exception {}
	
	public void updateHit(String seq) throws Exception {
		Connection conn = DBConfig.getConnection();
		//업데이트할때//
		PreparedStatement psmt = conn.prepareStatement(SQL.UPDATE_HIT);
		psmt.setString(1, seq);
		
		psmt.executeUpdate();
		
		psmt.close();
	}
	public BoardVO view(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();
		
		updateHit(seq);//위의 updateHit을 실행시키면서 seq를 날린다->뷰페이지에서 직접때리기로 바꿈
		//셀렉트할때//
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_VIEW);
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
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
		return vo;
	}
	
	public void delete(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();

		PreparedStatement psmt = conn.prepareStatement(SQL.DELETE_POST);
		psmt.setString(1, seq);
		
		psmt.executeUpdate();
		
		
		psmt.close();
		conn.close();
	}
	public String modify(HttpServletRequest request) throws Exception {
		String title = request.getParameter("subject");
		String content = request.getParameter("content");
		String seq = request.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();

		PreparedStatement psmt = conn.prepareStatement(SQL.UPDATE_POST);
		psmt.setString(1, title);
		psmt.setString(2, content);
		psmt.setString(3, seq);
		
		psmt.executeUpdate();
		
		
		psmt.close();
		conn.close();
		
		return seq;
	}
	
	public void insertComment() throws Exception {}
	public void listComment() throws Exception {}
}
