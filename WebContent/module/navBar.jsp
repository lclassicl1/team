<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="index.jsp"><img src='<%=request.getContextPath()%>/image/mainlogo.png' id='mainicon'></a>
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
      </ul>
      <ul class='navbar-nav'>
      <li class="nav-item">
        <a class="nav-link text-light" href="<%=request.getContextPath()%>/mypage.do">
        	<img src='<%=request.getContextPath()%>/image/mypageicon.png' alt='myPage' id='myPageicon'/>
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