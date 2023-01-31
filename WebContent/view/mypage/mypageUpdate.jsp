<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<h2>정보 수정</h2>
	<form action="<%=request.getContextPath()%>/mypageUpdate.do?userId=${userInfo.userId}" method="post">
		<table>
			<caption>회원 정보 수정</caption>
			<tbody>
				<tr>
					<th class="c1">
						<label for="memberid">아이디</label><br>
					</th>
					<td>
						<input type = "text" name="userId" value="${userInfo.userId}" style="border:none" disabled>
	 				</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="name">이름</label><br>
					</th>
					<td>
						<input type = "text"name="userName" value="${userInfo.userName}" style="border:none" disabled>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="birth">생년월일</label><br>
					</th>
					<td>
						<input name="userBirth" value="${userInfo.userBirth}" style="border:none" disabled>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="gender">성별</label><br>
					</th>
					<td>
						<input type="text" name="gender" value="${userInfo.userGender}" style="border:none" disabled>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="hp">전화번호</label><br>
					</th>
					<td>
						<input type = "text"name="userHp" value ="${userInfo.userHp }"></input>
						<c:if test="${errors.userHp}">전화번호를 입력하세요.</c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="address">주소</label><br>
					</th>
					<td>
						<input type = "text"name="userAddress" value="${userInfo.userAddress }"></input>
						<c:if test="${errors.userAddress}">주소를 입력하세요.</c:if>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="skill">자격증</label><br>
					</th>
					<td>
						<select name="userSkill">
							<option value=null ${userInfo.userSkill == null ? 'selected="selected"' : '' }>선택</option>
							<option value="정보처리산업기사" ${userInfo.userSkill == '정보처리산업기사' ? 'selected="selected"' : '' }>정보처리산업기사</option>
							<option value="정보처리기사" ${userInfo.userSkill == '정보처리기사' ? 'selected="selected"' : '' }>정보처리기사</option>
							<option value="OCJP" ${userInfo.userSkill == 'OCJP' ? 'selected="selected"' : '' }>OCJP</option>
							<option value="OCJD" ${userInfo.userSkill == 'OCJD' ? 'selected="selected"' : '' }>OCJD</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="school">최종학력</label><br>
					</th>
					<td>
						<select name="userSchool">
						    <option value=null ${userInfo.userSchool == null ? 'selected="selected"' : '' }>선택</option>
							<option value="고등학교졸업" ${userInfo.userSchool == '고등학교졸업' ? 'selected="selected"':'' }>고등학교졸업</option>
							<option value="전문대졸업" ${userInfo.userSchool == '전문대졸업' ? 'selected="selected"':'' }>전문대졸업</option>
							<option value="4년대학졸업" ${userInfo.userSchool == '4년대학졸업' ? 'selected="selected"':'' }>4년대학졸업</option>
							<option value="학사" ${userInfo.userSchool == '학사' ? 'selected="selected"':'' }>학사</option>
							<option value="박사" ${userInfo.userSchool == '박사' ? 'selected="selected"':'' }>박사</option>
						</select> 
					</td>
				</tr>
				<tr>
					<th class="c1">
						<label for="email">이메일</label><br>
					</th>
					<td>
						<input type ="text" name="userEmailId" required="required" value="${userEmailId }"> @
						<input type ="text" name="userEmailAddress" id="emailAddress" required="required" value="${userEmailAddress }">
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
						<input type ="submit" value="확인">
						<input type ="reset" value="취소">
					</td>
				</tr>
					
			</tbody>
		</table>
</form>
</body>
</html>