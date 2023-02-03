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
  <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/default.css'/>
	<title></title>
	<style>
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  display: flex;
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
}

.links {
  text-align: center;
  margin-top: 20px;
}

.links a {
  color: cornflowerblue;
  font-size: 14px;
  margin-right: 10px;
  text-decoration: none;
}
nav {
      background-color: rgb (243 244 246);
      height: 50px;
      display: flex;
      justify-content: flex-end;
      align-items: center;
    }
 nav li {
      list-style: none;
      color: #fff;
      margin-right: 20px;
    }
nav a {
  color: #fff;
  display: block;
  padding: 1em;
  text-decoration: none;
}

nav ul {
	background-color: rgb (243 244 246);
}

div{
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translate(-50%, -50%);
}

</style>
	<script>
		$(function(){
			
		});
	</script>
</head>
<body>
  		<nav>
    		<ul>
		      <li><a href="">홈으로</a></li>
		      <li><a href="#">공지사항</a></li>
		      <li><a href="#">자유게시판</a></li>
		      <li><a href="#">고수님해주세요</a></li>
		      <li><a href="#">고수가해줄게요</a></li>
		      <li><a href="#">트레이드게시판</a></li>
		      <li><a href="#">마이페이지</a></li>
		      <li><a href="#">로그아웃</a></li>
		    </ul>
  		</nav>
 		<div><h1>고수의 세계에 오신걸 환영합니다.</h1></div>
</body>
</html>