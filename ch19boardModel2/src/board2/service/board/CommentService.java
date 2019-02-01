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
		
		//if(req.getMethod().equals("POST")) { 가 안먹히면 이렇게 따로 빼서 스트링 확인..
		String method = req.getMethod();
		
		if(method.equals("POST")) {
			//json형태의 ajax를 받을때:리퀘스트.겟파라미터 똑같음!!
			String parent = req.getParameter("parent");
			String uid = req.getParameter("uid");
			String nick = req.getParameter("nick");
			String content = req.getParameter("content");
			String regip = req.getRemoteAddr();//아무튼 기왕이면 히든된 리퀘스트로 불러오는게 편리한듯!_!
			
			Connection conn = DBConfig.getConnection();
			
			CallableStatement call = conn.prepareCall(SQL.CALL_COMM);//프로시저=CALL
			call.setString(1, parent);
			call.setString(2, content);
			call.setString(3, uid);
			call.setString(4, regip);
			call.execute();
			
			call.close();
			conn.close();
			
			//리턴할 데이터 구하기
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
