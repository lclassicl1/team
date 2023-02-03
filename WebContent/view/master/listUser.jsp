<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>유저 목록</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style></style>
</head>
<body>
<h3>유저 목록</h3>
 <%-- <a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
 <a href="<%=request.getContextPath()%>/article/list.do">게시글 보러가기</a>
<a href="<%=request.getContextPath()%>/article/write.do">게시글 작성하기</a>
<a href="<%=request.getContextPath()%>/view/member/loginPage.jsp">MyPage</a> --%>
 <form action="<%=request.getContextPath()%>/master/user/search.do" method="post">
 유저 상태 :<select name="grade">
 				<option value="0">전체</option>
				<option value="1">일반</option>
				<option value="900">정지</option>
			</select>
검색 :  <input type="text" name="input">
 <input type="submit">
 </form>
 <table border="1">
  <thead>
  	<tr>
  	 <th>회원 번호</th>
  	 <th>회원 아이디</th>
  	 <th>회원 이름</th>
  	 <th>가입 일자</th>
  	 <th>회원 등급</th>
  	</tr>
  </thead>
  <tbody>
   <%-- 게시글이 없는 경우 --%>
   <%-- JSTL if조건문이용하여 출력 --%>
   <c:if test="${userPage.hasNoUser() }">
	   <tr>
	  	 <td colspan="5" style="text-align:center;">회원 정보가 없습니다.</td>
	   </tr>
   </c:if>
   <%-- JSTL forEach반복문이용하여 출력시작 --%>
   <c:forEach var="item" items="${userPage.userList }">
		 	 <tr>
		 	 <td>${item.userNo}</td>
		 	  <td>
		 	  <a href="<%=request.getContextPath()%>/master/user/read.do?no=${item.userNo}">
		 	  	${item.userId}
		 	  </a>
		 	  </td>
		 	  <td>${item.userName}</td>
		 	  <td>${item.userRegdate}</td>
		 	  <td>
		 	  <c:choose> 
				    <c:when test="${item.userGrade == 1}">
				        <a>일반 회원</a>
				    </c:when>
				    <c:when test="${item.userGrade == 900}">
				        <a>정지 회원</a>
				    </c:when>
				    <c:when test="${item.userGrade == 999}">
				        <a>관리자</a>
				    </c:when>
				    <c:otherwise>
				        <a>회원 등급 없음</a>
				    </c:otherwise>
				</c:choose>
		 	  </td>
		 	 </tr>
 	 </c:forEach> 
   <%-- 반복문이용하여 출력끝 --%>
    
   <%-- paging출력 부분 --%>
     <c:if test="${userPage.hasUser() }">
	   <tr>
	  	<td colspan="5" style="text-align:center;">
	  		<c:if test="${userPage.startPage >5 }">
	  			<a href="<%=request.getContextPath()%>/master/user/list.do?pageNo=${userPage.startPage -5 }">pre</a>
	  		</c:if>
	  		 <c:forEach var="pNo" begin="${userPage.startPage }" end="${userPage.endPage }">
	  			<a href="<%=request.getContextPath()%>/master/user/list.do?pageNo=${pNo }">${pNo}</a>
	  		</c:forEach>
	  		<c:if test="${userPage.endPage < userPage.totalPage }">
	  			<a href="<%=request.getContextPath()%>/master/user/list.do?pageNo=${noticePage.endPage+5 }">next</a>
	  		</c:if>
	    </td>
	   </tr>
   </c:if> 
  </tbody>
 </table>
</body>
</html>

















