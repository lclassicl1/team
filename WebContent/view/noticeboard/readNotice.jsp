<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <%@ page import="auth.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 게시글 정보  -->
<table border="1">
	<tr>
		<td>번호</td>
		<td>${read.article.articleNo }</td>
	</tr>
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
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
			<a href="<%=request.getContextPath()%>/notice/list.do?pageNo=${pageNo}">목록</a>
		<c:if test="${authUser.userNo == read.article.userNo }">
			<a href="<%=request.getContextPath()%>/notice/modify.do?no=${read.article.articleNo }">게시글 수정</a>
			<a href="<%=request.getContextPath()%>/notice/delete.do?no=${read.article.articleNo }">게시글 삭제</a>
		</c:if>
		<c:if test="${authUser.userGrade == 999 }">
		<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${pageNo}">목록(관리자 권한)</a>
		</c:if>
		</td>
	</tr> 
			
</table>

 
</body>
</html>