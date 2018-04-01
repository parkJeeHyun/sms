<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SMS</title>
</head>
<body>

<h1>마이페이지</h1>
<br>
팀명 : ${userSession.getTeamName()}<br><br>

<p>팀 사진</p>

  <a href="${pageContext.request.contextPath}/gangjwaController/gaeseolpage">
<input type="button" onclick="alert('강좌 개설 페이지로 이동합니다.')" value="강좌 개설">
</a><br><br>
<a href="${pageContext.request.contextPath}/gangjwaController/input">
<input type="button" onclick="alert('성적 부여 페이지로 이동합니다.')" value="성적 부여">
</a>
<br>
<br>
<br>

<script>
function myFunction() {
    alert("로그아웃을 하겠습니다.");
}
</script>	
<a href="${pageContext.request.contextPath}/loginController/logout" onclick="myFunction()">로그아웃</a>
</body>
</html>