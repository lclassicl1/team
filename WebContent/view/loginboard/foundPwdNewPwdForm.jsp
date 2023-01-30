<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새로운 비밀번호 설정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<hr>
	<form method="post" action="<%=request.getContextPath()%>/foundPwdPwd.do?id=${userId}">
	<c:if test="${errors.pwdNotMatch}">비밀번호와 비밀번호 확인이 일치하지 않습니다.</c:if>
		<p>
			새로운 비밀번호 : <input type="password" name="newPwd">
			<c:if test="${errors.newPwd}">비밀번호를 입력하세요.</c:if>
		</p>
		<p>
			새로운 비밀번호 확인 : <input type="password" name="newRePwd">
			<c:if test="${errors.newRePwd}">비밀번호 확인를 입력하세요.</c:if>
		</p>
		<input type="submit" value="확인">
		<input type="reset" value="취소">
	</form>
</body>
</html>