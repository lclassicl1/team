<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<style>
	
/* 백그라운드 배경색 및 기본 body 속성들 */
body {
  font-family: Arial, sans-serif;
  color: rgb(243 244 246);
  display: flex;
  align-items: center;
  height: 100vh;
  background-color: rgb(31 41 55);
}

/* 각각 태그들에 대한 속성 */
form {
  background-color: #333;
  box-shadow: 0 0 10px #ddd;
  padding: 20px;
  border-radius: 10px;
  width: 400px;
  margin: auto;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

label,
input,
button {
  display: block;
  width: 100%;
  margin-bottom: 20px;
}

label {
  font-weight: bold;
}

input,
button {
  padding: 10px;
  border-radius: 5px;
  border: none;
  font-size: 16px;
}
/* 버튼 디자인 */
button {
  background-color: #333;
  color: #fff;
  cursor: pointer;
}
/* 버튼 마우스오버에 대한 속성 */
button:hover {
  background-color: deepskyblue;
}

.links {
  text-align: center;
  margin-top: 20px;
}

.links a {
  color: cornflowerblue;
  font-size: 14px;
  margin-right: 10px;
  text-decoration: none;
}

div{
	text-align: center;
	margin:auto;
}

</style>
<script>
let count = 3;
setInterval(function() {
  count--;
  document.getElementById("countdown").innerHTML = count;
  if (count == 0) {
    window.location.href = "<%=request.getContextPath()%>/index.do";
  }
}, 1000);
</script>
</head>
<body>
<div>
	<h1>성공적으로 탈퇴되었습니다.</h1>
	<a href="javascript:void(0)" class="btn btn-secondary">
		<span id="countdown">3</span>초뒤에 메인페이지로 이동됩니다
	</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script> 
</body>
</html>