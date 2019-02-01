package board2.service.board;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import board2.config.DBConfig;
import board2.config.SQL;
import board2.controller.CommonAction;

public class CommentService implements CommonAction{
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//if(req.getMethod().equals("POST")) { �� �ȸ����� �̷��� ���� ���� ��Ʈ�� Ȯ��..
		String method = req.getMethod();
		
		if(method.equals("POST")) {
			//json������ ajax�� ������:������Ʈ.���Ķ���� �Ȱ���!!
			String parent = req.getParameter("parent");
			String uid = req.getParameter("uid");
			String nick = req.getParameter("nick");
			String content = req.getParameter("content");
			String regip = req.getRemoteAddr();//�ƹ�ư ����̸� ����� ������Ʈ�� �ҷ����°� ���ѵ�!_!
			
			Connection conn = DBConfig.getConnection();
			
			CallableStatement call = conn.prepareCall(SQL.CALL_COMM);//���ν���=CALL
			call.setString(1, parent);
			call.setString(2, content);
			call.setString(3, uid);
			call.setString(4, regip);
			call.execute();
			
			call.close();
			conn.close();
			
			//������ ������ ���ϱ�
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			String date = sdf.format(new Date());
			
			JSONObject json = new JSONObject();
			json.put("nick", nick);
			json.put("date", date);
			json.put("content", content);
			
			return json.toString();
		} else {
			return null;
		}
	}
}
