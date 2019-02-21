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
		// index.jsp�� ������������ ~~/farmstory �� ������ ��� ��Ʈ�ѷ��� �Ȱ�ġ�� jsp�� �ٷ� ����ȴ�.
		// commandURI.properties�� /=�ε��� ���񽺸� �����ؾ� ��Ʈ�ѷ��� ��ģ��.
		
		// �ֽű� �������� 3���� getLatestŬ���� ����(�ݺ��̴ϱ�)
		
		// ����Ʈ�� �ɱ�
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
