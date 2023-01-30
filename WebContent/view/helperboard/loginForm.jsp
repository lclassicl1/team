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
<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
	<c:if test="${errors.idOrPwNotMatch}">아이디와 암호가 일치하지 않습니다.</c:if>
	<form method="post" action="<%=request.getContextPath()%>/login.do">
		<p>
			아이디 : <input type="text" name="memberid" id="memberid">
			<c:if test="${errors.memberid}">ID를 입력하세요.</c:if>
		</p>
		<p>
			패스워드 : <input type="password" name="password" id="password">
			<c:if test="${errors.password}">PASSWORD를 입력하세요.</c:if>
		</p>
		<input type="submit" value="로그인">
	</form>
</body>
</html>