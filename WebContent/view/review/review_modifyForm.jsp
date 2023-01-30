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
<%-- ModifyArticleHandler 컨트롤러에 의해 아래와 같이 Model받았다
      new ModifyRequest(로그인한userid,글번호,db의작성자명,db의title,db의내용)
			request.setAttribute("modReq", modReq)

*DB내용:${articleData}<br/><br/>
*요청페이지:${pageNo}<br/>
*1페이지당게시글수:${rowSize}<br/><br/><br/>--%>
${modReq}
 <a href="<%=request.getContextPath()%>/index.jsp">메인 페이지로 돌아가기</a>
 <hr/>
 <form name="modifyFrm" id="modifyFrm" 
       method="post" action="<%=request.getContextPath()%>/article/modify.do">
 <table border="1">
 	<tr>
 	 <th>번호</th>
 	 <td>${modReq.reviewNumber}</td>
 	</tr>
 	<tr>
 	 <th>작성자명</th>
 	 <td>${modReq.writer_name}</td>
 	</tr>
 	<tr>
 	 <th>제목</th>
 	 <td>
 	  <input type="text" name="title" id="title" value="${modReq.title}"/>
 	 </td>
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
 	 </td>
 	</tr>
 	<tr>          
 	 <td colspan="2" style="text-align:center;">
 	 	<a href="/review/list.do?pageNo=${pageNo}&rowSize=${rowSize}">목록보기</a>
 	 	<a href="/review/read.do?no=${modReq.reviewNumber}&pageNo=요청페이지&rowSize=로우사이즈">글상세보기</a>
 	 	<a href="/review/deleteArticle.do?no=${modReq.articleNumber}">글삭제</a>
 	 </td>
 	</tr>
 </table>
 	  <input type="hidden" name="no" id="no" value="${modReq.reviewNumber}"/>
 	  <input type="hidden" name="writer_name" id="writer_name" value="${modReq.writer_name}"/>
 	  <%-- 여기에서는 작성자명을 수정처리컨트롤러에 넘기는 방식으로 추가하였다.
 	       현재방식이라면 DB의 작성자명과 세션에 담긴 이름이 동일하므로  세션으로 대체해도 된다. --%>
 </form>
</body>
</html>













