<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <%@ page import="auth.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='../../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../../css/navigation.css'/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>회원 정보</title>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<table border="1" class="table table-dark w-auto table-hover">
	<tr>
		<td>회원 번호</td>
		<td>${user.userNo }</td>
	</tr>
	<tr>
		<td>회원 아이디</td>
		<td>${user.userId }</td>
	</tr>
	<tr>
		<td>회원 이름</td>
		<td>${user.userName}</td>
	</tr>
	<tr>
		<td>회원 전화번호</td>
		<td>${user.userHp }</td>
	</tr>
	<tr>
		<td>회원 가입일</td>
		<td>${user.userRegdate }</td>
	</tr>
	<tr>
		<td>회원 주소</td>
		<td>${user.userAddress }</td>
	</tr>
	<tr>
		<td>회원 등급</td>
		<td>
			 <c:choose> 
			    <c:when test="${user.userGrade == 1}">
			        <a>일반 회원</a>
			    </c:when>
			    <c:when test="${user.userGrade == 100}">
			        <a>탈퇴 회원</a>
			    </c:when>
			    <c:when test="${user.userGrade == 900}">
			        <a>정지 회원</a>
			    </c:when>
			    <c:when test="${user.userGrade == 999}">
			        <a>관리자</a>
			    </c:when>
			    <c:otherwise>
			        <a>회원 등급 없음</a>
			    </c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td>회원 이메일</td>
		<td>${user.userEmail}</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${user.userGender }</td>
	</tr>
	<tr>
		<td>보유 자격증</td>
		<td>
				 <c:choose> 
				    <c:when test="${user.userSkill == null  || user.userSkill == ''}">
				        <a>없음</a>
				    </c:when>
				    <c:otherwise>
				        <a>${user.userSkill}</a>
				    </c:otherwise>
				</c:choose>
			</td>
	</tr>
	<tr>
		<td>최종학력</td>
		<td>
				 <c:choose> 
				    <c:when test="${user.userSchool == null || user.userSchool == ''}">
				        <a>없음</a>
				    </c:when>
				    <c:otherwise>
				        <a>${user.userSchool}</a>
				    </c:otherwise>
				</c:choose>
			</td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td>${user.userBirth }</td>
	</tr>
	<tr> 
		<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
			<a href="<%=request.getContextPath()%>/master/user/list.do?pageNo=${pageNo}">목록</a>
			<a href="<%=request.getContextPath()%>/master/user/article.do?pageNo=${pageNo}&userNo=${user.userNo}">회원이 쓴 게시글 보기</a>
			<c:if test="${user.userGrade == 1 }">
			<a href="<%=request.getContextPath()%>/master/user/black.do?no=${user.userNo}">회원 정지</a>
			</c:if>
			<c:if test="${user.userGrade == 900 }">
			<a href="<%=request.getContextPath()%>/master/user/nomal.do?no=${user.userNo}">회원 정지 해제</a>
			</c:if>
		</td>
	</tr> 
			
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
 
</body>
</html>