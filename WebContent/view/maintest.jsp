<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
  	<!-- google cdn 방식 jquery-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <meta charset="UTF-8">
  <meta name="description" content="memberboard web app">
  <meta name="keywords" content="article, javascript, board, webProject">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>고수</title>
	<style>
	</style>
	<script>
		$(function(){
			
		});
	</script>
</head>
<body>
  <!-- HEADER -->
  <header>
    <div class="inner">
      <a href="/" class="logo">
        <img src="./images/starbucks_logo.png" alt="STARBUCKS" />
      </a>
      <div class="sub-menu">
        <ul class="menu">
          <li>
            <a href="/signin">마이페이지</a>
          </li>
          <li>
            <a href="javascript:void(0)">로그아웃</a>
          </li>
        </ul>
      </div>
      <ul class="main-menu">
        <li class="item">
          <div class="item__name">공지사항</div>
        </li>
        <li class="item">
          <div class="item__name">자유게시판</div>
        </li>
        <li class="item">
          <div class="item__name">Gosu</div>
          <div class='item__content'>
          	<div class='content__menu'>
          		<ul class='inner'>
          			<li>고수님해주세요</li>
          			<li>고수가해줄게요</li>
          		</ul>
          	</div>
          </div>
        </li>
        <li class="item">
          <div class="item__name">중고거래게시판</div>
        </li>
        <li class="item">
          <div class="item__name">리뷰&후기게시판</div>
        </li>
      </ul>
    </div>
    </header>
</body>
</html>