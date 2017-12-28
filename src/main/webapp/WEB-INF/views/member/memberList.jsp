<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.nextit.member.dao.MemberDao"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.nextit.member.model.Member"%>
<%@page import="kr.or.nextit.common.jdbc.ConnectionProvider"%>
<%@page import="kr.or.nextit.member.service.MemberService" %>
<%@page import="kr.or.nextit.member.service.impl.MemberServiceImpl" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%
	/* request.setCharacterEncoding("utf-8");
	
	String searchType = request.getParameter("searchType");
	String searchWord = request.getParameter("searchWord");

	
	Map<String, Object> paramMap = new HashMap();
	
	if(searchWord != null && !searchWord.isEmpty()) {
		paramMap.put("searchType", searchType);
		paramMap.put("searchWord", searchWord);
	}
	
	MemberService memberService = MemberServiceImpl.getInstance();
	List<Member> memberList = memberService.getMemberList(paramMap);
	
	request.setAttribute("memberList", memberList); */
%>

<%-- <c:set var="memberList" value="<%= memberList %>" scope="request" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

<script type="text/javascript">

function fn_form(){
	location.href="memberForm?type=I";
}

function fn_search() {
	
	var frm = document.searchForm;
	
	if(frm.searchType.value != "" && frm.searchWord.value == ""){
		alert("검색어를 입력하세요");
		return false;
	}
	
	frm.submit();
}

</script>

<title>Insert title here</title>
</head>
<body>

<div class="container">
	
	<h2>회원목록</h2>
	<!-- 검색폼 -->
	<form class="form-inline" name="searchForm" action="memberList" method="post">
		<div class="form-group">
		
		<select name="searchType" class="form-control">
			<option value="">전체</option>
			<option value="id" ${param.searchType == 'id' ? 'selected' : ''}>아이디</option>
			<option value="name" ${param.searchType == 'name' ? 'selected' : ''}>이름</option>
		</select>
		
		<%-- <select name="searchType" class="form-control">
			<option value="">전체</option>
			<c:forEach var="searchType" items="${searchTypeMap}">
				<option value=${searchType.key}">${searchType.value}</option>
			</c:forEach>
		</select> --%>
		
		</div>
	
		<div class="form-group">
		<input type="text" name="searchWord" value="${param.searchWord}" class="form-control">
		</div>
		<div class="form-group">
		<input type="button" value="검색" onclick="fn_search()">
		</div>
	</form>
	<!-- 검색폼 -->
	
	<p align="right"><input type="button" value="회원가입" class="btn btn-default" onclick="fn_form()"></p>
	
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>가입일자</th>
			</tr>
		</thead>
		
		<tbody>
		<c:if test="${not empty memberList}">
			<c:forEach var="member" items="${memberList}">
			
				<c:url var="viewURL" value="memberView">
					<c:param name="mem_id" value="${member.mem_id}"></c:param>
				</c:url>
			
				<tr>
					<td>${member.mem_id}</td>
					<%-- <td><a href="memberView.jsp?mem_id=${member.mem_id}">${member.mem_name}</a></td> --%>
					<td><a href="${viewURL}">${member.mem_name}</a></td>
					<td>${member.mem_phone}</td>
					<td>${member.mem_email}</td>
					<td>${member.reg_date}</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty memberList}">
			<tr><td colspan="5" align="center">조회된 결과가 없습니다.</td></tr>
		</c:if>
		</tbody>
	</table>
	
</div>

</body>
</html>






