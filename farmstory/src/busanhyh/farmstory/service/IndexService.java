package busanhyh.farmstory.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;
import busanhyh.farmstory.vo.BoardVO;

public class IndexService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// index.jsp는 시작페이지라서 ~~/farmstory 로 실행할 경우 컨트롤러를 안거치고 jsp가 바로 실행된다.
		// commandURI.properties에 /=인덱스 서비스를 저장해야 컨트롤러를 거친다.
		
		// 최신글 가져오기 3개는 getLatest클래스 생성(반복이니까)
		
		// 리스트로 심기
		req.setAttribute("latest1", getLatest("farm"));
		req.setAttribute("latest2", getLatest("school"));
		req.setAttribute("latest3", getLatest("story"));
		
		return "/index.jsp";
	}
	
	private ArrayList<BoardVO> getLatest(String cate) throws Exception{
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LIST_INDEX);
		psmt.setString(1, cate);

		ResultSet rs = psmt.executeQuery();
		
		ArrayList<BoardVO> list = new ArrayList<>();
		while(rs.next()){
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt("seq"));
			vo.setTitle(rs.getString("title"));
			vo.setRdate(rs.getString("rdate"));
			
			list.add(vo);

			}
		rs.close();
		psmt.close();
		conn.close();
		
		return list;
	}
}
