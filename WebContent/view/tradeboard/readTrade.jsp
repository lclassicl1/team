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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<style>
.right {
	text-align: right;
	
}
.center{
	text-align: left;
	margin: 10px;
	margin-bottom:79px;
}
</style>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<div class="center"><h3>트레이드 게시판</h3></div>
<!-- 게시글 정보  -->
<table border="1" class="table table-dark table-hover">
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
		<td>조회수</td>
		<td>${read.article.articleReadCnt }</td>
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
			<a href="<%=request.getContextPath()%>/trade/list.do?pageNo=${pageNo}"><div class="right"><button class="btn btn-secondary btn-sm blank" id="list">목록</button></a>
		<c:if test="${authUser.userNo == read.article.userNo }">
			<a href="<%=request.getContextPath()%>/trade/modify.do?no=${read.article.articleNo }"><button class="btn btn-secondary btn-sm blank">게시글 수정</button></a>
			<a href="<%=request.getContextPath()%>/trade/delete.do?no=${read.article.articleNo }"><button class="btn btn-secondary btn-sm blank">게시글 삭제</button></a></div>
		</c:if>
		<c:if test="${authUser.userGrade == 999 }">
		<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${pageNo}" id='list_admin'>목록(관리자 권한)</a>
		</c:if>
		</td>
	</tr> 
			
</table>
 <!-- 글쓴이 정보  -->
<table border="1" class="table table-dark table-hover"> 
<h3 style="margin:10px;">판매자 정보</h3>
	<tr>
		<td>판매자 전화번호</td>
		<td>${read.user.userHp }</td>
	</tr>
	<tr>
		<td>판매자 성별</td>
		<td>
		  <c:choose> 
			    <c:when test="${read.user.userGender == 'M'}">
			        <a>남자</a>
			    </c:when>
			    <c:when test="${read.user.userGender == 'F'}">
			        <a>여자</a>
			    </c:when>
			    <c:otherwise>
			        <a>없음</a>
			    </c:otherwise>
			</c:choose>		
		</td>
		
	</tr>
	<tr>
		<td>판매자 주소</td>
		<td>${read.user.userAddress }</td>
	</tr>
</table>
<c:if test="${commentTotal.hasComm() }">
<h3 style="margin:10px;">댓글</h3>
 <!-- 댓글 -->
 <table border="1" class="table table-dark table-hover">
	<tr>
		<th></th>
		<th>작성자</th>
		<th>내용</th>
		<th>작성시간</th>
		<th>추천</th>
		<th></th>
	</tr>
	<c:forEach var="item" items="${commentTotal.commentList }">
		<tr>
		<td></td>
		<td>
			${item.userId }
		</td>
		<td>
			${item.commContent }
		</td>
		<td>
			${item.commCreDate }
		</td>
		<td>
			${item.commVolt }
		</td>
		<td>
		<a href="<%=request.getContextPath()%>/trade/comment/volt.do?no=${read.article.articleNo }&commNo=${item.commNo}" class="btn btn-secondary">추천</a>
		<c:if test="${authUser.userId == item.userId}">
			<a href="<%=request.getContextPath()%>/trade/comment/modify.do?commNo=${item.commNo}" class="btn btn-secondary">댓글 수정</a>
			<a href="<%=request.getContextPath()%>/trade/comment/delete.do?commNo=${item.commNo}&no=${read.article.articleNo}" class="btn btn-secondary">댓글 삭제</a>
		</c:if>
		</td>
		</tr>
	</c:forEach>
</table>
</c:if>
<h3 style="margin:10px;">댓글 작성</h3>
	<table border="1" class="table table-dark table-hover">
	<tr>
	<td>
		<h5>작성자 :<c:if test="${!empty authUser }">${authUser.userId }</c:if></h5>
	</td>
	</tr>
	<tr>
	<td>
	<form action="<%=request.getContextPath()%>/trade/comment/write.do?no=${read.article.articleNo }" method="post">
			<input type="text" name="content" style="width: 500px; height:50px;">
			<c:if test="${errors.contentEmpty }">댓글 내용을 작성해주세요.</c:if>
		<input type="submit" value="등록" class="btn btn-secondary">
		</form>
	</td>
	</tr>
	</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>