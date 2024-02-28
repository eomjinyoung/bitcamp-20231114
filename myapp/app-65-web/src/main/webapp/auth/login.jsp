<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.Member"%>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<h1>로그인</h1>
<%
      Member member = (Member) session.getAttribute("loginUser");
      if (member != null) {%>
        <p><%=member.getName()%> 님 환영합니다.</p>
<%
        response.setHeader("Refresh", "1;url=/index.html");
      } else {%>
        <p>이메일 또는 암호가 맞지 않습니다.</p>
<%
        response.setHeader("Refresh", "1;url=/auth/login");
      }%>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>