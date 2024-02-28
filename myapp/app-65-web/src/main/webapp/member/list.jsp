<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.vo.Member"%>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<h1>회원</h1>
<a href='/member/add'>새 회원</a>
<table border='1'>
    <thead>
    <tr> <th>번호</th> <th>이름</th> <th>이메일</th> <th>가입일</th> </tr>
    </thead>
    <tbody>
<%
List<Member> list = (List<Member>) request.getAttribute("list");
for (Member member : list) {%>
      <tr>
        <td><%=member.getNo()%></td>
        <td>
<%    if (member.getPhoto() != null) {%>
        <img src='/upload/<%=member.getPhoto()%>' height='20px'>
<%    } else {%>
        <img src='/img/default-photo.jpeg' height='20px'>
<%    }%>
        <a href='/member/view?no=<%=member.getNo()%>'><%=member.getName()%></a></td>
        <td><%=member.getEmail()%></td>
        <td><%=member.getCreatedDate()%></td>
      </tr>
<%}%>
    </tbody>
</table>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>