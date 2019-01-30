package board2.service.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.config.DBConfig;
import board2.controller.CommonAction;

public class RegisterService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("GET")) {
			// /register.do ��û : a�±��� ��ũ�ּ� : GET
			return "/register.jsp";
		} else {
			// GET�� �ƴѰ� : POST
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pw1");
			String name = req.getParameter("name");
			String nick = req.getParameter("nick");
			String email = req.getParameter("email");
			String hp = req.getParameter("hp");
			String zip = req.getParameter("zip");
			String addr1 = req.getParameter("addr1");
			String addr2 = req.getParameter("addr2");
			String regip = req.getRemoteAddr();//������ ������

			Connection conn = DBConfig.getConnection();
			
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
			
			//�����̷�Ʈ�� ����
			return "redirect:/ch19boardModel2/member/login.do";
		}
		
	}
}
