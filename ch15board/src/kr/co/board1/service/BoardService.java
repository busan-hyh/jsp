package kr.co.board1.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
	public MemberVO getMember(HttpSession session) {//자주쓰니까 memberVO를 모듈로 만들기 
		MemberVO member = (MemberVO)session.getAttribute("member");
		return member;
	}
	
	public void insertBoard() throws Exception {}
	public void list() throws Exception {}
	/*public void write(String... args, int file) throws Exception {//args 쓰는 방법...! 
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_BOARD);
		psmt.setString(1, args[0]);
		psmt.setString(2, args[1]);
		psmt.setInt(3, file);
		psmt.setString(4, args[2]);
		psmt.setString(5, args[3]);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	}*/
	
	public void updateHit(String seq) throws Exception {
		Connection conn = DBConfig.getConnection();
		//업데이트할때//
		PreparedStatement psmt = conn.prepareStatement(SQL.UPDATE_HIT);
		psmt.setString(1, seq);
		
		psmt.executeUpdate();
		
		psmt.close();
	}
	public void deleteHit(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement psmt = conn.prepareStatement(SQL.DELETE_HIT);
		psmt.setString(1, seq);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
		//return seq;
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
		request.setCharacterEncoding("UTF-8");
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
	
	public String writeComment(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String parent = request.getParameter("seq");
		String comment = request.getParameter("comment");
		String uid = request.getParameter("uid");//여기서 멤VO를 불러와서 넣을수 없나봄.. 세션이 잘 안먹힘?.?
		String regip = request.getRemoteAddr();//아무튼 기왕이면 히든된 리퀘스트로 불러오는게 편리한듯!_!
		Connection conn = DBConfig.getConnection();
		
		CallableStatement call = conn.prepareCall(SQL.CALL_COMM);
		call.setString(1, parent);
		call.setString(2, comment);
		call.setString(3, uid);
		call.setString(4, regip);
		call.execute();
		
		call.close();
		conn.close();
		
		return parent;
	}
	public ArrayList<BoardVO> listComment(String seq) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_COMM);
		psmt.setString(1, seq);
		ResultSet rs = psmt.executeQuery();
		
		ArrayList<BoardVO> commentList = new ArrayList<>();
		
		while(rs.next()){
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt(1));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			//vo.setCate(rs.getString(4)); 코멘트는 null이라 에러남
			//vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));
			vo.setNick(rs.getString("nick"));//숫자로 순서를 표시해도 되고 컬럼명을 지정해도 됨
			
			commentList.add(vo);
			}
		
		rs.close();
		psmt.close();
		conn.close();
		
		return commentList;
	}

	public int getTotal() throws Exception {
		int total = 0;
		Connection conn = DBConfig.getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(SQL.SELECT_TOTAL);
		if(rs.next()) {
			total = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return total;
	}
	public int getLimitStart(String pg) {
		//throws Exception은 db연동이 아닐땐 노필요
		int start = 0;
		//pg값을 못받아서 list.jsp?pg=를 띄우면 널포인트에러가 뜨므로 NULL체크를 해야한다. 
		if(pg == null){
			start = 1;
		}else{
			start = Integer.parseInt(pg);
		}
		return (start - 1) * 10;
	}
	public int getPageEnd(int total) {
		int pgEnd = 0;
		
		if(total % 10 == 0) {
			pgEnd = total/10;
		} else {
			pgEnd = (total/10) + 1;
		}
		return pgEnd;
	}
	public int[] getPageGroupStartEnd(String pg, int pgEnd) {
		int[] groupStartEnd = new int[2];
		
		int currentPage = Integer.parseInt(pg);
		int currentPageGroup = (int)Math.ceil(currentPage/10.0);
		//ex>[currentPage/10+1] 3pg의 경우 0.33...이지만 int이므로 0이 나옴. 거기+1
		//	 [(int)Math.ceil(currentPage/10.0)] 하지만 10pg는 +1하면 안되므로 3pg를 10.0로 나눠서 0.333이 나올때 올림하면 된당
		int groupStart = (currentPageGroup - 1) * 10 + 1; //각 그룹의 시작번호 계산
		int groupEnd = currentPageGroup * 10; //각 그룹의 끝번호 계산
		if (groupEnd > pgEnd) { //39pg까지 있는데 40이 뜨면 안되므로
			groupEnd = pgEnd;
		}
		groupStartEnd[0] = groupStart;
		groupStartEnd[1] = groupEnd;
		
		return groupStartEnd;
	}

}
