<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${isError}">
		<script type="text/javascript">
			alert("${message}");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${not isError}">
		<script type="text/javascript">
			alert("${message}");
			
			if(${fn:startsWith(locationURL, 'http')}){
				location.href = "${locationURL}";								
			}else{
				location.href = "${pageContext.request.contextPath}${locationURL}";				
			}
		</script>
	</c:if>
</body>
</html>