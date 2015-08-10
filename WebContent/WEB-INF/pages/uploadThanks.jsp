<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thanks for uploading</title>
</head>
<body>
	<c:url value="/kingofthehill/" var="homeURL" />
	<p> Go and visit the <a href="<c:out value="${homeURL}" />">homepage </a> </p>
	<h3><c:out value="${uploadStatus}" /></h3>
	<h3>S3 URI: <c:out value="${s3URI}" /></h3>
	<h3>GrafitiID: <c:out value="${grafitiId}" /></h3>
</body>
</html>