<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">

<title>게시판 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel='stylesheet' type='text/css' href='../../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../../css/navigation.css'/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<h2><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></h2>
<hr>
<a href="<%=request.getContextPath()%>/freeboard/write.do"><button>글쓰기</button></a>
<a href="<%=request.getContextPath()%>/freeboard/list.do"><button>글 목록으로</button></a>
<hr/>
<form name="categorySearch" id="categorySearch" method="get" 
		action="<%=request.getContextPath()%>/freeboard/searchBoard.do">
		  <select name="categorySearch">
			    <option value='' selected>-- 선택 --</option>
			    <option value='자유'>자유</option>
			    <option value='질문'>질문</option>
			    <option value='팁'>팁</option>
  	</select>
  		<input type="text" name="input"/>
  		<input type="submit" value="검색"/>
 </form>
<hr>
<table border="1" class="table table-dark table-hover">
<thead>
	<tr>
		<th>글 번호</th>
		<th>제목</th>
	<!-- 	<th>내용</th> -->
		<th>작성일</th>
		<th>수정일</th>
		<th>조회수</th>
		<th>유저명</th>
		<th>카테고리</th>
	</tr>
</thead>
	<tbody>
<c:forEach var="freeBoard" items="${freeBoard.list}">
			<tr>
					<td><c:out value="${freeBoard.articleNo}"/></td>
					<td><a href="<%=request.getContextPath()%>/freeboard/read.do?no=${freeBoard.articleNo}"><c:out value="${freeBoard.articleTitle}"/></a></td>
					<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${freeBoard.articleCredate}"/></td>
					<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${freeBoard.articleUpdate}"/></td>
					<td><c:out value="${freeBoard.articleReadcnt}"/></td>
					<td><c:out value="${freeBoard.userName}"/></td>
					<td><c:out value="${freeBoard.freeCategory}"/></td>
			</tr>
</c:forEach>
	</tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>