package busanhyh.farmstory.service.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.controller.CommonAction;
import busanhyh.farmstory.vo.TermsVO;

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
		
		//모델1과 다르게 리턴해서 jsp에서 쓰는게 아니고
		//리퀘스트에 vo를 담아둠
		req.setAttribute("vo", vo);
		return "/member/terms.jsp";
	}

}
