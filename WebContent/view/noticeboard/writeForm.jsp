<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="<%=request.getContextPath()%>/notice/write.do" method="post">
		<p>
			제목 : <input type="text" name = "title" id="title">	
			<c:if test="${errors.titleEmpty}">제목을 입력하세요 </c:if>
		</p>
		작성자 :<c:if test="${!empty authUser }">${authUser.userId }</c:if><br>
		내용 
		<p>
			<textarea rows="5" cols="30" name="content"></textarea>
			<c:if test="${errors.contentEmpty}">내용을 입력하세요 </c:if>
		</p>
		<input type="submit" value="등록">
	</form>
</body>
</html>