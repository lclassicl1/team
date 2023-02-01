<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <title></title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style></style>
 <script>
 	$(document).ready(function(){
 		
 	});//
 </script>
</head>
<body>
<%-- ---------------------------------------------------------------------------------댓글 목록 --%> 	 	
<form action="/view/review/review_list.jsp"></form>
 <table border="1">
  <thead>
   <%-- 게시글이 없는 경우 --%>
  <c:if test="${empty review}">
	<th>작성자명 ${authUSER.User_name}</th> <!-- 로그인한 경우에만 작성자명 옆에 작성자 이름이 출력되도록 만들 것. -->
  	<td rowspan="4" colspan="5" style="text-align:center;">댓글을 기다리는 중…</td>
   </c:if>
     <%-- 게시글이 있는 경우 --%>
   <c:if test="${not empty review}">
   <c:forEach  var="item" items="${review}">
 	 <tr>
 	  <td>${item.no}</td>
 	  <td><a href="/review/read.do?no=${item.comm_no}&pageNo=${reviewPage.currentPage}&rowSize=${rsize}">${item.review_title}</a></td>
 	  <td>${item.review_writer.review_writer_name}</td>
 	  <td>
 	      <fmt:formatDate type="date" value="${item.comm_credate}" /><br/>
 	      <fmt:formatDate pattern="yyyy.MM.dd. HH:mm:ss" value="${item.comm_update}" />
 	   </td>
 	  <td>${item.review_read_cnt}</td>
 	 </tr>
 	 </c:forEach>
 	 </c:if>
  	<tr>
  	 <th>댓글번호</th>
  	 </tr>
  	 <tr>
  	 <th>작성자</th>
  	 </tr>
  	 <tr>
  	 <th>작성일</th>
  	</tr>
  </thead>
 </table>
 <br/>
  	 	
 <%-- ----------------------c:if이용 댓글이 있는 경우 c:forEach이용 반복출력 ----------------------------%>
 <c:if test="${not empty replyList}">
	 <table border="1">
	  <tbody>
  <c:forEach var="reply" items="${replyList}">
	   <tr>
	    <th>댓번호</th><td>${reply.no}</td>
	    <th>원글번호</th><td>${reply.orino}</td>
	   </tr>
	   <tr>
	    <th>작성자</th><td>${reply.writer}</td>
	    <th>댓글작성일</th><td>${reply.wday}</td>
	   </tr>
	   <tr>
	    <th>제목</th>
	    <td colspan="3">${reply.title}</td>
	   </tr>
	   <tr>
	    <th>내용</th>
	    <td colspan="3"><u:pre value="${reply.content}"/></td>
	   </tr>
	   <tr>
	    <td colspan="4" class="center">
	      <button type="button" id="btnReplyUpdate">댓글수정</button> 
	      <button type="button" id="btnReplyDelete">댓글삭제</button> 
	      <%-- <button type="button" id="btnReplyGood">좋아요(${comm.volt})</button> 
	      <button type="button" id="btnReplyBad">나빠요(${comm.volt})</button>  --%>
	    </td>
	   </tr>
  </c:forEach>
	  </tbody>
	 </table>
 </c:if>
<br/>

</body>
</html>