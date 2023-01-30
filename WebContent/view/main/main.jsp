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
	#nav2>a {
  display: block; /* a태그는 글자성격 = inline */
  float: left;
  font-size: 30px;
  font-weight: 900;
  line-height: 80px;
  padding: 0 30px;
}
#nav2>ul {
  float: right;
}
#nav2>ul li {
  float: left;
  padding: 0 30px;
  line-height: 80px;
}</style>
	<script>
		$(function(){
			
		});
	</script>
</head>
<body>
	<nav id="nav2">
    	<a href="#">logo</a>
    	<ul>
      		<li><a href="#">공지사항</a></li>
      		<li><a href="<%=request.getContextPath()%>/freeboard/list.do">자유게시판</a></li>
      		<li><a href="<%=request.getContextPath()%>/help/list.do">해주세요</a></li>
      		<li><a href="<%=request.getContextPath()%>/helper/list.do">해줄게요</a></li>
      		<li><a href="<%=request.getContextPath()%>/trade/list.do">중고거래게시판</a></li>
      		<li><a href="#">리뷰&후기게시판</a></li>
    	</ul>
  </nav>
</body>
</html>