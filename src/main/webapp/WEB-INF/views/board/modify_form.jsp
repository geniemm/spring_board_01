<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/img_view.js"></script>
</head>
<body>
	   <%@ include file="../default/header.jsp" %>
   <form action="modify" method="post" enctype="multipart/form-data">
   	 <input type="hidden" name="imageFileName" value="${content.imageFileName }">
  	  <b>글번호</b><input type="text" name="writeNo" value="${content.writeNo }" readonly><br>
      <b>제목</b><br>
      <input type="text" name="title" value="${content.title }"><br>
      <b>내용</b><br>
      <textarea rows="5" cols="22" name="content">${content.content }</textarea>
      <br>
      <b>이미지</b><br>
      <img id="preview" alt="이미지 없음" width="100" height="100" src="download?name=${content.imageFileName }"><br>
      <input type="file" name="file" onchange="readURL(this)"><br>
      <hr>
      <input type="submit" value="수정">
      <input type="button" onclick="history.back()" value="이전">
   </form>
</body>
</body>
</html>