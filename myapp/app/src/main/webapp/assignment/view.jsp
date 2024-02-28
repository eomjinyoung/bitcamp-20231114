<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
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
<%
  Assignment assignment = (Assignment) request.getAttribute("assignment");
%>
<form action='/assignment/update' method='post'>
<div>
  번호: <input readonly name='no' type='text' value='<%=assignment.getNo()%>'>
</div>
<div>
  과제명: <input name='title' type='text' value='<%=assignment.getTitle()%>'>
</div>
<div>
  내용: <textarea name='content'><%=assignment.getContent()%></textarea>
</div>
<div>
  제출마감일: <input name='deadline' type='date' value='<%=assignment.getDeadline()%>'>
</div>
<div>
  <button>변경</button>
  <a href='/assignment/delete?no=<%=assignment.getNo()%>'>[삭제]</a>
</div>
</form>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>