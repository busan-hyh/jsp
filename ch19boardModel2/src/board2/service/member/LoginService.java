package board2.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board2.config.DBConfig;
import board2.config.SQL;
import board2.controller.CommonAction;
import board2.vo.MemberVO;



public class LoginService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//String method = req.getMethod();
		//System.out.println(method);
		HttpSession session = req.getSession();//���� ���ؿ���
		
		if(req.getMethod().equals("POST")) {
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pass");
			String redirectUrl = null;
			
			Connection conn = DBConfig.getConnection();
			PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LOGIN);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			
			ResultSet rs = psmt.executeQuery();
			
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
				
				redirectUrl = "/ch19boardModel2/list.do";
				//�����̷�Ʈ�� �տ� �ּұ��� �ʿ�..
			} else {
				//ȸ���� �ƴ� ���
				redirectUrl = "/ch19boardModel2/member/login.do?result=fail";
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
			return "redirect:"+redirectUrl;
			//else�� �����̷�Ʈ�� ���� ���ٷ���û�� �ʹ� �������� ���ٷ��� if post�� �ø�
		} else {
			//�α��μ��� üũ
			if(session.getAttribute("member") != null) {
				return "redirect:/ch19boardModel2/list.do";
			} else {
				return "/login.jsp";
			}
		}
	}
	
}
