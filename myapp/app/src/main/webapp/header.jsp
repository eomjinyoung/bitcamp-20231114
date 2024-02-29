<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <img src='https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png'>
  <a href='/assignment/list'>과제</a>
  <a href='/board/list?category=1'>게시글</a>
  <a href='/member/list'>회원</a>
  <a href='/board/list?category=2'>가입인사</a>

<c:if test="${not empty loginUser}">
  <span>${loginUser.name}</span>
  <a href='/auth/logout'>로그아웃</a>
</c:if>

<c:if test="${empty loginUser}">
  <a href='/auth/login'>로그인</a>
</c:if>

  <a href='/about.html'>소개</a>
</header>
