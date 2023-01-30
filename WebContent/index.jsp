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
	<style>	</style>
	<script>
		$(function(){
			$('#loginSubmit').click(function(){
				let userid = $('#user_id').val();
				let userpwd = $('#user_pwd').val();
				
				if(userid==""||userid==null) {
					alert('아이디를 입력해주세요');
					$('#user_id').focus();
					return false;
				} else if(userpwd==""||userpwd==null) {
					alert('비밀번호 입력해주세요');
					$('#user_pwd').focus();
					return false;
				}
				
				<%-- $.post('<%=request.getContextPath()%>/helper/index.do'),
					{user_id:"userid", user_pwd:"userpwd"} --%>
			});
		});
	
	</script>
</head>
<body>
	<a href="<%=request.getContextPath()%>/helper/index.do" class="logo" target="_blank">
    <img src="https://assets.codepen.io/1462889/fcy.png" alt="main">
  </a>
  <div class="section">
    <div class="container">
      <div class="row full-height justify-content-center">
        <div class="col-12 text-center align-self-center py-5">
          <div class="section pb-5 pt-5 pt-sm-2 text-center">
            <h6 class="mb-0 pb-3"><span>로그인 </span><span>회원가입</span></h6>
                  <input class="checkbox" type="checkbox" id="reg-log" name="reg-log"/>
                  <label for="reg-log"></label>
            <div class="card-3d-wrap mx-auto">
              <div class="card-3d-wrapper">
                <div class="card-front">
                  <div class="center-wrap">    
                    <div class="section text-center">
                      <h4 class="mb-4 pb-3">로그인</h4>
                      	<form action="<%=request.getContextPath()%>/login.do" method='post'>
    	                  <div class="form-group">
                        <input type="text" name="user_id" class="form-style" placeholder="Your id" id="user_id" autocomplete="off">
                        <i class="input-icon uil uil-at"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input type="password" name="user_pwd" class="form-style" placeholder="Your Password" id="user_pwd" autocomplete="off">
                        <i class="input-icon uil uil-lock-alt"></i>
                      </div>
                      <input type='submit' class="btn mt-4" id='loginSubmit' name='loginSubmit' value='submit'/></form>
                                    <p class="mb-0 mt-4 text-center"><a href="<%=request.getContextPath()%>/foundPwd.do" class="link">Forgot your password?</a></p>
                        </div>
                      </div> 
                    </div>
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
                       		보유자격증:<select name='user_skill'>
                        	<option value="">선택</option>
                        	<option value='정보처리산업기사'>정보처리산업기사</option>
                        	<option value='정보처리기사'>정보처리기사</option>
                        	<option value='OCJP'>OCJP</option>
                        	<option value='OCJD'>OCJD</option>
                        </select>
                        <i class="input-icon uil uil-lock-alt"></i>
                      </div>
                      <div class="form-group mt-2">
                       		학력:<select name='user_school'>
                        	<option value="">선택</option>
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
                  </div>
                </div>
              </div>
            </div>
          </div>
      </div>
  </div>
</body>
</html>