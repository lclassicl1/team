<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <%@ page import="auth.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 게시글 정보  -->
<table border="1">
	<tr>
		<td>번호</td>
		<td>${trade.tradeNo }</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${trade.readCnt }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${trade.userName }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td><c:out value="${trade.tradeTitle }"></c:out></td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
		${trade.tradeContent }
		</td>
	</tr>
	<tr>
		<td>작성시간</td>
		<td>
		${trade.createDate }
		</td>
	</tr>
	<tr> 
		<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
			<a href="<%=request.getContextPath()%>/trade/list.do?pageNo=${pageNo}">목록</a>
		<c:if test="${authUser.userNo == trade.userNo }">
			<a href="<%=request.getContextPath()%>/trade/modify.do?no=${trade.tradeNo }">게시글 수정</a>
			<a href="<%=request.getContextPath()%>/trade/delete.do?no=${trade.tradeNo }">게시글 삭제</a>
		</c:if>
		</td>
	</tr> 
			
</table>
 <!-- 글쓴이 정보  -->
<table border="1">
	<tr>
		<td>이름</td>
		<td>${user.userName }</td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td>${user.userHp }</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${user.userGender }</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>${user.userAddress }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${user.userEmail }</td>
	</tr>
	<tr>
		<td>자격증</td>
		<td>${user.userSkill }</td>
	</tr>
	<tr>
		<td>최종학력</td>
		<td>${user.userSchool }</td>
	</tr>
</table>
 <!-- 댓글 -->
 <table border="1">
	<tr>
		<th>작성자</th>
		<th>내용</th>
		<th>작성시간</th>
		<th>isshow</th>
	</tr>
	<c:forEach var="item" items="${commentList }">
		<tr>
		<td>
			${item.userId }
		</td>
		<td>
			${item.commContent }
		</td>
		<td>
			${item.commCreDate }
		</td>
		<td>
			${item.isshow }
		</td>
		
		<c:if test="${authUser.userId == item.userId}">
		<td>
			<a href="<%=request.getContextPath()%>/trade/comment/modify.do?commNo=${item.commNo}">댓글 수정</a>
			<a href="<%=request.getContextPath()%>/trade/comment/delete.do?commNo=${item.commNo}">댓글 삭제</a>
		</td>
		</c:if>
		</tr>
	</c:forEach>
	<tr>
	<td colspan="4">
	<form action="<%=request.getContextPath()%>/trade/comment/write.do?no=${trade.tradeNo }" method="post">
		작성자 :<c:if test="${!empty authUser }">${authUser.userId }</c:if><br>
		<p>
			<textarea rows="5" cols="30" name="content"></textarea>
		</p>
		<p>
			<c:if test="${errors.contentEmpty }">댓글 내용을 작성해주세요.</c:if>
		</p>
		<input type="submit" value="등록">
	</form>
	</td>
	</tr>
</table>
</body>
</html>