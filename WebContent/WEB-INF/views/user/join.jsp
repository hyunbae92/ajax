<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
var isChecked = false;
function checkValue(formObj){
	if(!isChecked){
		alert('아이디 중복확인이 필요합니다.');
		return false;
	}
	var uiId = formObj.ui_id;
	if(!uiId.value || uiId.value.trim().length<4 || uiId.value.trim().length>15){
		alert('id를 확인해주세요!');
		uiId.focus();
		return false;
	}
	var uiPwd = formObj.ui_password;
	if( !uiPwd.value || uiPwd.value.trim().length>30 || uiPwd.value.trim().length<4 ){
		alert('비밀번호를 확인해주세요!');
		uiPwd.focus();
		return false;
	}
	
	var uiName = formObj.ui_name;
	if(!uiName.value || uiName.value.trim().length>10 || uiName.value.trim().length<1 ){
		alert('이름을 확인해주세요!');
		uiName.focus();
		return false;
	}
	var uiAge= formObj.ui_age;
	if(!uiAge.value || uiAge.value<1 || uiAge.value>150){
		alert('나이를 확인해주세요!!');
		uiAge.focus();
		return false;
	}
	var uiBrith = formObj.ui_birth;
	if(!uiBrith.value || uiBrith.value.trim().length!=10){
		alert('생년월일을 확인해주세요');
		uiBrith.focus();
		return false;
	}
	var uiNick = formObj.ui_nickname;
	if(!uiNick.value || uiNick.value.trim().length>20 || uiNick.value.trim().length<4){
		alert('닉네임을 확인해주세요!');
		uiNick.focus();
		return false;
	}
	var uiPhone = formObj.ui_phone;
	if(uiPhone.value){
		if(uiPhone.value.trim().length!=11){
			alert('전화번호를 확인해주세요!');
			uiPhone.focus();
			return false;
		}
	}
	var uiEmail = formObj.ui_email;
	if(uiEmail.value){
		if(uiEmail.value.trim().length<4 || uiEmail.value.trim().length>100){
			alert('이메일을 확인해주세요!');
			uiPhone.focus();
			return false;
		}
	}
}
function checkId(){
	var id = document.querySelector('#ui_id').value;
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/user/checkid?ui_id='+id);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var res = JSON.parse(xhr.responseText)
				alert(res.msg);
				if(res.result=='true'){
				isChecked = true;
				}
			}
		}
	}
	xhr.send();	
}
</script>
<form action="/user/join" method="post" onsubmit="return checkValue(this)">
	<table border="1">
		<tr>
			<td>id</td>
			<th><input type="text" name="ui_id" id="ui_id" onchange="isChecked = false"></th>
			<th><button type="button" onclick="checkId()">중복확인</button></th>	
		</tr>
		<tr>
			<td>password</td>
			<th colspan="2"><input type="password" name="ui_password"></th>	
		</tr>
		<tr>
			<td>이름</td>
			<th colspan="2"><input type="text" name="ui_name"></th>	
		</tr>
		<tr>
			<td>age</td>
			<th colspan="2"><input type="number" name="ui_age"></th>	
		</tr>
		<tr>
			<td>생년월일</td>
			<th colspan="2"><input type="date" name="ui_birth"></th>	
		</tr>
		<tr>
			<td>전화번호</td>
			<th colspan="2"><input type="text" name="ui_phone"></th>	
		</tr>
		<tr>
			<td>e-mail</td>
			<th colspan="2"><input type="text" name="ui_email"></th>	
		</tr>
		<tr>
			<td>별명</td>
			<th colspan="2"><input type="text" name="ui_nickname"></th>	
		</tr>
		<tr>
			<th colspan="3"><button>회원가입</button></th>
		</tr>
	</table>
</form>
</body>
</html>