<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css'/>
  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js'/>
  <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/default.css'/>
<title>비밀번호 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
	
/* 백그라운드 배경색 및 기본 body 속성들 */
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  display: flex;
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
}

/* 각각 태그들에 대한 속성 */
form {
  background-color: #333;
  box-shadow: 0 0 10px #ddd;
  padding: 20px;
  border-radius: 10px;
  width: 400px;
  margin: auto;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

label,
input,
button {
  display: block;
  width: 100%;
  margin-bottom: 20px;
}

label {
  font-weight: bold;
}

input,
button {
  padding: 10px;
  border-radius: 5px;
  border: none;
  font-size: 16px;
}
/* 버튼 디자인 */
button {
  background-color: #333;
  color: #fff;
  cursor: pointer;
}
/* 버튼 마우스오버에 대한 속성 */
button:hover {
  background-color: deepskyblue;
}

.links {
  text-align: center;
  margin-top: 20px;
}

.links a {
  color: cornflowerblue;
  font-size: 14px;
  margin-right: 10px;
  text-decoration: none;
}
	
</style>
</head>
<body>
${errors }
<hr>
<%-- <a href="<%=request.getContextPath()%>/index.jsp" target="_blank">HOME</a> --%>
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
		<button type="submit" value="확인">확인</button>
		<button type="reset" value="취소">취소</button>
	</form>
</body>
</html>