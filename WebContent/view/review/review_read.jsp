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
<%-- ReadArticleHandler 컨트롤러에 의해 아래와 같이 Model받았다
request.setAttribute("articleData", articleData);//article테이블과 article_content테이블 관련 데이터
request.setAttribute("pageNo", pageNo);//요청페이지
request.setAttribute("rowSize", rowSize);//1페이지당게시글수 --%>
 <a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
 <hr/>
 <table border="1">
 	<tr>
 	 <th>번호</th>
 	 <td>${reviewData.review.review_number}</td>
 	</tr>
 	<tr>
 	 <th>작성자명</th>
 	 <td>${reviewData.review.review_writer.review_writer_name}</td>
 	</tr>
 	<tr>
 	 <th>작성일</th>
 	 <td>
 	 <fmt:formatDate type="both" value="${reviewData.review.review_credate}" />
 	 </td>
 	</tr>
 	<tr>
 	 <th>제목</th>
 	 <td>${reviewData.review.review_title}</td>
 	</tr>
 	<tr>
 	 <th>내용</th>
 	 <td><u:pre value="${reviewData.content.review_content}"/></td>
 	</tr>
 	<tr>          
 	 <td colspan="3" style="text-align:center;">
 	 <%--P662 29라인
 	 <c:set var="변수명" value="변수값"/>--%>
 	 <%-- 게시글 수정,삭제 --%>
 	 <c:set var="pgNo" value="${(empty param.pageNo)?'1':param.pageNo}"/>                      
 	 
 	 	<a href="/review/list.do?pageNo=${pgNo}&rowSize=${rowSize}"><button type="button">목록보기</button></a>
 	 	<%-- 수정과 삭제기능은
 	 	  로그인한 유저의 id와 작성자의 id가 일치하는 경우에만 노출하도록 한다 --%>
 	 	<c:if test="${authUSER.memberid==reviewData.review.review_writer.review_writer_id}">
	 	 	<a href="/review/modify.do?no=${reviewData.review.review_number}&pageNo=${pgNo}&rowSize=${rowSize}"><button type="button">글수정</button></a>
	 	 	<a href="/review/deleter.do?no=${reviewData.review.review_number}"><button type="button">글삭제</button></a>	 	 	
 	 	</c:if>
 	 	</table>
		<br/><br/>
	 	
	 		<%-- <a href="/article/deleteArticle.do?no=${reviewData.review.number}">글삭제(delete용)</a> --%>
	 	 	

	 	
<%-- ---------------------------------------------------------------------------------댓글 목록 --%> 	 	

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

<%--댓글 등록-------------------------------------------------------------------------------- --%>	 	
 	 <form name="frmInsReply" id="frmInsReply" 
       action="<%=request.getContextPath()%>/review/comment/writer.do" method="post">
 <input type="hidden" name="oriNo" id="oriNo" value="${comm_no}"/>
 <input type="hidden" name="writer" id="writer" value="${sessionScope.authUSER.memberid}"/> 
 <table border="1">
  <tbody>
  <!-- 로그인했을 때 작성자 이름 들어갈 위치. -->
  <tr>
 	 <th>작성자명</th>
 	 <td>${authUSER.User_name}</td>
 	<td rowspan="4" colspan="5" style="text-align:center;">
    <a href="/review/comment/writer.do?pageNo=${pgNo}&rowSize=${rowSize}"><button type="button" id="btnInsReply">댓글쓰기</button></a>
   </td>
   <tr>
    <th>댓글내용</th>
    <td colspan="3"><textarea name="rContent" id="rContent" cols="50" rows="3"  onclick="loginChk()" placeholder="깨끗한 댓글, 청결한 에티켓 문화"></textarea></td>
		</tr>
   
  </tbody>
 </table>
 </form>
<br/>	 	

</body>
</html>






