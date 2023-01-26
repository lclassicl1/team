<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ page import="jdbc.conn.ConnectionProvider"%>
<%@ page import="java.sql.*" %>


<!DOCTYPE html>
<html lang="ko">
<head>
  	<!-- google cdn 방식 jquery-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <meta charset="UTF-8">
  <meta name="description" content="memberboard web app">
  <meta name="keywords" content="article, javascript, board, webProject">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<style></style>
	<script>
		$(function(){
			
		});
	</script>
</head>
<body>
	<%
		try{
			Connection conn = ConnectionProvider.getConnection();
			out.print("연결성공");
			
		} catch (SQLException e) {
			out.print("연결실패"+e.getMessage());
			application.log("연결실패",e);
		}
	%>
</body>
</html>