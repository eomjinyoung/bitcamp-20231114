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

<h1>${boardName}</h1>
<a href='/app/board/form?category=${category}'>새 글</a>
<table border='1'>
  <thead>
    <tr> <th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th> <th>첨부파일</th> </tr>
  </thead>
  <tbody>

<c:forEach items="${list}" var="board">
    <tr>
      <td>${board.no}</td>
      <td><a href='/app/board/view?category=${category}&no=${board.no}'>${board.title}</a></td>
      <td>${board.writer.name}</td>
      <td>${board.createdDate}</td>
      <td>${board.fileCount}</td>
    </tr>
</c:forEach>

  </tbody>
</table>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>