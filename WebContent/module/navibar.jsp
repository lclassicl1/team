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
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/navigation.css'/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<title></title>
	<style>
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  background-color: rgb(31 41 55);
}

#mainicon {
	width: 100px;
	height: 50px;
}	

#myPageicon, #logouticon {
	width: 30px;
	height: 30px;
}
	</style>
	<script>
		$(function(){
			
		});
	</script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="index.jsp"><img src='<%=request.getContextPath()%>/image/logo.png' id='mainicon'></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">공지사항</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">고수님해주세요</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="#">고수가해줄게요</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="#">트레이드게시판</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="#">리뷰/후기게시판</a>
      </li> 
      <li class="nav-item">
        <a class="nav-link" href="#"><img src='<%=request.getContextPath()%>/image/mypageicon.png' alt='myPage' id='myPageicon'/></a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="#"><img src='<%=request.getContextPath()%>/image/logouticon.png' alt='Logout' id='logouticon'/></a>
      </li>  
    </ul>
  </div>  
</nav>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>