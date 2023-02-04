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
<script type="text/javascript">
	function sendit(){
		if(document.writeFrm.title.value==""){
			alert("제목을 입력해주세요.");
			document.writeFrm.title.focus();
			return false;
			}
		if(document.writeFrm.content.value==""){
		alert("내용을 입력해주세요.");
		document.writeFrm.content.focus();
		return false;
		}
		if(document.writeFrm.categorySearch.value==""){
			alert("카테고리를 골라주세요.");
			document.writeFrm.categorySearch.focus();
			return false;
			}
	}
</script>
<style>
	content{width:500px; height:200px; 
		resize:none;
	 	resize: vertical;
	}
</style>
</head>

<body>
<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
<a href="<%=request.getContextPath()%>/freeboard/list.do">글 목록으로</a>

	

<form name="writeFrm" method="post" action="<%=request.getContextPath()%>/freeboard/write.do" onsubmit="return sendit();">
	<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" style="width:500px;"/></td>
	</tr>
	<tr>
		<th>내용</th>
			<td><input name="content" id="content" style="width:500px;height:200px;"></td>
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