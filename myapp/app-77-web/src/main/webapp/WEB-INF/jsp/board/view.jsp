<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<h1>${boardName}</h1>
<form action='/app/board/update' method='post' enctype='multipart/form-data'>
  <input name='category' type='hidden' value='${category}'>
  <div>
    번호: <input readonly name='no' type='text' value='${board.no}'>
  </div>
  <div>
    제목: <input name='title' type='text' value='${board.title}'>
  </div>
  <div>
    내용: <textarea name='content'>${board.content}</textarea>
  </div>
  <div>
    작성자: <input readonly type='text' value='${board.writer.name}'>
  </div>

<c:if test="${category == 1}">
    <div>
      첨부파일: <input multiple name='attachedFiles' type='file'>
      <ul>
    <c:forEach items="${files}" var="file">
        <li><a href='/upload/board/${file.filePath}'>${file.filePath}</a>
          [<a href='/app/board/file/delete?category=${category}&no=${file.no}'>삭제</a>]</li>
    </c:forEach>
      </ul>
    </div>
</c:if>

  <div>
    <button>변경</button>
    <a href='/app/board/delete?category=${category}&no=${board.no}'>[삭제]</a>
  </div>
</form>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>