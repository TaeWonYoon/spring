<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form action="listAll" method ="get">
<button type = "submit"> CRud게시판 가기</button>

<P>  The time on the server is ${serverTime}. </P>
</form>
</body>
</html>
