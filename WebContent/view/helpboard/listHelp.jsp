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
<link rel='stylesheet' type='text/css' href='../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../css/navigation.css'/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 <title>게시판목록</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
 body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
}
.links {
  text-align: center;
  margin-top: 20px;
}

.links a {
  color: cornflowerblue;
  font-size: 14px;
  margin-right: 10px;
  text-decoration: none;
}

.center {
	text-align: center;
	margin: auto;
}

.left {
	text-align: left;
}
.blank {
	margin:10px;
}

.btn .btn-secondary .btn-sm .blank {
	display:flex;
}
.center{
	text-align: left;
	margin:10px;
}

.input{
	text-align:right;
	margin-top:-20px;
	
} 
.formClass {
	text-align: center;
}
 </style>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<div class="center"><h3>고수님해주세요</h3></div>
 <%-- <a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
 <a href="<%=request.getContextPath()%>/article/list.do">게시글 보러가기</a>
<a href="<%=request.getContextPath()%>/article/write.do">게시글 작성하기</a>
<a href="<%=request.getContextPath()%>/view/member/loginPage.jsp">MyPage</a> --%>
 <hr/>
 <div class="formClass">
 <form action="<%=request.getContextPath()%>/help/search.do" method="post">
 카테고리 : 
 <select name="category">
 				<option value="">전체</option>
				<option value="java">java</option>
				<option value="javascript">javascript</option>
				<option value="c++">c++</option>
				<option value="database">database</option>
				<option value="python">python</option>
			</select>
검색 :  <input type="text" name="input" >
 <input type="submit" class="btn btn-secondary btn-sm blank" value="검색">
 </form>
 </div>
 <table border="1" class="table table-dark table-hover">
  <thead>
  	<tr>
  	 <th>제목</th>
  	 <th>카테고리</th>
  	 <th>작성자</th>
  	 <th>조회수</th>
  	 <th>작성일</th>
  	</tr>
  </thead>
  <tbody>
   <%-- 게시글이 없는 경우 --%>
   <%-- JSTL if조건문이용하여 출력 --%>
   <c:if test="${helpPage.hasNoHelp() }">
	   <tr>
	  	 <td colspan="5" style="text-align:center;">게시글이 없습니다.</td>
	   </tr>
   </c:if>
   <%-- JSTL forEach반복문이용하여 출력시작 --%>
   <c:forEach var="item" items="${helpPage.helpList }">
		 	 <tr>
		 	  <td>
		 	  <a href="<%=request.getContextPath()%>/help/read.do?no=${item.articleNo}">
		 	  	${item.articleTitle}
		 	  </a>
		 	  </td>
		 	  <td>${item.helpCategory}</td>
		 	  <td>${item.userName}</td>
		 	  <td>${item.articleReadCnt}</td>
		 	  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.articleCredate}"/></td>
		 	 </tr>
 	 </c:forEach> 
   <%-- 반복문이용하여 출력끝 --%>
    
   <%-- paging출력 부분 --%>
     <c:if test="${helpPage.hasHelp() }">
	   <tr>
	  	<td colspan="5" style="text-align:center;">
	  		<c:if test="${helpPage.startPage >5 }">
	  			<a href="<%=request.getContextPath()%>/help/list.do?pageNo=${helpPage.startPage -5 }">pre</a>
	  		</c:if>
	  		 <c:forEach var="pNo" begin="${helpPage.startPage }" end="${helpPage.endPage }">
	  			<a href="<%=request.getContextPath()%>/help/list.do?pageNo=${pNo }">${pNo}</a>
	  		</c:forEach>
	  		<c:if test="${helpPage.endPage < helpPage.totalPage }">
	  			<a href="<%=request.getContextPath()%>/help/list.do?pageNo=${helpPage.endPage+5 }">next</a>
	  		</c:if>
	    </td>
	   </tr>
   </c:if> 
  </tbody>
 </table>
 <div class="input"><a href="<%=request.getContextPath()%>/help/write.do" class="btn btn-secondary blank">게시글 작성하기</a></div>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>


















