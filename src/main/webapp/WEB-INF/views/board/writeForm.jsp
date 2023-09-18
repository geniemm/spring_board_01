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
   <form action="writeSave" method="post" enctype="multipart/form-data">
      <b>작성자</b><br>
      <input type="text" name="id" value="${login }" readonly>
      <br>
      <b>제목</b><br>
      <input type="text" name="title"><br>
      <b>내용</b><br>
      <textarea rows="5" cols="22"  name="content"></textarea>
      <br>
      <b>이미지</b><br>
      <input type="file" name="image_file_name" 
                           onchange="readURL(this)">
      <br>
      <img id="preview" width="100" height="100" alt="선택없음">
      <hr>
      <input type="submit" value="등록">
      <a href="boardAllList">목록이동</a>
   </form>
</body>
</html>











