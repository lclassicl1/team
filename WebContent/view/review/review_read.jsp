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
 	 <td colspan="2" style="text-align:center;">
 	 <%--P662 29라인
 	 <c:set var="변수명" value="변수값"/>--%>
 	 <c:set var="pgNo" 
 	    value="${(empty param.pageNo)?'1':param.pageNo}"/>                      
 	 
 	 	<a href="/review/list.do?pageNo=${pgNo}&rowSize=${rowSize}">목록보기</a>
 	 	<%-- 수정과 삭제기능은
 	 	  로그인한 유저의 id와 작성자의 id가 일치하는 경우에만 노출하도록 한다 --%>
 	 	<c:if test="${authUSER.memberid==reviewData.review.review_writer.review_writer_id}">
	 	 	<a href="/review/modify.do?no=${reviewData.review.review_number}&pageNo=${pgNo}&rowSize=${rowSize}">글수정</a>
	 	 	<a href="/review/deleter.do?no=${reviewData.review.review_number}">글삭제</a>
	 	 	<%-- <a href="/article/deleteArticle.do?no=${reviewData.review.number}">글삭제(delete용)</a> --%>
 	 	</c:if>
 	 </td>
 	</tr> 	
 	<form type ="button" action=""></form><!-- 댓글관련 -->
 </table>
 	<hr/>
 <table  border="1"><!-- 댓글수정 -->
	
 <th>
 <fmt:formatDate type="both" value="${reviewData.review.review_credate}" />에 작성된 ${reviewData.review.review_writer.review_writer_name} 님의 댓글입니다.
 </th>
 <tr/>
 <th><u:pre value="${reviewData.content.review_content}"/></th>
 </tr>
 	
 	<tr>          
	<c:if test="${authUSER.memberid==reviewData.review.review_writer.review_writer_id}">
	<a href="/review/modify.do?no=${reviewData.review.review_number}&pageNo=${pgNo}&rowSize=${rowSize}">수정</a>
	<a href="/review/deleter.do?no=${reviewData.review.review_number}">삭제</a>
	</c:if>
	 </tr>
 </table>
</body>
</html>






