<%@page import="kr.or.nextit.member.service.impl.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.service.MemberService"%>
<%@page import="kr.or.nextit.member.dao.MemberDao"%>
<%@page import="kr.or.nextit.common.jdbc.ConnectionProvider"%>
<%@page import="kr.or.nextit.member.model.Member"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

<title>Insert title here</title>
<c:if test="${isError}">
		<script type="text/javascript">
			alert("${message}");
			//location.href="${pageContext.request.contextPath}/member/memberForm";
			history.pushState(null, "", "${pageContext.request.contextPath}/member/memberForm?type=${param.type}");
		</script>
	</c:if>
<script type="text/javascript">
/* 
 *회원 가입
 */
function fn_save(type) {
	
	if(!validate()){
		return;
	}
	
	var frm = document.memberForm;
	
	<c:if test="${param.type == 'I'}">
		frm.action = "memberInsert?type=${param.type}";
	</c:if>
	
	<c:if test="${param.type != 'I'}">
		frm.action = "memberUpdate";
	</c:if>
	
	/* if(type == "U"){	
		frm.action = "memberUpdate.jsp";
	}else{
		frm.action = "memberInsert.jsp"
	} */
	
	frm.submit();
}

function validate() {
	
	var frm = document.memberForm;
	
	if(frm.mem_id.value == ""){
		alert("아이디를 입력하세요.");
		frm.mem_id.focus();
		return false;
	}
	
	if(frm.mem_name.value == ""){
		alert("이름을 입력하세요.");
		frm.mem_name.focus();
		return false;
	}
	
	if(frm.mem_pwd.value == "" || frm.mem_pwd_confirm.value == ""){
		alert("비밀번호를 입력하세요.");
		return false;
	}else{
		if(frm.mem_pwd.value != frm.mem_pwd_confirm.value){
			alert("비밀번호가 같지 않습니다.");
			return false;
		}
	}
	
	if(frm.mem_birth.value == ""){
		alert("생년월을 입력하세요.");
		frm.mem_birth.focus();
		return false;
	}
	
	if(frm.mem_phone.value == ""){
		alert("전화번호를 입력하세요.");
		frm.mem_phone.focus();
		return false;
	}
	
	if(frm.mem_email.value == ""){
		alert("이메일 입력하세요.");
		frm.mem_email.focus();
		return false;
	}
	
	if(frm.mem_zipcode.value == ""){
		alert("우편번호를 입력하세요.");
		frm.mem_zipcode.focus();
		return false;
	}
	if(frm.mem_addr_master.value == ""){
		alert("주소를 입력하세요.");
		frm.mem_addr_master.focus();
		return false;
	}
	if(frm.mem_addr_detail.value == ""){
		alert("상세주소를 입력하세요.");
		frm.mem_addr_detail.focus();
		return false;
	}
	
	return true;
}

/* 
 * 회원 목록 함수
 */
function fn_list() {
	location.href="memberList";
}

</script>

</head>
<body>
<div class="container">
	<!-- name bean 프로퍼티와 같게 -->
	<c:if test="${empty member.mem_id}">
		<h2>회원가입</h2>
	</c:if>
	
	<c:if test="${not empty member.mem_id}">
		<h2>회원수정</h2>
	</c:if>
	
	<form name="memberForm" method="post">
	<table class="table table-bordered">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="mem_id" value="${member.mem_id}"> <button type="button" class="btn btn-primary">중복체크</button></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="mem_name" size="20" value="${member.mem_name}"> 실명을 입력하세요</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="mem_pwd" size="20"> 8~20자 내의 영문,숫자 조합</td>
		</tr>
		<tr>
			<td>비밀번호확인</td>
			<td><input type="password" name="mem_pwd_confirm" size="20"> 8~20자 내의 영문,숫자 조합</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="text" name="mem_birth" size="20" value="${member.mem_birth}"> 예) 19931231</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="mem_phone" size="20" value="${member.mem_phone}"> '-' 없이 입력</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="mem_email" size="20" value="${member.mem_email}"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<p>
				<input type="text" name="mem_zipcode" size="6" value="${member.mem_zipcode}">
				<button type="button" class="btn btn-primary">우편번호검색</button>
				</p>
				<p><input type="text" name="mem_addr_master" size="50" value="${member.mem_addr_master}"></p>
				<p><input type="text" name="mem_addr_detail" size="50" value="${member.mem_addr_detail}"></p>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${param.type == 'I'}">
					<input type="button" class="btn btn-default" value="가입하기" onclick="fn_save('I')">
				</c:if>
				<c:if test="${param.type != 'I'}">
					<input type="button" class="btn btn-default" value="수정하기" onclick="fn_save('U')">
				</c:if>
				<input type="reset" class="btn btn-default" value="취소">
				<input type="button" class="btn btn-default" value="목록" onclick="fn_list()">
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>