<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <title></title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
  .error {color:red;font-size:0.8em;}
 </style>
 <script>
 	$(document).ready(function(){
 		
 	});//
 </script>
</head>
<body>
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
    <!-- commentErrors -->
		</tr>
   
  </tbody>
 </table>
 </form>
<br/>	 	

</body>
</html>












