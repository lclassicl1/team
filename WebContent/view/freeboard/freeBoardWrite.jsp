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
<style>
	textarea{width:500px; height:200px; 
		resize:none;
	 	resize: vertical;
	}
</style>
</head>

<body>
<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
<a href="<%=request.getContextPath()%>/freeboard/list.do">글 목록으로</a>

	

<form name="write" id="write" method="post" 
		action="<%=request.getContextPath()%>/freeboard/write.do">
	<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" id="title"/></td>
	</tr>
	<tr>
		<th>내용</th>
			<td><textarea name="content" id="content" width:500px; height:200px; style="resize: none;"></textarea></td>
	</tr>
	<tr>
	<tr>
		<th>카테고리</th>
		<td>
		   	<select name='categorySearch'>
			    <option value='' selected>-- 선택 --</option>
			    <option value='자유'>자유</option>
			    <option value='질문'>질문</option>
			    <option value='팁'>팁</option>
  			</select>
		</td>
	<tr>
		<td colspan="2" style="text-align:center;"><input type="submit" value="글쓰기"/></td>
	</tr>
	</table>
</form>
</body>
</html>