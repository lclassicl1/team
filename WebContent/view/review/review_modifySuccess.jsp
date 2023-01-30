<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
<%--
 컨트롤러에 의해 아래와 같이 Model받았다
 ModifyRequest modReq(로그인한userid,글번호,db의작성자명,db의title,db의내용)
		request.setAttribute("modReq", modReq);
 --%>
 <a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
 <hr/>
 <h3>수정성공!!!(modifySuccess.jsp)</h3>
 	 <%--
 	 <c:set var="변수명" value="변수값"/>--%>
 	 <c:set var="pgNo" 
 	    value="${(empty param.pageNo)?'1':param.pageNo}"/>                      
 	 
 	 	<a href="/article/ListArticle.do?pageNo=${pgNo}&rowSize=3">목록보기(rowSize파라미터설정해야함)</a>
 	 	<%-- 수정과 삭제기능은
 	 	  로그인한 유저의 id와 작성자의 id가 일치하는 경우에만 노출하도록 한다 
 	 	<c:if test="${AUTHUSER.memberid==articleData.article.writer.writer_id}">
	 	 	<a href="/article/modify.do?no=${articleData.article.number}&pageNo=${pgNo}&rowSize=${rowSize}">글수정</a>
	 	 	<a href="/article/deleteArticle.do?no=${articleData.article.number}">글삭제(delete용)</a>
	 	 	<a href="/article/deleteArticle2.do?no=${articleData.article.number}">글삭제(update용)</a>
 	 	</c:if>
 	 	--%>
</body>
</html>






