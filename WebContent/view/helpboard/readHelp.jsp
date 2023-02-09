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
.check{
	width: 25px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<div class="center"><h3>고수님해주세요</h3></div>
<!-- 게시글 정보  -->
<table border="1" class="table table-dark w-auto table-hover">
	<tr>
		<td>제목</td>
		<td><c:out value="${read.article.articleTitle }"></c:out></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${read.article.userName }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
		${read.article.articleContent }
		</td>
	</tr>
	<tr>
		<td>카테고리</td>
		<td>
		${read.help.helpCategory }
		</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${read.article.articleReadCnt }</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>
		${read.article.articleCredate }
		</td>
	</tr>
	<tr> 
		<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
			<a href="<%=request.getContextPath()%>/help/list.do?pageNo=${pageNo}"><button class="btn btn-secondary btn-sm blank">목록</button></a>
		<c:if test="${authUser.userNo == read.article.userNo }">
			<a href="<%=request.getContextPath()%>/help/modify.do?no=${read.article.articleNo }"><button class="btn btn-secondary btn-sm blank">게시글 수정</button></a>
			<a href="<%=request.getContextPath()%>/help/delete.do?no=${read.article.articleNo }"><button class="btn btn-secondary btn-sm blank">게시글 삭제</button></a>
		</c:if>
		<c:if test="${authUser.userGrade == 999 }">
		<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${pageNo}"><button class="btn btn-secondary btn-sm blank">목록(관리자 권한)</button></a>
		</c:if>
		</td>
	</tr> 
			
</table>
 <!-- 댓글 -->
 <table border="1" class="table table-dark w-auto table-hover">
	<tr>
		<th style="background-color: #1F2937; border: 1px solid rgb(31 41 55);">     </th>
		<th>작성자</th>
		<th>내용</th>
		<th>작성시간</th>
	</tr>
	<c:forEach var="item" items="${commentList }">
		<tr>
		<td style="background-color: rgb(31 41 55); border: 1px solid rgb(31 41 55);">
		  <c:choose> 
			    <c:when test="${item.commConn == 'Y'}">
			        <a><img alt="채택" src="../../image/check.png" class="check"></a>
			    </c:when>
			    <c:otherwise>
			        <a></a>
			    </c:otherwise>
			</c:choose>
		</td>
		<td>
			${item.userId }
		</td>
		<td>
			${item.commContent }
		</td>
		<td>
			${item.commCreDate }
		</td>
		<c:if test="${authUser.userNo == read.article.userNo}">
		<td>
			<a href="<%=request.getContextPath()%>/help/comment/conn.do?commNo=${item.commNo}&no=${read.article.articleNo}"><button class="btn btn-secondary btn-sm blank">채택</button></a>
		</td>
		</c:if>
		
		<c:if test="${authUser.userId == item.userId}">
		<td>
			<a href="<%=request.getContextPath()%>/help/comment/modify.do?commNo=${item.commNo}"><button class="btn btn-secondary btn-sm blank">댓글 수정</button></a>
			<a href="<%=request.getContextPath()%>/help/comment/delete.do?commNo=${item.commNo}&no=${read.article.articleNo}"><button class="btn btn-secondary btn-sm blank">댓글 삭제</button></a>
		</td>
		</c:if>
		</tr>
	</c:forEach>
	<tr>
	<td style="background-color: rgb(31 41 55); border: 1px solid rgb(31 41 55);"></td>
	<td colspan="3">
	<form action="<%=request.getContextPath()%>/help/comment/write.do?no=${read.article.articleNo }" method="post">
		작성자 :<c:if test="${!empty authUser }">${authUser.userId }</c:if><br>
		<p>
			<input type="text" name="content" style="width: 300px; height: 50px;">
		</p>
		<p>
			<c:if test="${errors.contentEmpty }">댓글 내용을 작성해주세요.</c:if>
		</p>
		<input type="submit" value="등록">
	</form>
	</td>
	</tr>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>