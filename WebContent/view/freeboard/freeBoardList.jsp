<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='../css/main.css'/>
<link rel='stylesheet' type='text/css' href='../css/navigation.css'/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>게시판 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
.center {
	text-align: center;
	margin: auto;
}

.left {
	text-align: left;
}
.center{
	text-align: left;
	margin:10px;
}

.input{
	text-align:right;
	margin-top:-20px;
	
} 
.blank {
	margin:10px;
}
.formClass {
	text-align: center;
}
</style>
</head>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header> <!-- header 네비게이션바 --> 
<div class="center"><h3>자유게시판</h3></div>
 <hr/>
<div class="formClass"><form name="categorySearch" id="categorySearch" method="get" 
		action="<%=request.getContextPath()%>/freeboard/searchBoard.do">
 카테고리 : 
			<select name='categorySearch'>
			    <option value='' selected>전체</option>
			    <option value='자유'>자유</option>
			    <option value='질문'>질문</option>
			    <option value='팁'>팁</option>

  	</select>
			   검색: <input type="text" name="input"/>
			  		  <input type="submit" value="검색" class="btn btn-secondary btn-sm blank"/>
 </form>
 </div>
<table border="1" class="table table-dark table-hover">
<thead>
	<tr>
		<th>제목</th>
		<th>카테고리</th>
		<th>작성자</th>
		<th>조회수</th>
		<th>작성일</th>
	</tr>
</thead>
			<tbody>
				 <c:if test="${freePage.hasNoFreeBoard() }">
				   <tr>
				  	 	<td colspan="5" style="text-align:center;">게시글이 없습니다.</td>
				   </tr>
			   </c:if>
						
					<c:forEach var="item" items="${freePage.freeBoardList}">
						<tr>
								<td><a href="<%=request.getContextPath()%>/freeboard/read.do?no=${item.articleNo}"><c:out value="${item.articleTitle}"/></a></td>
								<td><c:out value="${item.freeCategory}"/></td>
								<td><c:out value="${item.userName}"/></td>
								<td><c:out value="${item.articleReadcnt}"/></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.articleCredate}"/></td>
						</tr>
					</c:forEach>
					<%-- paging출력 부분 --%>
					<c:if test="${freePage.hasFreeBoard()}">
					   <tr>
					  		<td colspan="5" style="text-align:center;">
					 			 	<c:if test="${freePage.startPage >5 }">
					  					<a href="<%=request.getContextPath()%>/freeboard/list.do?pageNo=${freePage.startPage -5}">pre</a>
					 		 	</c:if>
					  		 <c:forEach var="pNo" begin="${freePage.startPage }" end="${freePage.endPage}">
					  				<a href="<%=request.getContextPath()%>/freeboard/list.do?pageNo=${pNo }">${pNo}</a>
					  			</c:forEach>
					 		 	<c:if test="${freePage.endPage < freePage.totalPage }">
									  <a href="<%=request.getContextPath()%>/freeboard/list.do?pageNo=${freePage.endPage+5 }">next</a>
					  			</c:if>
					    </td>
					   </tr>
				   </c:if>
			</tbody>
		</table>
		<div class="input"><a href="<%=request.getContextPath()%>/freeboard/write.do" class="btn btn-secondary blank">게시글 작성하기</a></div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>	
	</body>
</html>