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
<a href="<%=request.getContextPath()%>/mypage.do"><button>뒤로가기</button></a>
	<form action="/mypageChangePwd.do" method="post">
	<table border="1">
	<caption><h2>비밀번호 변경</h2></caption>
		<tr>
			<th>현재 비밀번호</th>
			<td>
				<input type="password" name="nowPwd">
				<c:if test="${errors.nowPwd }">현재 비밀번호를 입력하세요.</c:if>
				<c:if test="${errors.pwdMatchFail }">현재 비밀번호와 일치하지 않습니다. </c:if>
			</td>
		</tr>
		<tr>
			<th>변경 비밀번호</th>
			<td>
				<input type="password" name="newPwd">
				<c:if test="${errors.newPwd }">변경할 비밀번호를 입력하세요.</c:if>
			</td>
		</tr>
		<tr>
			<th>변경 비밀번호 확인</th>
			<td>
				<input type="password" name="newRePwd">
				<c:if test="${errors.newPwd }">변경할 비밀번호를 입력하세요.</c:if>
				<c:if test="${errors.newPwdRePwdMatch }">변경 비밀번호와 일치하지 않습니다. </c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button type="submit" >확인</button></td>
		</tr>
	</table>
	</form>
</body>
</html>