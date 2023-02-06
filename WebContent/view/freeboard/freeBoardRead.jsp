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
<title>게시글 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<h2><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></h2>
<a href="<%=request.getContextPath()%>/freeboard/list.do">글 목록으로</a>
<script type="text/javascript">
	function sendit(){
		if(document.commentFrm.newComment.value==""){
		alert("댓글 내용을 입력해주세요.");
		document.commentFrm.newComment.focus();
		return false;
		} 
	}
</script>
<body>
<header><jsp:include page="../../module/navBar.jsp"/></header>
<c:forEach var="item" items="${freePage.freeBoardList}">
	<c:if test="${authUser.userName==item.userName}">
		<a href="<%=request.getContextPath()%>/mypageArticle.do">내가 쓴 글 목록으로</a>
	</c:if>
			<p>글번호 : ${item.articleNo}</p>
			<p>글 작성자 : ${item.userName}</p>
			<p>제목: ${item.articleTitle}</p>
			<p>내용: ${item.articleContent}</p>
			<p>카테고리: ${item.freeCategory}</p>
			<p>작성일 : ${item.articleCredate}</p>
			<p>수정일 : ${item.articleUpdate}</p>
<a href="<%=request.getContextPath()%>/freeboard/update.do?no=${item.articleNo}"><button>글 수정하기</button></a>
<a href="<%=request.getContextPath()%>/freeboard/delete.do?no=${item.articleNo}"><button>글 삭제하기</button></a>
	</c:forEach>
<hr>
<hr>

<h3>댓글</h3> 댓글 작성 성공:<c:out value="${commentResult}"></c:out>
<table border="1" class="table table-dark table-hover">
	<tr>
		<th>아이디</th>
		<th>내용</th>
		<th>등록일</th>
		<th>추천수</th>
	</tr>
<c:forEach var="comment" items="${comment.commentList}">
	<tr>
		<td>${comment.user_id}</td>
		<td style="width:500px;">${comment.comm_content}</td>
		<td>${comment.comm_credate}</td>
		<td>${comment.comm_volt}</td>
		 <c:if test="${authUser.userId==comment.user_id}">
		<td><a href="<%=request.getContextPath()%>/freeboard/commentdelete.do?articleNo=${freePage.freeBoardList[0].articleNo}&comm_no=${comment.comm_no}"><button>삭제</button></a></td>
		<td><a href="<%=request.getContextPath()%>/freeboard/commentupdate.do?articleNo=${freePage.freeBoardList[0].articleNo}&comm_no=${comment.comm_no}"><button>수정</button></a></td>
		</c:if>
		<td><a href="<%=request.getContextPath()%>/freeboard/commentlike.do?articleNo=${freePage.freeBoardList[0].articleNo}&comm_no=${comment.comm_no}"><button>추천</button></a></td>
	</tr>
</c:forEach>
</table>
<hr>
<form name="commentFrm" method="post"
		action="/freeboard/commentwrite.do" onsubmit="return sendit();">
		
		<input type="text" name="articleNo" value="${freePage.freeBoardList[0].articleNo}" hidden/>

	<table border="1" class="table table-dark table-hover">
	<tr>
		<th>댓글 내용</th>
			<td>
				<input type="text" name="newComment" style="width:500px;">
			</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;"><input type="submit" value="댓글 입력" /></td>
	</tr>
	</table>
</form>
                                                               
<%-- </c:forEach> --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>