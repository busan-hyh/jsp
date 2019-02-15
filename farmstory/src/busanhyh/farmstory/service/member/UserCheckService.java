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
			count = rs.getInt(1);//rs의 첫번째 컬럼만 가져옴
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
		JSONObject json = new JSONObject();
		json.put("result", count);
		
		String result = json.toString();
		
		//System.out.println(result);
		//JSON을 리턴하는 클래스임. MainController로~!
		return result;
	}
	
}
