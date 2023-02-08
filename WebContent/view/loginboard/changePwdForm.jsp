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
		<input type="submit" value="비밀번호 변경" class="btn btn-secondary"
		>
	</form>	
</body>
</html>