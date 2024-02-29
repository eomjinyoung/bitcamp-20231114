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

<h1>과제</h1>

<a href='/app/assignment/add'>새 과제</a>
<table border='1'>
  <thead>
    <tr> <th>번호</th> <th>과제</th> <th>제출마감일</th> </tr>
  </thead>
  <tbody>

<c:forEach items="${list}" var="assignment">
    <tr>
      <td>${assignment.no}</td>
      <td><a href='/app/assignment/view?no=${assignment.no}'>${assignment.title}</a></td>
      <td>${assignment.deadline}</td>
    </tr>
</c:forEach>

  </tbody>
</table>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>