package kr.co.board1.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.board1.config.DBConfig;
import kr.co.board1.vo.TermsVO;

public class MemberService {
	//440p 싱글톤 객체
	private static MemberService service = new MemberService();//매번 new하면 자원낭비이므로 스태틱으로 하나 고정해두기
	public static MemberService getInstance() {
		return service;
	}
	
	private MemberService() {} //얘는 new를 못하도록(싱글톤객체를 쓰도록) 막기위해 private로 지정해준것
	//이걸 안쓰면 그냥 new로 쓸 수 있음. 기본은 public이기때문에
	
	public void login() {}
	public void logout() {}

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
		TermsVO vo = null;
		
		if(rs.next()){
			vo = new TermsVO();
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}
		
		//2-6. 서버닫기
		rs.close();
		stmt.close();
		conn.close();
		
		return terms;
	}
	public void register() {}
}
