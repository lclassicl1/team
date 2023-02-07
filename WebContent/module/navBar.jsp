<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<head>
<link rel='stylesheet' type='text/css' href='../../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../../css/navigation.css'/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
}
</style>
</head> 
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="<%=request.getContextPath()%>/index.do"><img src='<%=request.getContextPath()%>/image/mainlogo.png' id='mainicon'></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/notice/list.do">공지사항</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/freeboard/list.do">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/help/list.do">고수님해주세요</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/helper/list.do">고수가해줄게요</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/trade/list.do">트레이드게시판</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/review/list.do">리뷰/후기게시판</a>
      </li>
      <li class="nav-item">
      	
      </li> 
      </ul>
      <ul class='navbar-nav'>
      <li class="nav-item">
        <a class="nav-link text-light" href="<%=request.getContextPath()%>/mypage.do">
        	${authUser.userName}님
        </a>
      </li>  
      <li class="nav-item">
        <a class="nav-link text-light" href="<%=request.getContextPath()%>/logout.do">
        	<img src='<%=request.getContextPath()%>/image/logouticon.png' alt='Logout' id='logouticon'/>
        </a>
      </li>  
    </ul>
  </div>  
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>