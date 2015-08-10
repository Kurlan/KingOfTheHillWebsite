<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${grafiti.title}" /></title>
</head>
<body>

	<a href="<c:out value="${grafiti.urlLink}" />"> 
		<img
			src="http://<c:out value="${cdnURL}" />/<c:out value="${grafiti.s3Key}" />"
			alt="<c:out value="${grafiti.altText}" />" 
			title="<c:out value="${grafiti.altText}"/>" 
			>
	</a>
</body>
</html>