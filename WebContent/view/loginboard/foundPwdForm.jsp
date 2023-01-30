<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
${errors }
<hr>
<a href="<%=request.getContextPath()%>/index.jsp" target="_blank">HOME</a>
	<form method="post" action="<%=request.getContextPath()%>/foundPwd.do">
		<c:if test="${errors.InputNotMatch }">아이디와 입력한 정보가 일치하지 않습니다.</c:if>
		<p>
			아이디 : <input type="text" name="inputId">
			<c:if test="${errors.UserNot }">없는 아이디입니다.</c:if>
			<c:if test="${errors.inputId}">아이디를 입력하세요.</c:if>
		</p>
		<p>
			이름 : <input type="text" name="inputName">
			<c:if test="${errors.inputName}">이름을 입력하세요.</c:if>
		</p>
		<p>
			전화번호 : <input type="text" name="inputHp">
			<c:if test="${errors.inputHp}">전화번호를 입력하세요.</c:if>
		</p>
		<p>
			생년월일 : <input type="date" name="inputBirth">
			 <c:if test="${errors.inputBirth}">생년월일을 입력하세요.</c:if> 
		</p>
		<input type="submit" value="확인">
		<input type="reset" value="취소">
	</form>
</body>
</html>