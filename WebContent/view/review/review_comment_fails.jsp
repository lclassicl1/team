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
 <c:if test="${cnt==1}">
	 <script>
	  alert("권한이 없습니다. 로그인페이지로 이동합니다");
	 </script>
 </c:if>
 
  <c:if test="${cnt eq 0}">	 
	 <script>  
	  alert("Error발생! 조금있다 다시 시도해주세요.");
	 </script>
  </c:if>
  
  <script>      
  location.href="<%=request.getContextPath()%>/index.jsp"; //지정url로 이동.
  </script>
</body>
</html>










