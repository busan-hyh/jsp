package busanhyh.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import busanhyh.farmstory.config.DBConfig;
import busanhyh.farmstory.config.SQL;
import busanhyh.farmstory.controller.CommonAction;
import busanhyh.farmstory.vo.BoardVO;

public class ListService implements CommonAction {
	String cate = null;
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		
		String pg = req.getParameter("pg");
		
		//1. LIMIT�� int 
		int start = getLimitStart(pg);
		
		//2. ��������ȣ ���(�ϴܿ� �ߴ� ��������ȣ)
		int total = getTotal();
		int pgEnd = getPageEnd(total);
		
		//�� ī���� ��ȣ ���(�۹�ȣ)
		int count = total+1-start;
		
		//3. ������ �׷� ���(10���� �ߵ���)(groupStart�� groupEnd�� �� �� �������� return�� 1���� �ǹǷ� �迭�� �Ẹ��!)
		int[] groupStartEnd = getPageGroupStartEnd(pg, pgEnd);
		
		//����ƮVO
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LIST);
		psmt.setString(1, cate);//LIMIT�� int 
		psmt.setInt(2, start);//LIMIT�� int 
		ResultSet rs = psmt.executeQuery();
		
		ArrayList<BoardVO> list = new ArrayList<>();
		
		while(rs.next()){
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt(1));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setCate(rs.getString(4));
			vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));
			vo.setNick(rs.getString("nick"));//���ڷ� ������ ǥ���ص� �ǰ� �÷����� �����ص� ��
			
			list.add(vo);
			}
		
		rs.close();
		psmt.close();
		conn.close();
		
		//vo�� ������Ʈ��ü�� ��´�.
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("pageEnd", pgEnd);
		req.setAttribute("pageGroupStartEnd", groupStartEnd);
		

		req.setAttribute("cate", cate);
		req.setAttribute("gr", gr);
		
		return "/board/list.jsp";

		}
	
	public int getLimitStart(String pg) {
		//throws Exception�� db������ �ƴҶ� ���ʿ�
		int start = 0;
		//pg���� ���޾Ƽ� list.jsp?pg=�� ���� ������Ʈ������ �߹Ƿ� NULLüũ�� �ؾ��Ѵ�. 
		if(pg == null){
			start = 1;
		}else{
			start = Integer.parseInt(pg);
		}
		return (start - 1) * 10;
	}
	public int getTotal() throws Exception {
		int total = 0;
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_TOTAL);
		psmt.setString(1, cate);//LIMIT�� int 
		ResultSet rs = psmt.executeQuery();

		if(rs.next()) {
			total = rs.getInt(1);
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
		
		return total;
	}
	public int getPageEnd(int total) {
		int pgEnd = 0;
		
		if(total % 10 == 0) {
			pgEnd = total/10;
		} else {
			pgEnd = (total/10) + 1;
		}
		
		return pgEnd;
	}
	public int getPageCountStart(int total, int limit) {
		return total - limit;
	}
	public int[] getPageGroupStartEnd(String pg, int pgEnd) {
		int[] groupStartEnd = new int[2];
		
		int current = 0;
		if(pg == null) {
			current = 1;
		}else {
			current = Integer.parseInt(pg);
		}
		int currentPage = current;
		int currentPageGroup = (int)Math.ceil(currentPage/10.0);
		//ex>[currentPage/10+1] 3pg�� ��� 0.33...������ int�̹Ƿ� 0�� ����. �ű�+1
		//	 [(int)Math.ceil(currentPage/10.0)] ������ 10pg�� +1�ϸ� �ȵǹǷ� 3pg�� 10.0�� ������ 0.333�� ���ö� �ø��ϸ� �ȴ�
		int groupStart = (currentPageGroup - 1) * 10 + 1; //�� �׷��� ���۹�ȣ ���
		int groupEnd = currentPageGroup * 10; //�� �׷��� ����ȣ ���
		if (groupEnd > pgEnd) { //39pg���� �ִµ� 40�� �߸� �ȵǹǷ�
			groupEnd = pgEnd;
		}
		groupStartEnd[0] = groupStart;
		groupStartEnd[1] = groupEnd;
		
		
		
		return groupStartEnd;
	
	}
}
