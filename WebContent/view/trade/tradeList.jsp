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
	<title>트레이드 게시판</title>
	<style></style>
	<script>
	</script>
</head>
<body>
	<h1>트레이드 게시판입니다.</h1>
	세션값:${tradePage}
	재목:${articles.trade_title}
	
	<table border='1'>
		<tr>
			<td colspan="5" style='text-align: center;'><a href="<%=request.getContextPath()%>/helper/tradeWrite.do">게시글 쓰기</a></td>
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일자</td>
			<td>조회수</td>
		</tr>
		<c:if test="${tradePage.hasNoArticles()}">
			<tr>
				<td colspan="5">작성된 게시글이 없습니다</td>
			</tr>		
		</c:if>	
		<c:forEach var='articles' items='${tradePage.content}'>
			<tr>
				<td>${articles.trade_no}</td>
				<td>${articles.trade_title}</td>
				<td>${articles.user_name}</td>
				<td>${articles.trade_credate}</td>
				<td>${articles.trade_readcnt}</td>
			</tr>	
		</c:forEach>
		<c:if test="${tradePage.hasArticles()}">
			<tr>
				<td colspan='5'>
					<c:if test="${tradePage.startPage > 5}">
						<a href='<%=request.getContextPath()%>/helper/trade.do?pageNo=${tradePage.startPage - 5}'>[이전]</a>
					</c:if>
					<c:forEach var='pNo' begin="${tradePage.startPage}" end='${tradePage.endPage}'>
						<a href='<%=request.getContextPath()%>/helper/trade.do?pageNo=${pNo}'>[${pNo}]</a>
					</c:forEach>
					<c:if test="${articles.endPage < articles.totalPage}">
						<a href='<%=request.getContextPath()%>/helper/trade.do?pageNo=${tradePage.startPage+5}'>[다음]</a>
					</c:if>
				</td>
			</tr>		
		</c:if>
	</table>
</body>
</html>