<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function writeSave(){
	if(document.comment.coment.value==""){
		alert("댓글을 입력해주세요.");
		document.comment.commmentt.focus();
		return false;}//변수명 수정예정
	if(document.comment.comment_Writer.value==""){
		alert("로그인 후 이용해주세요.");
		document.comment.comment_Writer.focus();
		return false;}//변수명 수정예정
	
	
}
</script>
</head>
<body>

</body>
</html>