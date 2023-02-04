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
		if(document.updateFrm.title.value==""){
			alert("제목을 입력해주세요.");
			document.updateFrm.title.focus();
			return false;
			}
			if(document.updateFrm.content.value==""){
				alert("내용을 입력해주세요.");
				document.updateFrm.content.focus();
				return false;
			}
			if(document.updateFrm.freeCategory.value==""){
				alert("카테고리를 입력해주세요.");
				document.updateFrm.freeCategory.focus();
				return false;
			}
	}
</script>
</head>

<body>
<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
<a href="<%=request.getContextPath()%>/freeboard/list.do">글 목록으로</a>


	<form name="updateFrm" method="post" 
		action="<%=request.getContextPath()%>/freeboard/update.do?no=${freePage.freeBoardList[0].articleNo}" onsubmit="return sendit();">
	<table border="1" >
	<tr>
		<th>글 번호</th>
		<td>${freePage.freeBoardList[0].articleNo}</td>
	</tr>
	<tr>
		<th>수정할 제목</th>
			<td><input type="text" name="title" style="width:500px;"/></td>
	</tr>
	<tr>
		<th>수정할 내용</th>
			<td><input type="text" name="content" style="width:500px;height:200px;" ></td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td>
		   	<select name='freeCategory'>
			    <option value='' selected>-- 선택 --</option>
			    <option value='자유'>자유</option>
			    <option value='질문'>질문</option>
			    <option value='팁'>팁</option>
  			</select>
		</td>
	<tr>
		<td colspan="2" style="text-align:center;"><input type="submit" value="수정하기"/></td>
	</tr>
	</table>
</form>
<hr>
</body>
</html>