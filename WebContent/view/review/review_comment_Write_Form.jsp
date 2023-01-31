<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>

<script>
//댓글 작성
$('.writeCom').click(function(){
	let com = $('input[type=text').val();
	$('#com').prepend("<p>"+com+"<button class='removeCom'>삭제<button></p>")
	$('input[type=text').val("")
})

//댓글삭제
$('#com').on('click','removeCom',function(){
	$(this).parent().remove()
})
</script>

<input type="text"><button class='writeCom'>댓글작성</button>
<br/>
<div id="com">
	<p>댓글<button class='removeCom'>삭제</button></p>
</div>
</body>
</html>