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
<form action="modify.do?no=${helper.articleNo }" method="post">
	<p>
	번호 : <br>${helper.articleNo }
	</p>
	<p>
	제목 : 
	<input type="text" name = "title" id="title" value="${helper.helperTitle }">
	<c:if test="${errors.title }">제목을 입력해주세요.</c:if>
	</p>
	<p>
	내용 : 
	<textarea name ="content" id="content" rows="5" cols="30" >${helper.helperContent }</textarea>
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
</body>
</html>