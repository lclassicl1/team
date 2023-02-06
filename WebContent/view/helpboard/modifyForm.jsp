<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Insert title here</title>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
게시글 수정 
<form action="modify.do?no=${help.article.articleNo }" method="post">
	<p>
	번호 : <br>${help.article.articleNo }
	</p>
	<p>
	제목 : 
	<input type="text" name = "title" id="title" value="${help.article.articleTitle }">
	<c:if test="${errors.title }">제목을 입력해주세요.</c:if>
	</p>
	<p>
	내용 : 
	<textarea name ="content" id="content" rows="5" cols="30" >${help.article.articleContent }</textarea>
	<c:if test="${errors.content }">내용을 입력해주세요.</c:if>
	</p>
	<p>
	카테고리 : 
	<select name="category" >
				<option value="java">java</option>
				<option value="javascript">javascript</option>
				<option value="c++">c++</option>
				<option value="database">database</option>
				<option value="python">python</option>
			</select>
	</p>
	<input type="submit" value="수정">
	<input type="reset" value="취소">
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>