<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<section id="container">
			<form action="/memberUpdate" method="post">
				<div class="">
					<label class="" for="userId">아이디</label>
					<input class="" type="text" id="userId" name="userid" value="${member.userid}" readonly="readonly"/>
				</div>
				<div class="">
					<label class="" for="userPass">패스워드</label>
					<input class="" type="password" id="userPass" name="userpass" />
				</div>
				<div class="">
					<label class="" for="userName">성명</label>
					<input class="f" type="text" id="userName" name="username" value="${member.username}"/>
				</div>
				<div class="">
					<button class="" type="submit">회원정보수정</button>
					<a href="/">취소</a>
				</div>
			</form>
		</section>
		
</body>
</html>