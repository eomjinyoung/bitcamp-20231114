<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<h1>과제</h1>

<form action='/app/assignment/update' method='post'>
<div>
  번호: <input readonly name='no' type='text' value='${assignment.no}'>
</div>
<div>
  과제명: <input name='title' type='text' value='${assignment.title}'>
</div>
<div>
  내용: <textarea name='content'>${assignment.content}</textarea>
</div>
<div>
  제출마감일: <input name='deadline' type='date' value='${assignment.deadline}'>
</div>
<div>
  <button>변경</button>
  <a href='/app/assignment/delete?no=${assignment.no}'>[삭제]</a>
</div>
</form>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>