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
		
		//1. LIMIT용 int 
		int start = getLimitStart(pg);
		
		//2. 페이지번호 계산(하단에 뜨는 페이지번호)
		int total = getTotal();
		int pgEnd = getPageEnd(total);
		
		//글 카운터 번호 계산(글번호)
		int count = total+1-start;
		
		//3. 페이지 그룹 계산(10개씩 뜨도록)(groupStart와 groupEnd가 둘 다 쓰이지만 return은 1개만 되므로 배열을 써보자!)
		int[] groupStartEnd = getPageGroupStartEnd(pg, pgEnd);
		
		//리스트VO
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LIST);
		psmt.setString(1, cate);//LIMIT용 int 
		psmt.setInt(2, start);//LIMIT용 int 
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
			vo.setNick(rs.getString("nick"));//숫자로 순서를 표시해도 되고 컬럼명을 지정해도 됨
			
			list.add(vo);
			}
		
		rs.close();
		psmt.close();
		conn.close();
		
		//vo를 리퀘스트객체에 담는다.
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("pageEnd", pgEnd);
		req.setAttribute("pageGroupStartEnd", groupStartEnd);
		

		req.setAttribute("cate", cate);
		req.setAttribute("gr", gr);
		
		return "/board/list.jsp";

		}
	
	public int getLimitStart(String pg) {
		//throws Exception은 db연동이 아닐땐 노필요
		int start = 0;
		//pg값을 못받아서 list.jsp?pg=를 띄우면 널포인트에러가 뜨므로 NULL체크를 해야한다. 
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
		psmt.setString(1, cate);//LIMIT용 int 
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
		//ex>[currentPage/10+1] 3pg의 경우 0.33...이지만 int이므로 0이 나옴. 거기+1
		//	 [(int)Math.ceil(currentPage/10.0)] 하지만 10pg는 +1하면 안되므로 3pg를 10.0로 나눠서 0.333이 나올때 올림하면 된당
		int groupStart = (currentPageGroup - 1) * 10 + 1; //각 그룹의 시작번호 계산
		int groupEnd = currentPageGroup * 10; //각 그룹의 끝번호 계산
		if (groupEnd > pgEnd) { //39pg까지 있는데 40이 뜨면 안되므로
			groupEnd = pgEnd;
		}
		groupStartEnd[0] = groupStart;
		groupStartEnd[1] = groupEnd;
		
		
		
		return groupStartEnd;
	
	}
}
