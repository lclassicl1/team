<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
게시글 수정 
<form action="modify.do?no=${review.article.articleNo }" method="post">
	<p>
	번호 : <br>${review.article.articleNo }
	</p>
	<p>
	제목 : 
	<input type="text" name = "title" id="title" value="${review.article.articleTitle }">
	<c:if test="${errors.title }">제목을 입력해주세요.</c:if>
	</p>
	<p>
	내용 : 
	<textarea name ="content" id="content" rows="5" cols="30" >${review.article.articleContent }</textarea>
	<c:if test="${errors.content }">내용을 입력해주세요.</c:if>
	</p>
	<p>
	카테고리 : 
	<select name="category" >
				<option value="리뷰">리뷰</option>
				<option value="후기">후기</option>
			</select>
	</p>
	<input type="submit" value="수정">
	<input type="reset" value="취소">
</form>
</body>
</html>