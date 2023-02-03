<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">

<title>게시판 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<h2><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></h2>
<hr>
<a href="<%=request.getContextPath()%>/freeboard/list.do"><button>전체 글 목록</button></a>
<a href="<%=request.getContextPath()%>/freeboard/write.do"><button>글쓰기</button></a>
<form name="categorySearch" id="categorySearch" method="get" 
		action="<%=request.getContextPath()%>/freeboard/searchBoard.do">
		  <select name='categorySearch'>
			    <option value='' selected>-- 선택 --</option>
			    <option value='자유'>자유</option>
			    <option value='질문'>질문</option>
			    <option value='팁'>팁</option>
  	</select>
			    <input type="text" name="input"/>
			    <input type="submit" value="검색"/>
 </form>
<hr>
<table border="1">
<thead>
	<tr>
		<th>글 번호</th>
		<th>작성자</th>
		<th>제목</th>
	<!-- 	<th>내용</th> -->
		<th>작성일</th>
		<th>수정일</th>
		<th>조회수</th>
		<th>카테고리</th>
	</tr>
</thead>

			<tbody>
					<c:forEach var="freeBoard" items="${freeBoard.list}">
						<tr>
								<td><c:out value="${freeBoard.articleNo}"/></td>
								<td><c:out value="${freeBoard.userName}"/></td>
								<td><a href="<%=request.getContextPath()%>/freeboard/read.do?no=${freeBoard.articleNo}"><c:out value="${freeBoard.articleTitle}"/></a></td>
								<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${freeBoard.articleCredate}"/></td>
								<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${freeBoard.articleUpdate}"/></td>
								<td><c:out value="${freeBoard.articleReadcnt}"/></td>
								<td><c:out value="${freeBoard.freeCategory}"/></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
	</body>
</html>