<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	String newName = request.getParameter("newName");
	String oldName = request.getParameter("oldName");
	String name = new String(oldName.getBytes("UTF-8"), "iso-8859-1");
	
	//다운카운트 업뎃쿼리
	Connection conn = DBConfig.getConnection();
	PreparedStatement psmt = conn.prepareStatement(SQL.UPDATE_DOWN_HIT);
	psmt.setString(1, seq);
	
	psmt.executeUpdate();
	System.out.print(psmt);
	psmt.close();
	conn.close();
	
	//경로
	String path = request.getServletContext().getRealPath("/data");//저장경로 상대경로 지정방법 
	File file = new File(path+"/"+newName);
	
	//~~~response 준비
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+name); //<-name 정보 받기
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
	
	//파일 스트림~~!~~연결, response객체에 심기
	//FileInputStream fis = new FileInputStream(file);	
	//파일을 덤프트럭에 모아서 한방에 보내는 '버퍼'
	//BufferedInputStream bis = new BufferedInputStream(fis);fis를 장착!
	//를 한줄로 바꾸면 ▼
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	
	//OutputStream fos = response.getOutputStream();
	//BufferedOutputStream bos = new BufferedOutputStream(fos);//fos를 버퍼에 장착
	//▼마찬가지로 한줄로 바꾸면
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	byte b[] = new byte[1024];//버퍼용 크기 지정, 1바이트짜리가 1024개 = 1kb
	
	int read = 0;
	while(true){
		read = bis.read(b);//bis에서 b크기 만큼 읽기
		if(read == -1){//읽을게 없어지면
			break;
		}
		bos.write(b, 0, read);//bos를 출력한다.
	}
	
	bos.flush();//남아있는 데이터 처리
	bos.close();
	bis.close();
	
%>