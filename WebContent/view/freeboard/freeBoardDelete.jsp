<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/main.css'/>
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/navigation.css'/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>게시판 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
</script>
<style>
.blank {
	margin:10px;
}

</style>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<a href="<%=request.getContextPath()%>/freeboard/read.do?no=${freePage.freeBoardList[0].articleNo}"><button class="table table-dark table-hover blank">뒤로가기</button></a>
	<c:forEach var="item" items="${freePage.freeBoardList}">
			<form name="update" id="update" method="post" 
					action="<%=request.getContextPath()%>/freeboard/delete.do?no=${item.articleNo}">
					<table border="1" class="table table-dark table-hover">
						<tr>
								<th>글 번호</th>
								<td>${item.articleNo}</td>
						</tr>
						<tr>
								<th>삭제할  글 제목</th>
								<td>${item.articleTitle}</td>
						</tr>
						<tr>
								<th>삭제할 글 내용</th>
								<td>${item.articleContent}</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;"><input type="submit" value="삭제하기" class="btn btn-secondary blank"/></td>
						</tr>
					</table>
			</form>
	</c:forEach>
<hr>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>