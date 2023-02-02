<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="ko">

<head>
  <!-- google cdn 방식 jquery-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/default.css' />
  <title>INDEX</title>
  <style>
body {
      background-color: rgb(31 41 55);
      color: rgb(243 244 246);
} 
div {
	margin: auto;
}
#indexPic {
	 width: 550px;
    height: 550px; 
    border-radius: 30%;
    overflow: hidden;
}

.container {
	margin: auto;
}

.input-group-text, .form-control {
	width:500px;
	text-align: center;
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
  	<form action='<%=request.getContextPath()%>/login.do' method='post' id='frm'>
	<div class="container"><h1>오류에 지친자들이여 내게로 오라. 내 너희를 쉬게하리라</h1>
  		<div><img src='<%=request.getContextPath()%>/image/mainimage.jpg' id='indexPic' /></div><br>
  			<div class="input-group-sm mb-3">
  			<span class="input-group-text" id="basic-addon1">ID</span>
  		<input type="text" class="form-control" placeholder="아이디" aria-label="Username" aria-describedby="basic-addon1" name='user_id' id='user_id'>
	</div>
    <div class="input-group-sm mb-3">
  		<span class="input-group-text" id="basic-addon1">PW</span>
  		<input type="password" class="form-control" placeholder="비밀번호" aria-label="UserPassword" aria-describedby="basic-addon1" name='user_pwd' id='user_pwd'>
	</div>
	<div>
		<button type="button" class="btn btn-dark btn-lg" id='submitBtn'>로그인</button>
		<button type="button" class="btn btn-dark btn-lg" id='registerBtn'>회원가입</button>
	</div>
	<div><a href='javascript:void(0);' id='forgot' class="btn btn-dark btn-lg">아이디/비밀번호찾기</a></div>
		</div>
 	 </form>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>