<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <%@ page import="auth.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
<table border="1">
	<tr>
		<td>회원 번호</td>
		<td>${user.userNo }</td>
	</tr>
	<tr>
		<td>회원 아이디</td>
		<td>${user.userId }</td>
	</tr>
	<tr>
		<td>회원 이름</td>
		<td>${user.userName}</td>
	</tr>
	<tr>
		<td>회원 전화번호</td>
		<td>${user.userHp }</td>
	</tr>
	<tr>
		<td>회원 가입일</td>
		<td>${user.userRegdate }</td>
	</tr>
	<tr>
		<td>회원 주소</td>
		<td>${user.userAddress }</td>
	</tr>
	<tr>
		<td>회원 등급</td>
		<td>${user.userGrade }</td>
	</tr>
	<tr>
		<td>회원 이메일</td>
		<td>${user.userEmail}</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${user.userGender }</td>
	</tr>
	<tr>
		<td>보유 자격증</td>
		<td>${user.userSkill }</td>
	</tr>
	<tr>
		<td>최종학력</td>
		<td>${user.userSchool }</td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td>${user.userBirth }</td>
	</tr>
	<tr> 
		<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
			<a href="<%=request.getContextPath()%>/master/user/list.do?pageNo=${pageNo}">목록</a>
			<a href="#">회원이 쓴 게시글 보기</a>
			<a href="<%=request.getContextPath()%>/master/user/black.do?no=${user.userNo}">회원 정지</a>
		</td>
	</tr> 
			
</table>

 
</body>
</html>