<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
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
${modReq}
 <form name="modifyFrm" id="modifyFrm" 
       method="post" action="<%=request.getContextPath()%>/article/modify.do">
 <table border="1">
 	<tr>
 	 <th>작성자명</th>
 	 <td>${modReq.writer_name}</td>
 	</tr>
 	<tr>
 	 <th>내용</th>
 	 <td>
 	 	<textarea name="content" id="content" rows="5" cols="30">${modReq.content}</textarea>
 	 </td>
 	</tr>
 	<tr>          
 	 <td colspan="2" style="text-align:center;">
		<input type="submit" value="수정하기"/>        
 	 	<a href="/review/deleteArticle.do?no=${modReq.articleNumber}">글삭제</a>
 	 </td>
 	</tr>
 </table>
 	  <input type="hidden" name="no" id="no" value="${modReq.reviewNumber}"/>
 	  <input type="hidden" name="writer_name" id="writer_name" value="${modReq.writer_name}"/>
 </form>
</body>
</html>













