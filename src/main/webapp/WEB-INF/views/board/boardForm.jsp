<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" >

<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>

<script type="text/javascript">
$(function () {

	$frm = $("#boardForm");
	
	//파일 추가
	$("#btnFileAdd").click(function() {
		$("#fileList").append(
				'<div class="col-xs-12">'+
				'<input type="file" name="uploadFiles" multiple="multiple" style="display:inline;">'+
				'<button type="button" class="btn btn-danger btn-xs btn-delete-file">X</button>'+
				'</div>'	
		
			);
		});
		
		//신규파일삭제
		$("#fileList").on("click", ".btn-delete-file", function() {
			$(this).parent().remove();
		});
		
		//기존파일삭제
		$(".btn-delete-exist").on("click", function() {
			$(this).parent().html('<input type="hidden" name="delFileSeqNo" value="' + $(this).data("file_seq_no")  + '">');
		});
		
	});


	function fn_list() {
		location.href="boardList";
	}
	
	function fn_save(type) {
		
		if(validate() == false){
			return false;
		}
		
		var frm = document.boardForm;
		
		frm.action = "boardInsert";
		
		if(type == 'U'){
			frm.action = "boardUpdate";
		}
		frm.submit();
		
	}
	
	function validate() {
		var frm = document.boardForm;
		
		if(frm.bo_title.value == ""){
			alert("제목을 입력하세요.");
			frm.bo_title.focus();
			return false;
		}
		if(frm.bo_writer.value == ""){
			alert("작성자는 필수 입력 항목입니다.");
			frm.bo_writer.focus();
			return false;
		}
		
		if(frm.bo_content.value == ""){
			alert("내용을 입력하세요.");
			frm.bo_content.focus();
			return false;
		}
		
		
	}
</script>

</head>
<body>

<div class="container">
	<!-- 시퀀스 초기값은 0 -->
	<h3>${board.bo_seq_no == 0 ? '글쓰기' : '글수정'}</h3>
	
	<form name="boardForm" id="boardForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bo_seq_no" value="${board.bo_seq_no}"><!-- 핵심 -->
		<input type="hidden" name="bo_type" value="BBS">
		<table class="table">
			<tr>
				<td width="15%">제목</td>
				<td>
					<div class="col-xs-12"><!-- 12:size MAX -->
						<!-- name은 프로퍼티이름 -->
						<input type="text" name="bo_title" value="${board.bo_title}" class="form-control" placeholder="제목을 입력하세요.">
					</div>
				</td>
			<tr>	
			<tr>
				<td>작성자</td>
				<td>
					<div class="col-xs-3">
						<input type="hidden" name="bo_writer" value="${board.bo_writer}">
						<input type="text" name="bo_writer_name" value="${board.bo_writer_name}" class="form-control" readonly>
					</div>
				</td>
			<tr>	
			<tr>
				<td>공개여부</td>
				<td>
					<div class="checkbox col-xs-12">
						<label for="bo_open_yn"><input type="checkbox" name="bo_open_yn" id="bo_open_yn" value="Y" ${board.bo_open_yn == 'N' ? '' : 'checked'} checked>공개여부</label><!-- label체크시 체크박스체크같음 -->
					</div>
				</td>
			<tr>	
			<!-- 첨부파일 다중선택업로드(갯수만) multiple="multiple" -->
			<tr>
				<td>첨부파일</td>
				<td>
				<p>
					<c:forEach var="fileItem" items="${board.fileItemList}">
							<div style="margin: 5px;">
								<a href="${pageContext.request.contextPath}/common/download?file_seq_no=${fileItem.file_seq_no}">${fileItem.file_name}</a> ${fileItem.file_fancy_size}
								<button type="button" class="btn btn-danger btn-xs btn-delete-exist" data-file_seq_no="${fileItem.file_seq_no}">X</button>
							</div>
						</c:forEach>
				</p>
				<p>	
					<button type="button" class="btn btn-primary btn-xs" id="btnFileAdd">추가</button>
				</p>
				
				<div id="fileList">
					<div class="col-xs-12">
						<input type="file" name="uploadFiles" multiple="multiple" style="display:inline;"><button type="button" class="btn btn-danger btn-xs btn-delete-file">X</button>
					</div>
				</div>
				
				</td>
			<tr>
			<!-- //첨부파일 -->	
			<tr>
				<td colspan="2"><textarea rows="15" name="bo_content" class="form-control">${board.bo_content}</textarea></td>
			<tr>	
			
		</table>
		
		<p align="center">
			
			<c:if test="${board.bo_seq_no == 0}">
			<input type="button" value="저장" class="btn btn-primary" onclick="fn_save('I');">
			</c:if>
			
			<c:if test="${board.bo_seq_no != 0}">
			<input type="button" value="수정" class="btn btn-primary" onclick="fn_save('U');">	
			</c:if>
			
			<input type="reset" value="취소" class="btn btn-primary">
			<input type="button" value="목록" class="btn btn-primary" onclick="fn_list();">
		</p>
		
	</form>

</div>

</body>
</html>