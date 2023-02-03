<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<h2><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></h2>
<a href="<%=request.getContextPath()%>/mypageArticle.do">내가 쓴 글 목록으로</a>
<body>


<c:forEach var="item" items="${articlePage.articleList}">
			<p>글번호 : ${item.articleNo}</p>
			<p>글 작성자 : ${item.userName}</p>
			<p>제목: ${item.articleTitle}</p>
			<p>내용: ${item.articleContent}</p>
			<p>카테고리: ${item.articleCategory}</p>
			<p>작성일 : ${item.articleCredate}</p>
			<p>수정일 : ${item.articleUpdate}</p>
<a href="<%=request.getContextPath()%>/mypageArticleUpdate.do?no=${item.articleNo}"><button>글 수정하기</button></a>
<a href="<%=request.getContextPath()%>/mypageArticleDelete.do?no=${item.articleNo}"><button>글 삭제하기</button></a>
	</c:forEach>
<hr>
<hr>

<h3>댓글</h3> 댓글 작성 성공:<c:out value="${commentResult}"></c:out>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>내용</th>
		<th>등록일</th>
	</tr>
<c:forEach var="comment" items="${comment.commentList}">
	<tr>
		<td>${comment.user_id}</td>
		<td>${comment.comm_content}</td>
		<td>${comment.comm_credate}</td>
		 <c:if test="${authUser.userId==comment.user_id}">
		<td><a href="<%=request.getContextPath()%>/freeboard/commentdelete.do?article_no=${articlePage.articleList[0].articleNo}&comm_no=${comment.comm_no}"><button>삭제</button></a></td>
		<td><a href="<%=request.getContextPath()%>/freeboard/commentupdate.do?article_no=${articlePage.articleList[0].articleNo}&comm_no=${comment.comm_no}"><button>수정</button></a></td>
		</c:if>
	</tr>
</c:forEach>
</table>
<hr>
<form method="post"
		action="/freeboard/commentwrite.do">
		
		<input type="text" name="article_no" value="${articlePage.articleList[0].articleNo}" hidden/>

	<table border="1">
	<tr>
		<th>댓글 내용</th>
			<td><textarea name="comm_content" id="comm_content" ></textarea></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;"><input type="submit" value="댓글 입력"/></td>
	</tr>
	</table>
</form>
</body>
</html>