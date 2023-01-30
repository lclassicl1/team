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
	<a href="<%=request.getContextPath()%>/index.do" class="logo" target="_blank">
    <img src="https://assets.codepen.io/1462889/fcy.png" alt="main">
  </a>
  <div class="section">
    <div class="container">
      <div class="row full-height justify-content-center">
        <div class="col-12 text-center align-self-center py-5">
          <div class="section pb-5 pt-5 pt-sm-2 text-center">
            <h6 class="mb-0 pb-3"><span><a href='<%=request.getContextPath()%>/register.do'>회원가입</a></span></h6>
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
                  </div>
                </div>
              </div>
            </div>
          </div>
      </div>
  </div>
</body>
</html>