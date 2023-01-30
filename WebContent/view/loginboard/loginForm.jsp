<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<hr>
<a href="<%=request.getContextPath()%>/index.jsp" target="_blank">HOME</a>
<c:if test="${errors.userNotFound }">회원 정보가 없습니다.</c:if>
	<c:if test="${errors.idPwdNotMatch}">아이디와 암호가 일치하지 않습니다.</c:if>
	<form method="post" action="<%=request.getContextPath()%>/login.do">
		<p>
			아이디 : <input type="text" name="loginId">
			<c:if test="${errors.loginId}">ID를 입력하세요.</c:if>
		</p>
		<p>
			비밀번호 : <input type="password" name="loginPwd">
			<c:if test="${errors.loginPwd}">PASSWORD를 입력하세요.</c:if>
		</p>
		<input type="submit" value="로그인">
		<input type="reset" value="취소">
	</form>
	<a href="<%=request.getContextPath()%>/join.do" target="_blank">회원가입</a>
	<a href="<%=request.getContextPath()%>/view/foundPwdForm.jsp" target="_blank">비밀번호 찾기</a>
</body>
</html>