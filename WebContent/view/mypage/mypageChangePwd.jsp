<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<a href="<%=request.getContextPath()%>/mypage.do"><button>뒤로가기</button></a>
	<form action="/mypageChangePwd.do" method="post">
	<table border="1" class="table table-dark table-hover">
	<caption>비밀번호 변경</caption>
		<tr>
			<th>현재 비밀번호</th>
			<td>
				<input type="password" name="nowPwd">
				<c:if test="${errors.nowPwd}">현재 비밀번호를 입력하세요.</c:if>
				<c:if test="${errors.pwdMatchFail}">현재 비밀번호와 일치하지 않습니다. </c:if>
			</td>
		</tr>
		<tr>
			<th>변경 비밀번호</th>
			<td>
				<input type="password" name="newPwd">
				<c:if test="${errors.newPwd}">변경할 비밀번호를 입력하세요.</c:if>
			</td>
		</tr>
		<tr>
			<th>변경 비밀번호 확인</th>
			<td>
				<input type="password" name="newRePwd">
				<c:if test="${errors.newPwd }">변경할 비밀번호를 입력하세요.</c:if>
				<c:if test="${errors.newPwdRePwdMatch}">변경 비밀번호와 일치하지 않습니다. </c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button type="submit" class="btn btn-secondary">확인</button></td>
		</tr>
	</table>
	</form>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>