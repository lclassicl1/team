<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
  	<!-- google cdn 방식 jquery-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src='js/bootstrap.js'></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css'/>
  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js'/>
  <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/default.css'/>
 	<title>INDEX</title>
	<style>
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  display: flex;
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
}

form {
  background-color: rgb(0 0 51);
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

button {
  background-color: cornflowerblue;
  color: #fff;
  cursor: pointer;
}

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
	<script>
	$(function(){
  		//유효성검증
  		function validate() {
  			let idtxt = $('#user_id').val();
  			let pwtxt = $('#user_pwd').val();
  			
  			if(idtxt=='') {
  				alert('아이디값을 입력해주세요');
  				$('#user_id').focus();
  				return false;
  			}
  			/* 비밀번호 Text 유효성검사 */
  			
  			if(pwtxt=='') {
  				alert('비밀번호를 입력해주세요');
  				$('#user_pwd').focus();
  				return false;
  			}
  			
  			$('#frm').submit();
  		}
  		
  		
  		//아이디/비밀번호 찾기 눌렀을때
  		$('#forgot').click(function(){
  			window.open('<%=request.getContextPath()%>/foundPwd.do','_blank','width=500,height=700');
  			
  		});
  		
  		$('#submitBtn').click(function(){
  			/* ID Text 유효성검사 */
  			validate();
  		});
  		
  		//회원가입 버튼 눌렀을대
  		$('#registerBtn').click(function(){
  			location.href='<%=request.getContextPath()%>/register.do';
  		});
  	});
	</script>
</head>
<body>
	 <form id='frm' action='<%=request.getContextPath()%>/login.do' method='post'>
      <h1>코딩해줘</h1>
      <div>
        <label for="user_id">아이디</label>
        <input type="text" id="user_id" name="user_id">
      </div>
      <div>
        <label for="user_pwd">비밀번호</label>
        <input type="password" id="user_pwd" name="user_pwd">
      </div>
      <button type="button" id='submitBtn'>Accept</button>
      <div class="links">
        <a href="javascript:void(0)" id='forgot'>Forgot Password</a>
        <a href="javascript:void(0)" id='registerBtn'>Sign Up</a>
      </div>
    </form>
</body>
</html>