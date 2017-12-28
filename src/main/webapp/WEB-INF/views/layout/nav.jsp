<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(function(){
	$(".login").click(function(){		
		location.href="${pageContext.request.contextPath}/login/loginForm";
	});
	 
	
});

</script>

	<div class="container-fluid">
    <!-- <div class="navbar-header">
      <a class="navbar-brand" href="#">www.NEXTIT.or.kr</a>
    </div> -->
    
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <%-- <li><a href="${pageContext.request.contextPath}/login/loginForm">로그인</a></li> --%>
      <li><a href="${pageContext.request.contextPath}/member/memberList">회원관리</a></li>
      <li><a href="${pageContext.request.contextPath}/board/boardList">게시판</a></li>
    </ul>
    
    <c:if test="${empty sessionScope.LOGIN_USER}">
	    <ul class="nav navbar-nav navbar-right">
			<li><a class="login"><span class="glyphicon glyphicon-log-in"></span>
			Login</a></li>
		</ul>
	</c:if>
    <c:if test="${not empty sessionScope.LOGIN_USER}">
	    <%-- <div style="display: inline; float: right; padding-top: 15px; padding-bottom: 15px;">
	    	${LOGIN_USER.mem_name} (${LOGIN_USER.mem_id}) 회원님.
	    	<a href="${pageContext.request.contextPath}/login/logout">로그아웃</a>
	    </div> --%>
	    <ul class="nav navbar-nav navbar-right">	    	
	    	<li><a href="${pageContext.request.contextPath}/member/memberView?mem_id=${LOGIN_USER.mem_id}">${LOGIN_USER.mem_name} (${LOGIN_USER.mem_id}) 회원님.</a></li>
			<li><a href="${pageContext.request.contextPath}/login/logout" class="logout"><span class="glyphicon glyphicon-log-out"></span>
			Logout</a></li>
		</ul>
	    
    </c:if>
    
  </div>
