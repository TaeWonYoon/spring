<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<script type="text/javascript">
	$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				location.href = "/";
			})
			
			$("#btnSubmit").on("click", function(){
				var e1 = document.getElementById('e1').value;
				var e2 = document.getElementById('e2').value;
				var e3 = document.getElementById('e3');
				e3.value = e1 + "@" + e2;
				console.log(e3.value);
				
				if($("#userId").val()==""){
					alert("아이디를 입력해주세요.");
					$("#userId").focus();
					return false;
				}
				if($("#userPass").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#userPass").focus();
					return false;
				}
				if($("#userName").val()==""){
					alert("성명을 입력해주세요.");
					$("#userName").focus();
					return false;
				}
				var idChkVal = $("#idChk").val();
				if(idChkVal == "N"){
					alert("중복확인 버튼을 눌러주세요.");
				}else if(idChkVal == "Y"){
					$('#rform').submit();
					console.log(idChkVal);
				}
			});
	})
		function fn_idChk(){
			$.ajax({
				url : "/idChk",
				type : "post",
				dataType : "json",
				data : {"userid" : $("#userId").val()},
				success : function(data){
					if(data == 1){
						alert("중복된 아이디입니다.");
						$('#userId').focus();
					}else if(data == 0){
						$("#idChk").attr("value", "Y");
						alert("사용가능한 아이디입니다.");
					}
				}
			})
		}
</script>
<body>
	
	<div>
		<form method="post" id="rform">
			아이디 <input type="text" name="userid" id="userId">
				 <button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button><br>
			비번 <input type="password" name="userpass" id="userPass"> <br>
			성명 <input type="text" name="username" id="userName"> <br>
			이메일 <input type="text" id="e1"> @ 
			<select id="e2">
				<option value="naver.com">naver.com</option>
				<option>기타</option>
			</select>
			<input type="hidden" name="email" id="e3" value="">
			<button type="button" id="btnSubmit">전송</button>
		</form>
	</div>
<%--	<script>
		function m_s(){
			var e1 = document.getElementById('e1').value;
			var e2 = document.getElementById('e2').value;
			var e3 = document.getElementById('e3');
			e3.value = e1 + "@" + e2;
            document.getElementById('form').submit();
		}

	</script>--%>
</body>
</html>