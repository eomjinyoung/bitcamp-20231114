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

<h1>회원</h1>
<form action='/app/member/update' method='post' enctype='multipart/form-data'>
<div>
    사진:
    <c:if test="${not empty member.photo}">
      <a href='/upload/${member.photo}'> <img src='/upload/${member.photo}' height='80px'></a><br>
    </c:if>
    <c:if test="${empty member.photo}">
      <a href='/img/default-photo.jpeg'> <img src='/img/default-photo.jpeg' height='80px'></a><br>
    </c:if>
      <input name='file' type='file'>
</div>
<div>
    번호: <input readonly name='no' type='text' value='${member.no}'>
</div>
<div>
    이메일: <input name='email' type='text' value='${member.email}'>
</div>
<div>
    이름: <input name='name' type='text' value='${member.name}'>
</div>
<div>
  암호: <input name='password' type='password'>
</div>
<div>
  가입일: <input readonly type='text' value='${member.createdDate}'>
</div>
<div>
  <button>변경</button>
  <a href='/app/member/delete?no=${member.no}'>[삭제]</a>
</div>
</form>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>