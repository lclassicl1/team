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
<script>
	function userinfo() {
		window.open('<%=request.getContextPath()%>/helper/writerInfo.do?no=${read.user.userNo}',"nw", "width=400,height=500");
	}
</script>
<style>
.check{
	width: 25px;
}
</style>
<title>Insert title here</title>

</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<!-- 게시글 정보  -->

<div class="center"><h3>고수가해줄게요</h3></div>
<table border="1" class="table table-dark table-hover">

	<tr>
		<td>제목</td>
		<td><c:out value="${read.article.articleTitle }"></c:out></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${read.article.userName }&nbsp;<a href="#" class="btn btn-secondary btn-sm" onclick="userinfo();">프로필보기</a>
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
		${read.helper.helperCategory }
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
			<a href="<%=request.getContextPath()%>/helper/list.do?pageNo=${pageNo}"><button class="btn btn-secondary btn-sm blank">목록</button></a>
		<c:if test="${authUser.userNo == read.article.userNo }">
			<a href="<%=request.getContextPath()%>/helper/modify.do?no=${read.article.articleNo }"><button class="btn btn-secondary btn-sm blank">게시글 수정</button></a>
			<a href="<%=request.getContextPath()%>/helper/delete.do?no=${read.article.articleNo }"><button class="btn btn-secondary btn-sm blank">게시글 삭제</button></a>
		</c:if>
		<c:if test="${authUser.userGrade == 999 }">
		<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${pageNo}"><button class="btn btn-secondary btn-sm blank">목록(관리자 권한)</button></a>
		</c:if>
		</td>
	</tr> 
</table>
<h3>댓글</h3>		
 <!-- 댓글 -->
 <table style="border-top:1px solid white;border-bottom:1px solid white" class="table table-dark table-hover">
 
	<tr>
		<th></th>
		<th>작성자</th>
		<th>내용</th>
		<th>작성시간</th>
		<th></th>
	</tr>
	<c:forEach var="item" items="${commentList }">
		<tr>
		<td>
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
			<a href="<%=request.getContextPath()%>/helper/comment/conn.do?commNo=${item.commNo}&no=${read.article.articleNo}"><button class="btn btn-secondary btn-sm blank">채택</button></a>
			<c:if test="${authUser.userId == item.userId}">
				<a href="<%=request.getContextPath()%>/helper/comment/modify.do?commNo=${item.commNo}">댓글 수정</a>
				<a href="<%=request.getContextPath()%>/helper/comment/delete.do?commNo=${item.commNo}&no=${read.article.articleNo}"><button class="btn btn-secondary btn-sm blank">댓글 삭제</button></a>
			</c:if>
		</td>
		</c:if>
		
		</tr>
	</c:forEach>
	</table>
	<h3>댓글 작성</h3>
	<table class="table table-dark table-hover" >
	<tr>
	<td>
		<h5>작성자 :<c:if test="${!empty authUser }">${authUser.userId }</c:if></h5>
	</td>
	</tr>
	<tr>
	<td>
	<form action="<%=request.getContextPath()%>/helper/comment/write.do?no=${read.article.articleNo }" method="post">
			<input type="text" name="content" style="width: 300px; height: 50px;">
			<c:if test="${errors.contentEmpty }">댓글 내용을 작성해주세요.</c:if>
		<input type="submit" value="등록" class="btn btn-secondary">
		</form>
	</td>
	</tr>
	</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>