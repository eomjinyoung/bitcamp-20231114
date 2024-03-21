<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<h1>로그인</h1>

<form action='/app/auth/login' method='post'>
<div>
    이메일: <input name='email' type='text'
    value='${email}'>
</div>
<div>
    암호: <input name='password' type='password'>
</div>
<button>로그인</button>
<input type='checkbox' name='saveEmail'> 이메일 저장
</form>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>