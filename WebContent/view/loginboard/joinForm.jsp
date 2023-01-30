<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
	.c1{width:150px;}
	#memberFrm td, #memberFrm th{
		border: 1px solid;
		padding:5px;
	}
	#memberFrm th{
	text-align:right;
	}
	table{border:solid 1px;}
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
${errors }
<hr>

	<form name = "memberFrm" id="memberFrm" method="post"  action="<%=request.getContextPath()%>/join.do">
		<table>
			<caption>회원가입</caption>
			<tbody>
				<tr>
					<th class="c1">
						<label for="memberid">아이디</label><br>
					</th>
					<td>
						<input type = "text" name="userId" autofocus>
						<c:if test="${errors.userId}">ID를 입력하세요.  </c:if>
						<c:if test="${errors.duplicateId}">이미 사용중인 ID입니다. </c:if>
	 				</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="password">비밀번호</label><br>
					</th>
					<td>
						<input type = "password" name="userPwd">
						<c:if test="${errors.userPwd}">비밀번호를 입력하세요. </c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="re_password">비밀번호 확인</label><br>
					</th>
					<td>
						<input type = "password"name="userRePwd">
						<c:if test="${errors.userRePwd}">비밀번호확인을 입력하세요. </c:if>
						<c:if test="${errors.notMatch}">비밀번호와 비밀번호확인이 일치하지 않습니다.</c:if>
						
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="name">이름</label><br>
					</th>
					<td>
						<input type = "text"name="userName">
						<c:if test="${errors.userName}">이름을 입력하세요.</c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="birth">생년월일</label><br>
					</th>
					<td>
						<input type = "date"name="userBirth">
						<c:if test="${errors.userBirth}">생년월일을 입력하세요.</c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="hp">전화번호</label><br>
					</th>
					<td>
						<input type = "text"name="userHp">
						<c:if test="${errors.userHp}">전화번호를 입력하세요.</c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="address">주소</label><br>
					</th>
					<td>
						<input type = "text"name="userAddress">
						<c:if test="${errors.userAddress}">주소를 입력하세요.</c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="gender">성별</label><br>
					</th>
					<td>
						<select name="userGender">
							<option value="없음">선택</option>
							<option value="M">남자</option>
							<option value="F">여자</option>
						</select>
						<c:if test="${errors.userGender}">성별을 선택해주세요.</c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="skill">자격증</label><br>
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
				</tr>
				<tr>
					<th class="c1">
						<label for="school">최종학력</label><br>
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
					<th class="c1">
						<label for="email">이메일</label><br>
					</th>
					<td>
						<input type ="text" name="emailId" required="required"> @
						<input type ="text" name="emailAddress" id="emailAddress" required="required">
						<select name="email_dd" id="email_dd"  onchange="selectEmail(this);">
							<option value="">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.com">daum.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="nate.com">nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
					 <input type ="checkbox" name="agree" id="agree">
					 <label for="agree">약관에 동의</label>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type ="submit" value="확인">
						<input type ="reset" value="취소">
					</td>
				</tr>
					
			</tbody>
		</table>
	</form>
		
</body>
</html>