<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<h1>${boardName}</h1>

<form action='/app/board/add?category=${category}' method='post' enctype='multipart/form-data'>
  <input name='category' type='hidden' value='${category}'>
  <div>
        제목: <input name='title' type='text'>
  </div>
  <div>
        내용: <textarea name='content'></textarea>
  </div>
<c:if test="${category == 1}">
  <div>
        첨부파일: <input multiple name='attachedFiles' type='file'>
  </div>
</c:if>

<div>
  <button>등록</button>
</div>
</form>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>