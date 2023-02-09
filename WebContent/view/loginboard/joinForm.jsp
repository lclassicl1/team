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
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
	.c1{width:150px;}
	#memberFrm td, #memberFrm th{
		border: 1px solid white;
		
		padding:5px;
	}
	#memberFrm th{
	text-align:center;
	}
	
/* 백그라운드 배경색 및 기본 body 속성들 */
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  /* display: flex; */
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
}
label {
	color:rgb(243 244 246);
}

/* 각각 태그들에 대한 속성 */
form {
  background-color: #333;
  box-shadow: 0 0 10px #ddd;
  padding: 20px;
  border-radius: 10px;
  width: 540px;
  margin:auto;
  margin-top:100px;
  
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
.table {
 	width: 500px;
 	vertical-align:middle;
 	color: rgb(243 244 246);
}
input{
	margin:auto;
}
.error{
	color:red;
}
.textClass{
	height: 30px;
}
</style>
<script>
 $(document).ready(function(){
	 
	 $("#email_dd").change(function(){
	 	let val = $("select#email_dd option:selected").val();
	 	$("#emailAddress").val(val);
	 });
	 	
	 }); //jQuery끝
</script>
</head>
<body>
<h1 style="text-align: center; margin-top:300px; margin-bottom:-30px;">GOSU 회원가입</h1>
	<form name = "memberFrm" id="memberFrm" method="post"  action="<%=request.getContextPath()%>/join.do">
		<table class="table">
			<tbody>
				<tr>
					<th colspan="2" class="c1">
						아이디 
					</th>
					<td colspan="2">
						<input type = "text" name="userId" autofocus class="textClass">
						<div class="error"><c:if test="${errors.userId}">* ID를 입력하세요.  </c:if></div>
						<div class="error"><c:if test="${errors.duplicateId}">* 이미 사용중인 ID입니다. </c:if></div>
	 				</td>
				</tr>
				<tr>
					<th colspan="2" class="c1">
						비밀번호
					</th>
					<td colspan="2">
						<input type = "password" name="userPwd" class="textClass">
					<div class="error">	<c:if test="${errors.userPwd}">* 비밀번호를 입력하세요. </c:if></div>
					</td>
				</tr>
				<tr>
					<th colspan="2" class="c1">
						비밀번호 확인
					</th>
					<td colspan="2">
						<input type = "password"name="userRePwd" class="textClass">
					<div class="error">	<c:if test="${errors.userRePwd}">* 비밀번호확인을 입력하세요. </c:if></div>
					<div class="error">	<c:if test="${errors.notMatch}">* 비밀번호와 비밀번호확인이 일치하지 않습니다.</c:if></div>
						
					</td>
				</tr>
				<tr>
					<th class="c1">
						이름
					</th>
					<td>
						<input type = "text"name="userName" class="textClass">
						<div class="error"><c:if test="${errors.userName}">* 이름을 입력하세요.</c:if></div>
					</td>
					<th class="c1">
						생년월일
					</th>
					<td>
						<input type ="date" name="userBirth"  class="textClass">
						<div class="error"><c:if test="${errors.userBirth}">* 생년월일을 입력하세요.</c:if></div>
					</td>
				</tr>
				<tr>
					<th class="c1">
						전화번호
					</th>
					<td>
						<input type = "text"name="userHp" class="textClass">
					<div class="error">	<c:if test="${errors.userHp}">* 전화번호를 입력하세요.</c:if></div>
					</td>
					<th class="c1">
						성별
					</th>
					<td style="text-align: center;">
						<select name="userGender" style="width: 100px;">
							<option value="없음">선택</option>
							<option value="M">남자</option>
							<option value="F">여자</option>
						</select><br>
					<div class="error">	<c:if test="${errors.userGender}">* 성별을 선택해주세요.</c:if></div>
					</td>
				</tr>
				<tr>
					<th colspan="2" class="c1">
						주소
					</th>
					<td colspan="2">
						<input type = "text"name="userAddress" class="textClass">
						<div class="error"><c:if test="${errors.userAddress}">* 주소를 입력하세요.</c:if></div>
					</td>
				</tr>
				<tr>
					<th colspan="1" class="c1">
						이메일
					</th>
					<td colspan="3">
						<input type ="text" name="emailId" required="required" style="width:130px; float:left; margin-right:15px;" class="textClass"> 
						<b style="float: left;margin-right:15px;">@</b>
						<input type ="text" name="emailAddress" id="emailAddress" required="required" style="width:110px; float:left;margin-right:5px;" class="textClass">
						<select name="email_dd" id="email_dd"  onchange="selectEmail(this);" style="float:left; bottom: 20px;">
							<option value="">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.com">daum.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="nate.com">nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="c1">
						자격증
					</th>
					<td>
						<select name="userSkill">
							<option value=null>선택</option>
							<option value="정보처리산업기사">정보처리산업기사</option>
							<option value="정보처리기사">정보처리기사</option>
							<option value="OCJP">OCJP</option>
							<option value="OCJD">OCJD</option>
						</select>
					</td>
					<th class="c1">
						최종학력
					</th>
					<td>
						<select name="userSchool">
						    <option value=null>선택</option>
							<option value="고등학교졸업">고등학교졸업</option>
							<option value="전문대졸업">전문대졸업</option>
							<option value="4년대학졸업">4년대학졸업</option>
							<option value="학사">학사</option>
							<option value="박사">박사</option>
						</select> 
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align:center;">
					 <input type ="checkbox" name="agree" id="agree">
						회원가입 동의<br>		  
					<div class="error">	<c:if test="${errors.agree}">* 회원가입 동의를 체크해주세요.</c:if></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center; border: 1px solid #333;">
						<input type ="submit" value="확인" class="btn btn-secondary">
					</td>
					<td colspan="2" style="text-align:center; border: 1px solid #333;">
						<input type ="reset" value="취소" class="btn btn-secondary" >
					</td>
				</tr>
					
			</tbody>
		</table>
	</form>
		
</body>
</html>