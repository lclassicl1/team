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
<form action="<%=request.getContextPath()%>/review/comment/modify.do?commNo=${comment.commNo}" method="post">
	<p>
	내용 : 
	<textarea name ="content" id="content" rows="5" cols="30" >${comment.commContent }</textarea>
	<c:if test="${content }">내용을 입력해주세요.</c:if>
	</p>
	<input type="submit" value="수정">
	<input type="reset" value="취소">
</form>
</body>
</html>