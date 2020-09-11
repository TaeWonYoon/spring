<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form action="listAll" method ="get">
<button type = "submit"> CRud게시판 가기</button>
<b><a href="/register">회원가입</a></b>
<P>  The time on the server is ${serverTime}. </P>
</form>

<form name='homeForm' method="post" action="/login">
		<c:if test="${member == null}">
			<div>
				<label for="userid"></label>
				<input type="text" id="userid" name="userid">
			</div>
			<div>
				<label for="userpass"></label>
				<input type="password" id="userpass" name="userpass">
			</div>
			<div>
				<button type="submit">로그인</button>
				<button type="button">회원가입</button>
			</div>
		</c:if>
		<c:if test="${member != null }">
			<div>
				<p>이름은 ${member.username}임</p>
				<p>${member.userid}님 환영 합니다.</p>
				<button id="memberUpdateBtn" type="button">회원정보 수정</button>
				<button id="logoutBtn" type="button">로그아웃</button>
				<a href="/memberDeleteView">회원탈퇴</a>
			</div>
		</c:if>
		<c:if test="${msg == false}">
			<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
		</c:if>
	</form>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		
		$("#logoutBtn").on("click", function(){
			location.href="/logout";
		});
		
		$("#registerBtn").on("click", function(){
			location.href="/register";
		});
		
		$("#memberUpdateBtn").on("click", function(){
			location.href="/memberUpdateView";
		});
	})
</script>
</body>
</html>
