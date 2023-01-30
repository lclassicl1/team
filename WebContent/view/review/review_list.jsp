<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function(){
	//게시물출력수 선택
		//<select name="rowSize" id="rowSize">에 change이벤트
		$("#rowSize").change(function(){
			//let val = $("select#rowSize option:selected").val();
			$("#rowSizeFrm").submit();
		});
});
</script>
</head>
<body>
<h2>리뷰 / 후기</h2>
	<a href="<%=request.getContextPath()%>/review/write.do">글쓰기</a><br/><br/>
	<a href="<%=request.getContextPath()%>/index.jsp">메인 화면으로</a>
<form name="rowSizeFrm" id="rowSizeFrm" 
       action="/review/list.do" method="get"> 
   게시물수:
   <select name="rowSize" id="rowSize">
  		<option value="3">선택</option>
		<option value="3">3</option>
		<option value="5">5</option>
		<option value="10">10</option>
  </select>
 </form> 
 <table border="1">
  <thead>
  	<tr>
  	 <th>번호</th>
  	 <th>제목</th>
  	 <th>작성자</th>
  	 <th>작성일</th>
  	 <th>조회수</th>
  	</tr>
  </thead>
  <tbody>
   <%-- 게시글이 없는 경우 --%>
   <%-- JSTL if조건문이용하여 출력 --%>
   <c:if test="${reviewPage.hasNoReview()}">
   <tr>
  	 <td colspan="5" style="text-align:center;">텅~ 게시판이 비어있어요. </td>
   </tr>
   </c:if>  
   <c:if test="${reviewPage.hasReview()}">
   <c:forEach  var="item" items="${reviewPage.content}">
 	 <tr>
 	  <td>${item.review_number}</td>
 	  <td><a href="/review/read.do?no=${item.review_number}&pageNo=${reviewPage.currentPage}&rowSize=${rsize}">${item.review_title}</a></td>
 	  <td>${item.review_writer.review_writer_name}</td>
 	  <td>
 	      <fmt:formatDate type="date" value="${item.review_credate}" /><br/>
<%--  	      <fmt:formatDate pattern="yyyy/MM/dd" value="${item.review_update}" /><br/>
 	      <fmt:formatDate pattern="yyyy년 MM월 dd일" value="${item.review_update}" /><br/> --%>
 	      <fmt:formatDate pattern="yyyy.MM.dd. HH:mm:ss" value="${item.review_update}" />
 	   </td>
 	  <td>${item.review_read_cnt}</td>
 	 </tr>
 	 </c:forEach>
 	 </c:if>
   <tr>
  	<td colspan="5" style="text-align:center;">
     <%-- JSTL if조건문: 이전출력 --%>
     <c:if test="${reviewPage.startPage>5}">
       <a href="/review/list.do?pageNo=${articlePage.startPage-5}&rowSize=${rsize}">이전</a>
     </c:if>    
     <%-- JSTL forEch조건문: 페이지번호출력 --%>  
     <c:forEach var="pNo"                       
     			begin="${reviewPage.startPage}" 
     			end="${reviewPage.endPage}">
      <a href="/review/list.do?pageNo=${pNo}&rowSize=${rsize}">${pNo}</a> 
     </c:forEach>                                     
     <%-- JSTL if조건문: 다음출력 --%>  
     <c:if test="${reviewPage.endPage<reviewPage.totalPages}">
       <a href="/review/list.do?pageNo=${reviewPage.startPage+5}&rowSize=${rsize}">다음</a>
     </c:if> 
   </td>
   </tr>
  </tbody>
 </table>
</body>
</html>