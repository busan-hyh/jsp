package busanhyh.farmstory.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;



public class UserCheckService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String uid = req.getParameter("uid");
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_UID_COUNT);
		psmt.setString(1, uid);
		ResultSet rs = psmt.executeQuery();
		
		int count = 0;
		
		if(rs.next()){
			count = rs.getInt(1);//rs�� ù��° �÷��� ������
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
		JSONObject json = new JSONObject();
		json.put("result", count);
		
		String result = json.toString();
		
		//System.out.println(result);
		//JSON�� �����ϴ� Ŭ������. MainController��~!
		return result;
	}
	
}
