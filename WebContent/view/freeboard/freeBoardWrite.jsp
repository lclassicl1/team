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
.right {
	text-align: right;
}
.center {
	text-align: left;
	margin: 10px;
	margin-bottom:79px;
	
}
</style>
</head>

<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<div class="center"><h3>자유게시판</h3></div>
	<form name="writeFrm" method="post" action="<%=request.getContextPath()%>/freeboard/write.do" onsubmit="return sendit();">
			<table border="1" class="table table-dark table-hover">
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
					</tr>
				<tr>
						<th>제목</th>
						<td><input type="text" name="title" style="width:500px;"/></td>
				</tr>
					<tr>
						<td>작성자</td>
						<td>${userId}</td>
					</tr>
				<tr>
						<th>내용</th>
						<td><input name="content" id="content" style="width:500px;height:200px;"></td>
				</tr>
			</table>
					<div class="right"><input type="submit" value="등록" class="btn btn-secondary"></div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	</form>
</body>
</html>