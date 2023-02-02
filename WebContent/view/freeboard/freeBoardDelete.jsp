<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<script 
src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
</script>
</head>

<body>
<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
<a href="<%=request.getContextPath()%>/freeboard/list.do">글 목록으로</a>


	
	<c:forEach var="board" items="${freeBoard.list}">
<form name="update" id="update" method="post" 
		action="<%=request.getContextPath()%>/freeboard/delete.do?no=${board.articleNo}">
	<table border="1" >
	<tr>
		<th>글 번호</th>
		<td>${board.articleNo}</td>
	</tr>
	<tr>
		<th>삭제할  글 제목</th>
			<td>${board.articleTitle}</td>
	</tr>
	<tr>
		<th>삭제할 글 내용</th>
			<td>${board.articleContent}</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;"><input type="submit" value="삭제하기"/></td>
	</tr>
	</table>
</form>
	</c:forEach>
<hr>
</body>
</html>