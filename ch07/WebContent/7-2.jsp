<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>2.인클루드 액션태그</h3>
	<%@ include file="./inc/head.jsp" %>
	<jsp:include page="./inc/head.jsp" />
	<main>
		<h1>메인 컨텐츠 영역</h1>
	</main>
	<jsp:include page="./inc/footer.jsp" />
</body>
</html>