package kr.co.board1.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.board1.config.DBConfig;
import kr.co.board1.vo.TermsVO;

public class MemberService {
	//440p �̱��� ��ü
	private static MemberService service = new MemberService();//�Ź� new�ϸ� �ڿ������̹Ƿ� ����ƽ���� �ϳ� �����صα�
	public static MemberService getInstance() {
		return service;
	}
	
	private MemberService() {} //��� new�� ���ϵ���(�̱��水ü�� ������) �������� private�� �������ذ�
	//�̰� �Ⱦ��� �׳� new�� �� �� ����. �⺻�� public�̱⶧����
	
	public void login() {}
	public void logout() {}

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
		TermsVO vo = null;
		
		if(rs.next()){
			vo = new TermsVO();
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}
		
		//2-6. �����ݱ�
		rs.close();
		stmt.close();
		conn.close();
		
		return terms;
	}
	public void register() {}
}
