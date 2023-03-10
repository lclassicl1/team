<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style>
.center {
	text-align: left;
	margin: 10px;
	
}

.input {
	text-align: right;
	margin-top: -10px;
}
.center {
	text-align: left;
	margin: 10px;
	margin-bottom:79px;
	
}

</style>
<title>Insert title here</title>
</head>
<body>
	<header><jsp:include page="../../module/navBar.jsp"/></header>
	<div class="center">
		<h3>공지사항</h3>
	</div>	
	<form action="<%=request.getContextPath() %>/notice/write.do" method="post">
		<table border="1" class="table table-dark table-hover">
			<tr>
				<td>제목</td>
				<td> <input type="text" name = "title" id="title"><c:if test="${errors.titleEmpty}">제목을 입력하세요 </c:if></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><c:if test="${!empty authUser }">${authUser.userId }</c:if></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="5" cols="30" name="content"></textarea><c:if test="${errors.contentEmpty}">내용을 입력하세요 </c:if></td>
			</tr>
		</table>
				<div class="input"><input type="submit" value="등록" class="btn btn-secondary"></div>
		
	</form>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>