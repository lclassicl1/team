<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel='stylesheet' type='text/css' href='../../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../../css/navigation.css'/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 <title>게시판목록(관리자 전용)</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
 .center{
 	text-align: left;
 	margin:10px;
 	margin-bottom: 41px;
 }
 .formClass{
 	text-align: center;
 	margin:5px;
 }
 </style>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<div class="center"><h3>전체게시글 목록(관리자 전용)</h3></div>
<div class="formClass">	
		 <form action="<%=request.getContextPath()%>/master/article/search.do" method="post">
		검색 :  <input type="text" name="input">
		 <input type="submit" class="btn btn-secondary blank" value="검색">
	 </form>
</div>
 <table border="1" class="table table-dark table-hover">
  <thead>
  	<tr>
  	 <th>제목</th>
  	 <th>카테고리</th>
  	 <th>작성자</th>
  	 <th>게시글 상태</th>
  	 <th>조회수</th>
  	 <th>작성일</th>
  	</tr>
  </thead>
  <tbody>
   <%-- 게시글이 없는 경우 --%>
   <%-- JSTL if조건문이용하여 출력 --%>
   <c:if test="${articlePage.hasNoArticle() }">
	   <tr>
	  	 <td colspan="5" style="text-align:center;">게시글이 없습니다.</td>
	   </tr>
   </c:if>
   <%-- JSTL forEach반복문이용하여 출력시작 --%>
   <c:forEach var="item" items="${articlePage.articleList }">
		 	 <tr>
		 	  <td>
		 	  <a href="<%=request.getContextPath()%>/master/article/read.do?no=${item.articleNo}&category=${item.articleCategory}">
		 	  	${item.articleTitle}
		 	  </a></td>
		 	    <td>
		 	   <c:choose> 
				    <c:when test="${item.articleCategory == 'notice'}">
				        <a>공지사항</a>
				    </c:when>
				    <c:when test="${item.articleCategory == 'free'}">
				        <a>자유게시판</a>
				    </c:when>
				    <c:when test="${item.articleCategory == 'help'}">
				        <a>고수님해주세요</a>
				    </c:when>
				    <c:when test="${item.articleCategory == 'helper'}">
				        <a>고수가해줄게요</a>
				    </c:when>
				    <c:when test="${item.articleCategory == 'trade'}">
				        <a>트레이드게시판</a>
				    </c:when>
				    <c:when test="${item.articleCategory == 'review'}">
				        <a>리뷰/후기게시판</a>
				    </c:when>
				    <c:otherwise>
				        <a>없음</a>
				    </c:otherwise>
				</c:choose>
		 	  </td>
		 	  <td>${item.userName }</td>
		 	    <td>
		 	   <c:choose> 
				    <c:when test="${item.isshow == 'Y'}">
				        <a>정상</a>
				    </c:when>
				    <c:when test="${item.isshow == 'N'}">
				        <a>삭제</a>
				    </c:when>
				    <c:otherwise>
				        <a>없음</a>
				    </c:otherwise>
				</c:choose>
		 	  </td>
		 	  <td>${item.articleReadCnt }</td>
		 	   <td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.articleCredate}"/></td>
		 	 </tr>
 	 </c:forEach> 
   <%-- 반복문이용하여 출력끝 --%>
    
   <%-- paging출력 부분 --%>
     <c:if test="${articlePage.hasArticle() }">
	   <tr>
	  	<td colspan="6" style="text-align:center;">
	  		<c:if test="${articlePage.startPage >5 }">
	  			<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${articlePage.startPage -5 }">pre</a>
	  		</c:if>
	  		 <c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
	  			<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${pNo }">${pNo}</a>
	  		</c:forEach>
	  		<c:if test="${articlePage.endPage < articlePage.totalPage }">
	  			<a href="<%=request.getContextPath()%>/master/article/list.do?pageNo=${articlePage.endPage+5 }">next</a>
	  		</c:if>
	    </td>
	   </tr>
   </c:if> 
  </tbody>
 </table>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>


















