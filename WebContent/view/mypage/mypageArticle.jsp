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
	<title></title>
	<style></style>
	<script>
		$(function(){
		});
	</script>
</head>
<body>
<a href="<%=request.getContextPath()%>/mypage.do"><button>뒤로가기</button></a>
<hr>
<form name="categorySearch" id="categorySearch" method="get" 
		action="<%=request.getContextPath()%>/freeboard/searchBoard.do">
		  <select name='categorySearch'>
			    <option value='' selected>-- 선택 --</option>
			    <option value='자유'>자유</option>
			    <option value='질문'>질문</option>
			    <option value='팁'>팁</option>
  	</select>
  					<input type="text" name="input"/>
			    <input type="submit" value="검색"/>
 </form>
<hr>
		<table border="1">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>수정일</th>
				<th>조회수</th>
				<th>카테고리</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${freeBoard.list}">
			<tr>
					<td><c:out value="${item.articleNo}"/></td>
					<td><c:out value="${item.userName}"/></td>
					<td><a href="<%=request.getContextPath()%>/freeboard/read.do?no=${item.articleNo}"><c:out value="${item.free_title}"/></a></td>
					<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${item.articleCredate}"/></td>
					<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${item.articleUpdate}"/></td>
					<td><c:out value="${item.articleReadcnt}"/></td>
					<td><c:out value="${item.freeCategory}"/></td>
			</tr>
				</c:forEach>
	</tbody>
</table>
</body>
</html>