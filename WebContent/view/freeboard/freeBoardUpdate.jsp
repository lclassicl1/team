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
<style>
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
	
		<form name="updateFrm" method="post" 
			action="<%=request.getContextPath()%>/freeboard/update.do?no=${freePage.freeBoardList[0].articleNo}" onsubmit="return sendit();">
				<c:forEach var="item" items="${freePage.freeBoardList}">
					<table border="1" class="table table-dark table-hover">
							<tr hidden>
									<th >글 번호</th>
									<td>${freePage.freeBoardList[0].articleNo}</td>
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
							<tr>
									<th>제목</th>
									<td><input type="text" name="title" style="width:500px;" value="${freePage.freeBoardList[0].articleTitle}"/></td>
							</tr>
							<tr>
									<th>내용</th>
									<td><input type="text" name="content" style="width:500px;height:200px;" value="${freePage.freeBoardList[0].articleContent}"></td>
							</tr>
					</table>
							<div class="right">
								<input type="submit" value="등록" class="btn btn-secondary btn-sm blank"/>
								<input type="reset" value="취소" class="btn btn-secondary btn-sm blank">
							</div>
			</c:forEach>
	</form>
<hr>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>