<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.vo.Assignment"%>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<h1>과제</h1>

<a href='/assignment/add'>새 과제</a>
<table border='1'>
  <thead>
    <tr> <th>번호</th> <th>과제</th> <th>제출마감일</th> </tr>
  </thead>
  <tbody>
<%
List<Assignment> list = (List<Assignment>) request.getAttribute("list");
for (Assignment assignment : list) {%>
    <tr>
      <td><%=assignment.getNo()%></td>
      <td><a href='/assignment/view?no=<%=assignment.getNo()%>'><%=assignment.getTitle()%></a></td>
      <td><%=assignment.getDeadline()%></td>
    </tr>
<%}%>
  </tbody>
</table>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>