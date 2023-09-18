<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
	<%@ include file="../default/header.jsp" %>
	<h3>회원가입</h3>
	<form action="${contextPath }/member/register" method="post">
		<input type="text" name="id" placeholder="id"><br>
		<input type="text" name="pw" placeholder="pw"><br>
		<input type="text" readonly id="addr1" name="addr" placeholder="우편번호">
		<button type="button" onclick="daumPost()">우편번호 찾기</button><br>
		<input type="text" readonly id="addr2" name="addr" placeholder="주소"><br>
		<input type="text" name="addr" id="addr3" placeholder="상세주소"><br>
		<input type="submit" value="회원가입"><br>
	</form>
</body>
</html>














