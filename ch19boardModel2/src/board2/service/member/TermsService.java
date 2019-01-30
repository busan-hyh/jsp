package board2.service.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.config.DBConfig;
import board2.controller.CommonAction;
import board2.vo.TermsVO;

public class TermsService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `JSP_TERMS`;");

		TermsVO vo = null;
		
		if(rs.next()){
			vo = new TermsVO();
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}

		rs.close();
		stmt.close();
		conn.close();
		
		//��1�� �ٸ��� �����ؼ� jsp���� ���°� �ƴϰ�
		//������Ʈ�� vo�� ��Ƶ�
		req.setAttribute("vo", vo);
		
		return "/terms.jsp";
	}
}