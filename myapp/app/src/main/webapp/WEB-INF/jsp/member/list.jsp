<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<h1>회원</h1>
<a href='/app/member/form'>새 회원</a>
<table border='1'>
    <thead>
    <tr> <th>번호</th> <th>이름</th> <th>이메일</th> <th>가입일</th> </tr>
    </thead>
    <tbody>
<c:forEach items="${list}" var="member">
      <tr>
        <td>${member.no}</td>
        <td>
  <c:choose>
    <c:when test="${not empty member.photo}">
        <img src='/upload/${member.photo}' height='20px'>
    </c:when>
    <c:otherwise>
        <img src='/img/default-photo.jpeg' height='20px'>
    </c:otherwise>
  </c:choose>
        <a href='/app/member/view?no=${member.no}'>${member.name}</a></td>
        <td>${member.email}</td>
        <td><fmt:formatDate value="${member.createdDate}" pattern="yyyy-MM-dd"/></td>
      </tr>
</c:forEach>
    </tbody>
</table>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>