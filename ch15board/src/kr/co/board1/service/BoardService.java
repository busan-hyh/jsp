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
	public MemberVO getMember(HttpSession session) {//���־��ϱ� memberVO�� ���� ����� 
		MemberVO member = (MemberVO)session.getAttribute("member");
		return member;
	}
	
	public void insertBoard() throws Exception {}
	public void list() throws Exception {}
	/*public void write(String... args, int file) throws Exception {//args ���� ���...! 
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
		//throws Exception�� db������ �ƴҶ� ���ʿ�
		int start = 0;
		//pg���� ���޾Ƽ� list.jsp?pg=�� ���� ������Ʈ������ �߹Ƿ� NULLüũ�� �ؾ��Ѵ�. 
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
		//ex>[currentPage/10+1] 3pg�� ��� 0.33...������ int�̹Ƿ� 0�� ����. �ű�+1
		//	 [(int)Math.ceil(currentPage/10.0)] ������ 10pg�� +1�ϸ� �ȵǹǷ� 3pg�� 10.0�� ������ 0.333�� ���ö� �ø��ϸ� �ȴ�
		int groupStart = (currentPageGroup - 1) * 10 + 1; //�� �׷��� ���۹�ȣ ���
		int groupEnd = currentPageGroup * 10; //�� �׷��� ����ȣ ���
		if (groupEnd > pgEnd) { //39pg���� �ִµ� 40�� �߸� �ȵǹǷ�
			groupEnd = pgEnd;
		}
		groupStartEnd[0] = groupStart;
		groupStartEnd[1] = groupEnd;
		
		return groupStartEnd;
	}

}
