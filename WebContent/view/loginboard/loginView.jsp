<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
      background-color: rgb(31 41 55);
      color: rgb(243 244 246);
} 
</style>
</head>
<body>


	<c:if test="${!empty authUser }">
 		로그인에 성공하였습니다. 
		${authUser.userName }님 환영합니다.<br> 
		<a href="<%=request.getContextPath()%>/logout.do">로그아웃하기</a>
		<a href="<%=request.getContextPath()%>/changePwd.do">비밀번호 변경</a>
		<a href="<%=request.getContextPath()%>/modifyUserInfo.do">개인정보 수정</a><br>
		<a href="<%=request.getContextPath()%>/help/list.do">해주세요 게시글</a>
		<a href="<%=request.getContextPath()%>/helper/list.do">해줄게요 게시글</a>
		
		<a href="<%=request.getContextPath()%>/mypage.do">마이페이지</a>
		
		<a href="#">공지사항 게시글</a>
		<a href="<%=request.getContextPath()%>/trade/list.do">트레이드 게시글</a>
		<a href="<%=request.getContextPath()%>/freeboard/list.do">자유게시판</a>
		<a href="#">리뷰</a>

	</c:if>
	<c:if test="${empty authUser }">
		<a href="<%=request.getContextPath()%>/login.do">로그인하기</a>
		<a href="<%=request.getContextPath()%>/join.do">회원가입하기</a>
	</c:if>
</body>
</html>