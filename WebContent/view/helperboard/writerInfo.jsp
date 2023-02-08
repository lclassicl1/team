<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/main.css'/>
<title>Insert title here</title>
<style>
#close {
	margin:10px;
}

body, table {
	text-align: center;
	margin: auto;
}

#table {
	margin-top: 30px;
}
</style>
<script>
	$(function(){
		$('#close').click(function(){
			window.close(this);
		});
	});

</script>
</head>
<body>
<table border="1" class="table table-dark w-auto table-hover" id='table'>
	<tr>
		<td>이름</td>
		<td>${user.userName }</td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td>${user.userHp }</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${user.userGender }</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>${user.userAddress }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${user.userEmail }</td>
	</tr>
	<tr>
		<td>자격증</td>
		<td>${user.userSkill }</td>
	</tr>
	<tr>
		<td>최종학력</td>
		<td>${user.userSchool }</td>
	</tr>
</table>
<button type="button" class="btn btn-secondary" id="close">닫기</button>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>