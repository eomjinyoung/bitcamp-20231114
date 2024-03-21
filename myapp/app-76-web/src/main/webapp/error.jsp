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

<h1>오류!</h1>

<c:if test="${not empty message}">
  <p>${message}</p>
</c:if>

<c:if test="${not empty detail}">
  <pre>
  ${detail}
  </pre>
</c:if>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>