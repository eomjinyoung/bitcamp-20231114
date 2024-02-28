<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<h1>오류!</h1>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {%>
      <p><%=message%></p>
<%  }
    Throwable exception = (Throwable) request.getAttribute("exception");
    if (exception != null) {%>
      <pre>
<%
      out.flush();
      exception.printStackTrace(new PrintWriter(out));
%>
      </pre>
<%  }%>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>