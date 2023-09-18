<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	member -> login.jsp
	<%@ include file="../default/header.jsp" %>
	${contextPath }
	<form action="${contextPath }/member/logChk" method="post">
		<input type="text" name="id"><br>
		<input type="text" name="pw"><br>
		<input type="submit" value="로그인">
		<a href="${contextPath }/member/register_view">회원가입</a>
		
		<br>
		<input type="checkbox" name="autoLogin">자동로그인
	</form>
</body>
</html>













