<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
  	<!-- google cdn 방식 jquery-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <meta charset="UTF-8">
  <meta name="description" content="memberboard web app">
  <meta name="keywords" content="article, javascript, board, webProject">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<style></style>
	<script>
		$(function(){
		});
	</script>
</head>
<body>
<a href="<%=request.getContextPath()%>/view/loginboard/loginView.jsp">HOME</a>
	<h2>마이페이지</h2>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${userInfo.userId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${userInfo.userName}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${userInfo.userHp}</td>
		</tr>
			<tr>
			<th>주소</th>
			<td>${userInfo.userAddress}</td>
		</tr>
			<tr>
			<th>이메일</th>
			<td>${userInfo.userEmail}</td>
		</tr>
			<tr>
			<th>성별</th>
			<td>${userInfo.userGender}</td>
		</tr>
		<tr>
			<th>자격증</th>
			<td>${userInfo.userSkill}</td>
		</tr>
			<tr>
			<th>학력사항</th>
			<td>${userInfo.userSchool}</td>
		</tr>
			<tr>
			<th>생년월일</th>
			<td>${userInfo.userBirth}</td>
		</tr>
	</table>
<a href="<%=request.getContextPath()%>/mypageUpdate.do?userId=${userInfo.userId}"><button>정보 수정하기</button></a>
<a href="<%=request.getContextPath()%>/mypageChangePwd.do"><button>비밀번호 변경</button></a>
<a href="<%=request.getContextPath()%>/mypageArticle.do"><button>내가 쓴 글</button></a>
</body>
</html>











