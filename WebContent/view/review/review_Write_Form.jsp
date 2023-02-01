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
 <style>
  .error {color:red;font-size:0.8em;}
 </style>
 <script>
 	$(document).ready(function(){
 		
 	});//
 </script>
</head>
<body>
<%-- WrtieArticleHandler 컨트롤러에 의해 아래와 같이 Model받았다
    class User {
		   private int    memberno;	//회원번호
		   private String memberid;	//id
		   private String membername;	//이름
		   private int    grade;   //회원등급.기본1. 1(정상),2(강퇴),3(탈퇴),4(휴면),999(관리자) }
		request.setAttribute("AUTHUSER", authUser);
		
		//에러정보
		  errors.put("title",Boolean.TRUE);
			errors.put("content",Boolean.TRUE);
		request.setAttribute("errors",errors);

*DB내용:${articleData}<br/><br/>
*요청페이지:${pageNo}<br/>
*1페이지당게시글수:${rowSize}<br/><br/><br/>--%>
<br/><br/>
 <hr/>
 <h3>writeForm.jsp</h3>
 
 <form name="writeFrm" id="writeFrm" 
       method="post" action="<%=request.getContextPath()%>/review/write.do">
 <table border="1">
 	<tr>
 	 <th>작성자명</th>
 	 <td>${authUser.userName}</td>
 	</tr>
 	<tr>
 	 <th>제목</th>
 	 <td>
 	  <input type="text" name="title" id="title"/>
 	  <span class="error"><c:if test="${errors.title}">제목을 입력하세요</c:if></span>
 	 </td>
 	</tr>
 	<tr>
 	 <th>내용</th>
 	 <td>
 	 	<textarea name="content" id="content" rows="5" cols="30"></textarea>
 	 	<span class="error"><c:if test="${errors.content}">내용을 입력하세요</c:if></span>
 	 </td>
 	</tr>
 	<tr>          
 	 <td colspan="2" style="text-align:center;">
		<input type="submit" value="글쓰기"/>
		<a href="/review/modify.do?no=글번호">글 수정</a>
 	 	<a href="/review/deleter.do?no=글번호">글 삭제</a>
 	 </td>
 	</tr>
 </table>
  <a href="<%=request.getContextPath()%>/index.jsp">메인 페이지로 돌아가기</a><br/>
 <a href="<%=request.getContextPath()%>/review/list.do">리뷰/후기 게시판으로 돌아가기</a>
 </form>
</body>
</html>













