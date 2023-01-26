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
  <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/thema.css'/>
 	<title>INDEX</title>
	<style>	</style>
	<script>
		$(function(){
			$('#loginSubmit').click(function(){
				$.ajax({
					type:"post",
					url:"<%=request.getContextPath()%>/helper/main.do"
				});
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
                      <div class="form-group">
                        <input type="text" name="user_id" class="form-style" placeholder="Your id" id="user_id" autocomplete="off">
                        <i class="input-icon uil uil-at"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input type="password" name="user_pwd" class="form-style" placeholder="Your Password" id="user_pwd" autocomplete="off">
                        <i class="input-icon uil uil-lock-alt"></i>
                      </div>
                      <a href="javascript:void(0);" class="btn mt-4" id='loginSubmit'>submit</a>
                                    <p class="mb-0 mt-4 text-center"><a href="#0" class="link">Forgot your password?</a></p>
                        </div>
                      </div>
                    </div>
                <div class="card-back">
                  <div class="center-wrap">
                    <div class="section text-center">
                      <h4 class="mb-4 pb-3">회원가입</h4>
                      <div class="form-group">
                        <input type="text" name="logname" class="form-style" placeholder="Your Full Name" id="logname" autocomplete="off">
                        <i class="input-icon uil uil-user"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input type="email" name="logemail" class="form-style" placeholder="Your Email" id="logemail" autocomplete="off">
                        <i class="input-icon uil uil-at"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input type="password" name="logpass" class="form-style" placeholder="Your Password" id="logpass" autocomplete="off">
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