<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<head>
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/main.css'/>
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/navigation.css'/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
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
/* #mainicon {
	width: 100px;
	height: 50px;
}	

#myPageicon, #logouticon {
	width: 30px;
	height: 30px;
} */
</style>
<script>
	$(function(){
		$('#logouticon').click(function(){
			if(confirm('로그아웃 하시겠습니까?')) {
				location.href="<%=request.getContextPath()%>/logout.do";
			}
		});
	});

</script>
</head> 
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="<%=request.getContextPath()%>/article/list.do"><img src='<%=request.getContextPath()%>/image/navlogo.png' id='mainicon'></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/notice/list.do">공지사항</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/freeboard/list.do">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/help/list.do">고수님해주세요</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/helper/list.do">고수가해줄게요</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/trade/list.do">트레이드게시판</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/review/list.do">리뷰게시판</a>
      </li>
      <li class="nav-item">
      	
      </li> 
      </ul>
      <ul class='navbar-nav'>
      <li class="nav-item">
        <a class="nav-link text-light" href="<%=request.getContextPath()%>/mypage.do">
        	${authUser.userName}님
        </a>
      </li>  
      <li class="nav-item">
        <a class="nav-link text-light">
        	<img src='<%=request.getContextPath()%>/image/logouticon.png' alt='Logout' id='logouticon'/>
        </a>
      </li>  
    </ul>
  </div>  
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>