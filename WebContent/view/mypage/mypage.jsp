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
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>  
	<title></title>
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

.center {
	text-align: left;
	margin: 10px;
	margin-bottom:79px;
	
}

.left {
	text-align: left;
}	

.right {
	text-align: right;
}

.ment {
	text-align: right;
	margin-left: 30px;
}
	</style>
	<script>
		$(function(){
			$("#confirm").click(function(){
				
				if(confirm('탈퇴하시겠습니까?')) {
					location.href="<%=request.getContextPath()%>/unRegister.do?userNo=${userInfo.userNo}";
				} else {
					
				}
				
			});
		});
	</script>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
	<div class="center"><h2>마이페이지</h2></div>
	<table border="1" class="table table-dark table-hover">
		<tr>
			<th>아이디</th>
			<td>${userInfo.userId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${userInfo.userName}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${userInfo.userHp}</td>
		</tr>
			<tr>
			<th>주소</th>
			<td>${userInfo.userAddress}</td>
		</tr>
			<tr>
			<th>이메일</th>
			<td>${userInfo.userEmail}</td>
		</tr>
			<tr>
			<th>성별</th>
			<td>
				  <c:choose> 
			    <c:when test="${userInfo.userGender == 'M'}">
			        <a>남자</a>
			    </c:when>
			    <c:when test="${userInfo.userGender == 'F'}">
			        <a>여자</a>
			    </c:when>
			    <c:otherwise>
			        <a>없음</a>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<th>자격증</th>
			<td>
				 <c:choose> 
				    <c:when test="${userInfo.userSkill == null || userInfo.userSkill == ''}">
				        <a>없음</a>
				    </c:when>
				    <c:otherwise>
				        <a>${userInfo.userSkill}</a>
				    </c:otherwise>
				</c:choose>
			</td>
		</tr>
			<tr>
			<th>학력사항</th>
			<td>
				 <c:choose> 
				    <c:when test="${userInfo.userSchool == null || userInfo.userSchool == ''}">
				        <a>없음</a>
				    </c:when>
				    <c:otherwise>
				        <a>${userInfo.userSchool}</a>
				    </c:otherwise>
				</c:choose>
			</td>
		</tr>
			<tr>
			<th>생년월일</th>
			<td>${userInfo.userBirth}</td>
		</tr>
	</table>
<div class="right">
	<a href="javascript:void(0)"><button class="btn btn-secondary" id="confirm">회원 탈퇴</button></a>
	<a href="<%=request.getContextPath()%>/mypageUpdate.do?userId=${userInfo.userId}"><button class="btn btn-secondary">정보 수정하기</button></a>
	<a href="<%=request.getContextPath()%>/mypageChangePwd.do"><button class="btn btn-secondary">비밀번호 변경</button></a>
	<a href="<%=request.getContextPath()%>/mypageArticle.do"><button class="btn btn-secondary">내가 쓴 글</button></a>
</div>

<br>
<c:if test="${authUser.userGrade == 999 }">
<div class="ment">관리자 모드<br></div>
<div class="right">
<a href="<%=request.getContextPath()%>/master/user/list.do" class="btn btn-secondary">회원 목록 보기</a>
<a href="<%=request.getContextPath()%>/master/article/list.do" class="btn btn-secondary">전체글 관리자 모드로 보기</a>
</div>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>











