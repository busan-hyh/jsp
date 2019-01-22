package kr.co.board1.service;

import java.sql.CallableStatement;
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
	public MemberVO getMember(HttpSession session) {//���־��ϱ� memberVO�� ���� ����� 
		MemberVO member = (MemberVO)session.getAttribute("member");
		return member;
	}
	
	public void insertBoard() throws Exception {}
	public void list() throws Exception {}
	
	public void updateHit(String seq) throws Exception {
		Connection conn = DBConfig.getConnection();
		//������Ʈ�Ҷ�//
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
		
		updateHit(seq);//���� updateHit�� �����Ű�鼭 seq�� ������->������������ ����������� �ٲ�
		//����Ʈ�Ҷ�//
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_VIEW);
		psmt.setString(1, seq);//string���� �Ķ���Ͱ� ���Ƿ� ?seq='1' �� �ּҰ� �ȴ�. int�� String�̵� SELECT���� ����
		
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
		String uid = request.getParameter("uid");//���⼭ ��VO�� �ҷ��ͼ� ������ ������.. ������ �� �ȸ���?.?
		String regip = request.getRemoteAddr();//�ƹ�ư ����̸� ����� ������Ʈ�� �ҷ����°� ���ѵ�!_!
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
			//vo.setCate(rs.getString(4)); �ڸ�Ʈ�� null�̶� ������
			//vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));
			vo.setNick(rs.getString("nick"));//���ڷ� ������ ǥ���ص� �ǰ� �÷����� �����ص� ��
			
			commentList.add(vo);
			}
		
		rs.close();
		psmt.close();
		conn.close();
		
		return commentList;
	}
}
