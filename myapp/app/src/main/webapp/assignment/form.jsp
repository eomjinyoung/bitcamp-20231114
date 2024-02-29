<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<h1>과제 관리 시스템</h1>

<h2>과제</h2>

<form action='/app/assignment/add' method='post'>
  <div>
        과제: <input name='title' type='text'>
  </div>
  <div>
        내용: <textarea name='content'></textarea>
  </div>
  <div>
        제출 마감일: <input name='deadline' type='date'>
  </div>
  <div>
    <button>등록</button>
  </div>
</form>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>