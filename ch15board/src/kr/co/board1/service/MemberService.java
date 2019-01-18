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
	//440p 싱글톤 객체
	private static MemberService service = new MemberService();//매번 new하면 자원낭비이므로 스태틱으로 하나 고정해두기
	public static MemberService getInstance() {
		return service; //new가 아니라 겟인스턴스로 불러올수 있다.
	}
	
	private MemberService() {} //얘는 new를 못하도록(싱글톤객체를 쓰도록) 막기위해 private로 지정해준것
	//이걸 안쓰면 그냥 new로 쓸 수 있음. 기본은 public이기때문에
	
	public String login(HttpServletRequest request, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pass");
		
		String redirectUrl = null;
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LOGIN);
		psmt.setString(1, uid);
		psmt.setString(2, pass);
		ResultSet rs = psmt.executeQuery(); //select문은 resultSet필요
		
		//5단계 세션저장
		if(rs.next()){
			//회원일 경우(쿼리참조!) 세션에 저장
			MemberVO vo = new MemberVO();//자동으로 SID라는 브라우저 식별값을 추가함.
			vo.setSeq(rs.getInt(1)); //public은 sql처럼 바로 불러올 수있지만 private는 set으로 불러온다.
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
			//회원이 아닐 경우
			redirectUrl = "../login.jsp?result=fail";
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
		return redirectUrl;
		
	}
	public void logout(HttpSession session, HttpServletResponse response) throws Exception { 
		session.invalidate();//세션값 삭제
		response.sendRedirect("../login.jsp");
	}
	
	public TermsVO terms() throws Exception {
		//2. DB연결 후 쿼리실행 모~~두 외울것!!!
		Connection conn = DBConfig.getConnection();
		
		//2-3. 쿼리실행체 생성
		Statement stmt = conn.createStatement();
		
		//2-4. 쿼리 실행
		//SELECT문일때는 executeQuery를 쓴다. SELECT만 ResultSet이 표시되므로 대입연산자가 필요.(콘솔이 필요한것처럼) 
		//그외 명령문은 executeUpdate를 쓴다.
		ResultSet rs = stmt.executeQuery("SELECT * FROM `JSP_TERMS`;"); //rs객체가 불러온 데이터임!!
		
		//2-5. 데이터 설정
		TermsVO vo = null; //TermsVO를 쓰기 위한 제목 void대신 TermsVO
		
		if(rs.next()){
			vo = new TermsVO();
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}
		
		//2-6. 서버닫기
		rs.close();
		stmt.close();
		conn.close();
		
		return vo;
	}
	public void register(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");//request를 받기위한 HttpServletRequest request 
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pw1");
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
		String email = request.getParameter("email");
		String hp = request.getParameter("hp");
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String regip = request.getRemoteAddr();//브라우저 아이피

		final String HOST = "jdbc:mysql://192.168.0.126:3306/hyh";
		final String USER = "hyh";
		final String PASS = "1234";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		String  sql  = "INSERT INTO `JSP_MEMBER` SET ";
		sql += "uid=?,";
		sql += "pass=PASSWORD(?),"; //암호형식 MD5 으로 저장 
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
