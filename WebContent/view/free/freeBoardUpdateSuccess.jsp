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

<hr>
<hr>
	글수정 완료 :<c:out value="${result}"></c:out>
<hr>
<hr>
</body>
</html>