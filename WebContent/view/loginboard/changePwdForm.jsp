<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/changePwd.do" method="post">
		<p>
			현재 비밀번호 : <input type="password" name="nowPwd">
			<c:if test="${errors.nowPwd }">현재 비밀번호를 입력하세요.</c:if>
			<c:if test="${errors.pwdMatchFail }">현재 비밀번호와 일치하지 않습니다. </c:if>
		</p>
		<p>
			변경 비밀번호 : <input type="password" name="newPwd">
			<c:if test="${errors.newPwd }">변경할 비밀번호를 입력하세요.</c:if>
		</p>
		<p>
			변경 비밀번호 확인 : <input type="password" name="newRePwd">
			<c:if test="${errors.newPwd }">변경할 비밀번호를 입력하세요.</c:if>
			<c:if test="${errors.newPwdRePwdMatch }">변경 비밀번호와 일치하지 않습니다. </c:if>
		</p>
		<input type="submit" value="비밀번호 변경">
	</form>	
</body>
</html>