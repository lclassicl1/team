<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
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
</body>
</html>