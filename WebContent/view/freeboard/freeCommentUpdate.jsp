<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="">
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
<title>게시글 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<div class="center"><h3>자유게시판</h3></div>
<h2><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></h2>
<a href="<%=request.getContextPath()%>/freeboard/list.do">글 목록으로</a>
<c:forEach var="item" items="${freePage.freeBoardList}">
	<table border="1" class="table table-dark w-auto table-hover">
			<tr>
					<td>조회수</td>
					<td>${item.articleReadcnt}</td>
			</tr>
				<tr>
					<td>글 작성자</td>
					<td>${item.userName}</td>
			</tr>
				<tr>
					<td>제목</td>
					<td>${item.articleTitle}</td>
			</tr>
				<tr>
					<td>내용</td>
					<td>${item.articleContent}</td>
			</tr>
				<tr>
					<td>카테고리</td>
					<td>${item.freeCategory}</td>
			</tr>
				<tr>
					<td>작성시간</td>
					<td>${item.articleCredate}</td>
			</tr>
			<tr>
				<th colspan="2" style="text-align:center;">
						<a href="<%=request.getContextPath()%>/freeboard/list.do"><button class="btn btn-secondary btn-sm blank">글 목록으로</button></a>
						<a href="<%=request.getContextPath()%>/freeboard/update.do?no=${item.articleNo}"><button class="btn btn-secondary btn-sm blank">글 수정하기</button></a>
						<a href="<%=request.getContextPath()%>/freeboard/delete.do?no=${item.articleNo}"><button class="btn btn-secondary btn-sm blank">글 삭제하기</button></a>
				</th>
			</tr>
	</table>
	</c:forEach>
<hr>
		<table border="1" class="table table-dark w-auto table-hover">
			<tr>
				<th>아이디</th>
				<th>내용</th>
				<th>등록일</th>
				<th>추천수</th>
			</tr>
		<c:forEach var="comment" items="${comment.commentList}">
			<tr>
				<td>${comment.user_id}</td>
				<td style="width:500px;">${comment.comm_content}</td>
				<td>${comment.comm_credate}</td>
				<td>${comment.comm_volt}</td>
				 <c:if test="${authUser.userId==comment.user_id}">
				<td><a href="<%=request.getContextPath()%>/freeboard/commentdelete.do?articleNo=${freePage.freeBoardList[0].articleNo}&commNo=${comment.comm_no}"><button class="btn btn-secondary btn-sm blank">삭제</button></a></td>
				<td><a href="<%=request.getContextPath()%>/freeboard/commentupdate.do?articleNo=${freePage.freeBoardList[0].articleNo}&commNo=${comment.comm_no}"><button class="btn btn-secondary btn-sm blank">수정</button></a></td>
				</c:if>
				<td><a href="<%=request.getContextPath()%>/freeboard/commentlike.do?articleNo=${freePage.freeBoardList[0].articleNo}&commNo=${comment.comm_no}"><button class="btn btn-secondary btn-sm blank">추천</button></a></td>
			</tr>
		</c:forEach>
		</table>
<hr>
	<form method="post"
			action="<%=request.getContextPath()%>/freeboard/commentupdate.do?articleNo=${articleNo}&commNo=${updateList.comm_no}">
		<table border="1" class="table table-dark w-auto table-hover">
				<tr>
						<td>${updateList.user_id}</td>
				</tr>
				<tr>
						<td>
							<input type="text" name="commContent" style="width:300px;height:100px;" value="${updateList.comm_content}">
						</td>
				</tr>
				<tr>
						<td colspan="2" style="text-align:center;"><input type="submit" value="댓글 수정"/></td>
				</tr>
		</table>
	</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>







