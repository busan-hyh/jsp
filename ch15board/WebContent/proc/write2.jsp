<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");
	
	String path = request.getServletContext().getRealPath("/data");//저장경로 상대경로 지정방법 
	int maxSize = 1024 * 1024 * 10; //10MB 

	MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	
	String subject = mr.getParameter("subject");//request로 겟파라미터 안하고 mr로 해야함
	String content = mr.getParameter("content");
	String uid = member.getUid();
	String fileName = mr.getFilesystemName("file");//write.jsp input의 name="file"
	String regip = request.getRemoteAddr();
	
	Connection conn = DBConfig.getConnection();
	//conn.setAutoCommit(false);//트랜젝션 시작
	//파일 상태변수를 넣어 SQL에 적용하기
	int file = 0;
	
	if (fileName != null) {
		file = 1;
		//파일명 UUID값으로 변경, write.jsp와 해당파일을 stream연결을 해야 가공이 가능하다.
		int idx = fileName.lastIndexOf(".");//뒤에서 젤 먼저 나오는 .의 인덱스값
		String ext = fileName.substring(idx);//해당 인덱스값부터 추출(확장자)
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddddHHmmss_");
		String now = sdf.format(date);
		
		String newFilename = now+uid+ext;
		
		File oldFile = new File(path+"/"+fileName);//파일객체 만들기. 실제파일 지정
		File newFile = new File(path+"/"+newFilename);//가상의 파일 지정
		
		/*
		byte[] buf = new byte[1024];//1kb
		FileInputStream fis = new FileInputStream(oldFile);//실제파일을 jsp로 인풋 설정
		FileOutputStream fos = new FileOutputStream(newFile);//jsp에서 가상파일로 아웃풋 설정.
		
		int read = 0;
		while(true){//복사-붙여넣기 하면서 이름을 바꾸기
			read = fis.read();//파일 하나에서 데이터를 쭉쭉쭉 뽑아내는 과정임
			
			if(read == -1) {
				break; //더이상 가져올 바이너리 데이터가 없을경우 -1이 되기때문에 끝나는거임
			}
			fos.write(buf, 0, read);
		}
		fis.close();
		fos.close();
		*/ //라는걸 메서드로 하는 방법▼
		oldFile.renameTo(newFile);
		
		oldFile.delete();//기존파일 삭제
		//파일이 있으면 DB에 이름테이블을 INSERT
		
		PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_BOARD);
		psmt.setString(1, subject);
		psmt.setString(2, content);
		psmt.setInt(3, file);
		psmt.setString(4, uid);
		psmt.setString(5, regip);
		psmt.executeUpdate();
		
		//글쓰기가 실행되어야 seq가 만들어짐. MAX(seq)를 통해 
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL.SELECT_MAXS);//마지막글, 즉 현재글 번호 가져오기
		int seq = 0;

		if (rs.next()) {
			seq = rs.getInt(1);
		}
		//System.out.println("seq = "+seq);이클립스 콘솔로그 확인
		PreparedStatement psmtF = conn.prepareStatement(SQL.INSERT_FILE);
		psmtF.setInt(1, seq);
		psmtF.setString(2, fileName);
		psmtF.setString(3, newFilename);
		psmtF.executeUpdate();
		
		psmt.close();
		//psmtF.close();
		conn.close();
	} else {
		PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_BOARD);
		psmt.setString(1, subject);
		psmt.setString(2, content);
		psmt.setInt(3, file);
		psmt.setString(4, uid);
		psmt.setString(5, regip);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	}
	
	
	response.sendRedirect("../list.jsp");
%>