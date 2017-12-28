<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" >

<script type="text/javascript">
	function fn_writeForm() {
		location.href = "${pageContext.request.contextPath}/board/boardForm";
	}
	
	function fn_loginForm() {
		location.href = "${pageContext.request.contextPath}/board/boardForm";
	}
	function fn_logOut() {
		location.href = "${pageContext.request.contextPath}/board/boardForm";
	}
	
	function fn_search(currentPage) {
		var frm = document.searchForm;
		
		frm.currentPage.value = currentPage;
		
		if(frm.searchType.value !="" && frm.searchWord.value == ""){
			alert("검색어를 입력하세요.");
			return false;
		}
		
		frm.action = "${pageContext.request.contextPath}/board/boardList";//절대경로
		//frm.action = "boardList";//상대경로
		frm.submit();
		
	}

</script>

</head>
<body>

<div class="container">
	
	<h3>게시판 목록</h3>
	
	<div align="right">
			<input type="button" value="글쓰기" class="btn btn-primary" onclick="fn_writeForm();">
	</div>
	
	
	<!-- 검색기능 -->
	<div>
		<form name="searchForm" method="post" class="form-inline">
			<p>
				<input type="hidden" name="currentPage" value="${param.currentPage}">	<!-- 현재페이지 -->
				<select name="searchType" class="form-control">
					<!-- 01,02...공통코드 -->
					<option value="">전체</option>
					<option ${param.searchType == '01' ? 'selected' : ''} value="01">제목</option>
					<option ${param.searchType == '02' ? 'selected' : ''} value="02">내용</option>
					<option ${param.searchType == '03' ? 'selected' : ''} value="03">제목+내용</option>
					<option ${param.searchType == '04' ? 'selected' : ''} value="04">작성자</option>
				</select>
				
				<input type="text" name="searchWord" value="${param.searchWord}" size="40" class="form-control">
				<input type="button" value="검색" onclick="fn_search(1);" class="btn btn-primary">
			</p>
			<p>
				<div class="form-group">
					<label>총 게시글 : ${pagingUtil.totalCount}</label>
				</div>
				<div class="form-group" style="float:right;">
					<label>페이지 사이즈 :</label>
				<select name="pageSize" class="form-control"><!-- 페이지 사이즈 -->
					<option ${param.pageSize == '10' ? 'selected' : ''} value="10">10개</option>
					<option ${param.pageSize == '20' ? 'selected' : ''} value="20">20개</option>
					<option ${param.pageSize == '50' ? 'selected' : ''} value="50">50개</option>
				</select>
				</div>
			</p>
		</form>
	</div>
	
	
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		
		<tbody>
			
			<c:if test="${not empty boardList}" >
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.bo_seq_no}</td>
						
<%-- 						<td><a href="boardView?bo_seq_no=${board.bo_seq_no}">${board.bo_title}</a></td> --%>
						<td><a href="boardView/${board.bo_seq_no}">${board.bo_title}</a></td>
						
						<td>${board.bo_writer_name}</td>
						<td>${board.reg_date}</td>
						<td>${board.bo_hit_cnt}</td>
					</tr>
				</c:forEach>			
			</c:if>
			<c:if test="${empty boardList}" >
				<tr>
					<td colspan="5" align="center">게시글이 존재하지 않습니다.</td>
				</tr>		
			</c:if>
			
			
		</tbody>
	</table>
	
	<!-- 페이지 네이게이션 -->
	<div style="text-align:center;">
		<ul class="pagination">
			${pagingUtil.pageHtml}
		</ul>
	</div>
	
</div>

</body>
</html>