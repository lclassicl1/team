<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' type='text/css' href='../../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../../css/navigation.css'/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
  
}
</style>
</head>
<header>
	<jsp:include page="../../module/navBar.jsp"></jsp:include>
</header>
<body>
<c:if test="${authUser.userGrade == 999 }">
관리자 모드<br>
<a href="<%=request.getContextPath()%>/master/user/list.do">회원 목록 보기</a>
<a href="<%=request.getContextPath()%>/master/article/list.do">전체글 관리자 모드로 보기</a>
</c:if>
<hr>

	<c:if test="${!empty authUser }">
 		로그인에 성공하였습니다. 
		${authUser.userName }님 환영합니다.<br> 
		<a href="<%=request.getContextPath()%>/logout.do">로그아웃하기</a>
		<a href="<%=request.getContextPath()%>/changePwd.do">비밀번호 변경</a>
		<a href="<%=request.getContextPath()%>/modifyUserInfo.do">개인정보 수정</a>
		<a href="<%=request.getContextPath()%>/mypage.do">마이페이지</a><br>
		<a href="<%=request.getContextPath()%>/article/list.do">전체글 조회</a>
		<a href="<%=request.getContextPath()%>/help/list.do">해주세요 게시글</a>
		<a href="<%=request.getContextPath()%>/helper/list.do">해줄게요 게시글</a>
		
		
		<a href="<%=request.getContextPath()%>/notice/list.do">공지사항 게시글</a>
		<a href="<%=request.getContextPath()%>/trade/list.do">트레이드 게시글</a>
		<a href="<%=request.getContextPath()%>/freeboard/list.do">자유게시판</a>
		<a href="<%=request.getContextPath()%>/review/list.do">리뷰</a>

	</c:if>
	<c:if test="${empty authUser }">
		<a href="<%=request.getContextPath()%>/login.do">로그인하기</a>
		<a href="<%=request.getContextPath()%>/join.do">회원가입하기</a>
	</c:if>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>