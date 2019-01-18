package kr.co.board1.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.board1.config.DBConfig;
import kr.co.board1.config.SQL;
import kr.co.board1.vo.MemberVO;
import kr.co.board1.vo.TermsVO;

public class MemberService {
	//440p �̱��� ��ü
	private static MemberService service = new MemberService();//�Ź� new�ϸ� �ڿ������̹Ƿ� ����ƽ���� �ϳ� �����صα�
	public static MemberService getInstance() {
		return service; //new�� �ƴ϶� ���ν��Ͻ��� �ҷ��ü� �ִ�.
	}
	
	private MemberService() {} //��� new�� ���ϵ���(�̱��水ü�� ������) �������� private�� �������ذ�
	//�̰� �Ⱦ��� �׳� new�� �� �� ����. �⺻�� public�̱⶧����
	
	public String login(HttpServletRequest request, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pass");
		
		String redirectUrl = null;
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LOGIN);
		psmt.setString(1, uid);
		psmt.setString(2, pass);
		ResultSet rs = psmt.executeQuery(); //select���� resultSet�ʿ�
		
		//5�ܰ� ��������
		if(rs.next()){
			//ȸ���� ���(��������!) ���ǿ� ����
			MemberVO vo = new MemberVO();//�ڵ����� SID��� ������ �ĺ����� �߰���.
			vo.setSeq(rs.getInt(1)); //public�� sqló�� �ٷ� �ҷ��� �������� private�� set���� �ҷ��´�.
			vo.setUid(rs.getString(2));
			vo.setPass(rs.getString(3));
			vo.setName(rs.getString(4));
			vo.setNick(rs.getString(5));
			vo.setEmail(rs.getString(6));
			vo.setHp(rs.getString(7));
			vo.setGrade(rs.getInt(8));
			vo.setZip(rs.getString(9));
			vo.setAddr1(rs.getString(10));
			vo.setAddr2(rs.getString(11));
			vo.setRegip(rs.getString(12));
			vo.setRdate(rs.getString(13));
			
			session.setAttribute("member", vo);
			
			redirectUrl = "../list.jsp";
		} else {
			//ȸ���� �ƴ� ���
			redirectUrl = "../login.jsp?result=fail";
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
		return redirectUrl;
		
	}
	public void logout(HttpSession session, HttpServletResponse response) throws Exception { 
		session.invalidate();//���ǰ� ����
		response.sendRedirect("../login.jsp");
	}
	
	public TermsVO terms() throws Exception {
		//2. DB���� �� �������� ��~~�� �ܿ��!!!
		Connection conn = DBConfig.getConnection();
		
		//2-3. ��������ü ����
		Statement stmt = conn.createStatement();
		
		//2-4. ���� ����
		//SELECT���϶��� executeQuery�� ����. SELECT�� ResultSet�� ǥ�õǹǷ� ���Կ����ڰ� �ʿ�.(�ܼ��� �ʿ��Ѱ�ó��) 
		//�׿� ��ɹ��� executeUpdate�� ����.
		ResultSet rs = stmt.executeQuery("SELECT * FROM `JSP_TERMS`;"); //rs��ü�� �ҷ��� ��������!!
		
		//2-5. ������ ����
		TermsVO vo = null; //TermsVO�� ���� ���� ���� void��� TermsVO
		
		if(rs.next()){
			vo = new TermsVO();
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}
		
		//2-6. �����ݱ�
		rs.close();
		stmt.close();
		conn.close();
		
		return vo;
	}
	public void register(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");//request�� �ޱ����� HttpServletRequest request 
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pw1");
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
		String email = request.getParameter("email");
		String hp = request.getParameter("hp");
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String regip = request.getRemoteAddr();//������ ������

		final String HOST = "jdbc:mysql://192.168.0.126:3306/hyh";
		final String USER = "hyh";
		final String PASS = "1234";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		String  sql  = "INSERT INTO `JSP_MEMBER` SET ";
		sql += "uid=?,";
		sql += "pass=PASSWORD(?),"; //��ȣ���� MD5 ���� ���� 
		sql += "name=?,";
		sql += "nick=?,";
		sql += "email=?,";
		sql += "hp=?,";
		sql += "zip=?,";
		sql += "addr1=?,";
		sql += "addr2=?,";
		sql += "regip=?,";
		sql += "rdate=NOW()";
		/*String sql = "INSERT INTO `JSP_MEMBER` (seq, uid, pass, name, nick, email, hp, regip, rdate) ";
			sql += "VALUES ('"+uid+"', '"+pass+"', '"+name+"', '"+nick+"','"+email+"','"+hp+"','"+regip+"', NOW());";*/
		//String sql = "INSERT INTO `USER` SET uid='2', name='3', hp='4', addr='5', pos='6', dep='7'";

		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		psmt.setString(2, pass);
		psmt.setString(3, name);
		psmt.setString(4, nick);
		psmt.setString(5, email);
		psmt.setString(6, hp);
		psmt.setString(7, zip);
		psmt.setString(8, addr1);
		psmt.setString(9, addr2);
		psmt.setString(10, regip);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	}
}
