<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <%@ page import="auth.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../css/navigation.css'/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.input {
	text-align: right;

}
</style>
<title>Insert title here</title>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<h3>공지사항</h3>
<!-- 게시글 정보  -->
<table border="1" class="table table-dark table-hover">
	<tr>
		<td>조회수</td>
		<td>${read.article.articleReadCnt }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${read.article.userName }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td><c:out value="${read.article.articleTitle }"></c:out></td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
		${read.article.articleContent }
		</td>
	</tr>
	<tr>
		<td>작성시간</td>
		<td>
		${read.article.articleCredate }
		</td>
	</tr>
	<tr> 
		<td colspan="2">
		<div class="input">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
				<a href="<%=request.getContextPath()%>/notice/list.do?pageNo=${pageNo}" class="btn btn-secondary">목록</a>
			<c:if test="${authUser.userNo == read.article.userNo }">
				<a href="<%=request.getContextPath()%>/notice/modify.do?no=${read.article.articleNo }" class="btn btn-secondary">게시글 수정</a>
				<a href="<%=request.getContextPath()%>/notice/delete.do?no=${read.article.articleNo }" class="btn btn-secondary">게시글 삭제</a>
			</c:if>
			<c:if test="${authUser.userGrade == 999 }">
			<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${pageNo}" class="btn btn-secondary">목록(관리자 권한)</a>
			</c:if>
		</div>
		</td>
	</tr> 
			
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
 
</body>
</html>