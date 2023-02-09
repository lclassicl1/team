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
  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css'/>
  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js'/>
  <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/default.css'/>
	<title></title>
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
	<script>
		$(function(){
			
		});
	</script>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
	<div class="card-back">
                  <div class="center-wrap">
                    <div class="section text-center">
                      <h4 class="mb-4 pb-3">회원가입</h4>
                      <div class="form-group">
                        <input type="text" name="user_id" class="form-style" placeholder="아이디" id="user_id" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input type="text" name="user_name" class="form-style" placeholder="이름" id="user_name" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input type="password" name="user_pwd" class="form-style" placeholder="비밀번호" id="user_pwd" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div> 
                      <div class="form-group mt-2">
                        <input type="password" name="user_repwd" class="form-style" placeholder="비밀번호확인" id="user_repwd" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div> 
                      <div class="form-group mt-2">
                        <input type="text" name="user_hp" class="form-style" placeholder="휴대폰번호" id="user_hp" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input type="text" name="user_address" class="form-style" placeholder="주소" id="user_address" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div>
                      <div class="form-group mt-2">
                        <input type="email" name="user_email" class="form-style" placeholder="이메일" id="user_email" autocomplete="off">
                        <i class="input-icon uil uil-at"></i>
                      </div>
                      <div class="form-group mt-2">
                        <input type="date" name="user_birth" class="form-style" id="user_birth" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div>
                      <div class="form-group mt-2">
                        <input type="checkbox" name="user_gender" class="form-style" id="user_genderM" autocomplete="off"><label for='user_genderM'>남</label>
                        <input type="checkbox" name="user_gender" class="form-style" id="user_genderF" autocomplete="off"><label for='user_genderF'>여</label>
                        <i class="input-icon uil uil-at"></i>
                      </div>  
                      <div class="form-group mt-2">
                       		보유자격증:<select name='user_skill' class="form-select">
                        	<option value="">없음</option>
                        	<option value='정보처리산업기사'>정보처리산업기사</option>
                        	<option value='정보처리기사'>정보처리기사</option>
                        	<option value='OCJP'>OCJP</option>
                        	<option value='OCJD'>OCJD</option>
                        </select>
                        <i class="input-icon uil uil-lock-alt"></i>
                      </div>
                      <div class="form-group mt-2">
                       		학력:<select name='user_school'>
                        	<option value="">없음</option>
                        	<option value='고등학교졸업'>고등학교졸업</option>
                        	<option value='전문대졸업'>전문대졸업</option>
                        	<option value='4년대학졸업'>4년대학졸업</option>
                        	<option value='학사'>학사</option>
                        	<option value='박사'>박사</option>
                        </select>
                        <i class="input-icon uil uil-lock-alt"></i>
                      </div>
                      <a href="#" class="btn mt-4" id='registerSubmit'>submit</a>
                        </div>
                      </div>
                    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>                    
</body>
</html>