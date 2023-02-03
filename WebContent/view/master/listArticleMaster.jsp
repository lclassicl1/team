<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>게시판목록(관리자 전용)</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style></style>
</head>
<body>
<h3>전체게시글 목록(관리자 전용))</h3>
 <table border="1">
  <thead>
  	<tr>
  	 <th>제목</th>
  	 <th>카테고리</th>
  	 <th>작성자</th>
  	 <th>노출여부</th>
  	 <th>조회수</th>
  	 <th>작성 시간</th>
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
		 	  <td>${item.articleCategory}</td>
		 	  <td>${item.userName }</td>
		 	  <td>${item.isshow }</td>
		 	  <td>${item.articleReadCnt }</td>
		 	  <td>${item.articleCredate }</td>
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
</body>
</html>


















