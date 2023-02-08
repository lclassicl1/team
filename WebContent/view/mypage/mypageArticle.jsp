<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
 	<!-- google cdn 방식 jquery-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <meta charset="UTF-8">
  <meta name="description" content="memberboard web app">
  <meta name="keywords" content="article, javascript, board, webProject">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>  
	<title></title>
	<style></style>
	<script>
		$(function(){
		});
	</script>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<a href="<%=request.getContextPath()%>/mypage.do"><button>뒤로가기</button></a>
<hr>
<form method="get" 
		action="<%=request.getContextPath()%>/mypageArticleSearch.do">
		  <select name='categorySearch'>
			    <option value='' selected>-- 선택 --</option>
			    <option value='notice'>공지사항</option>
			    <option value='free'> 자유게시판</option>
			    <option value='help'>고수님해주세요</option>
			    <option value='helper'>고수가해줄게요</option>
			    <option value='trade'>트레이드게시판</option>
			    <option value='review'>리뷰/후기게시판</option>
  	</select>
  					<input type="text" name="input"/>
			    <input type="submit" value="검색" class="btn btn-secondary"/>
 </form>
<hr>
		<table border="1" class="table table-dark table-hover">
		<thead>
			<tr>
				<th>제목</th>
				<th>게시판 종류</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${articlePage.articleList}">
			<tr>
					<td><a href="<%=request.getContextPath()%>/mypageArticleRead.do?no=${item.articleNo}&category=${item.articleCategory}"><c:out value="${item.articleTitle}"/></a></td>
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
					<td><c:out value="${item.userName}"/></td>
					<td><c:out value="${item.articleReadCnt}"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.articleCredate}"/></td>
			</tr>
		</c:forEach>
		
				 <%-- paging출력 부분 --%>
     <c:if test="${articlePage.hasArticle()}">
		   <tr>
		  		<td colspan="5" style="text-align:center;">
		  	<c:if test="${articlePage.startPage >5 }">
		  			<a href="<%=request.getContextPath()%>/mypageArticle.do?pageNo=${articlePage.startPage -5}">pre</a>
		  	</c:if>
		  		 <c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage}">
		  			<a href="<%=request.getContextPath()%>/mypageArticle.do?pageNo=${pNo }">${pNo}</a>
		  	</c:forEach>
		  	<c:if test="${articlePage.endPage < articlePage.totalPage }">
		  			<a href="<%=request.getContextPath()%>/mypageArticle.do?pageNo=${articlePage.endPage+5 }">next</a>
	  		</c:if>
	    </td>
	   </tr>
   </c:if> 
	</tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>