<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload a picture</title>
</head>
<body>
	<form method="POST" enctype="multipart/form-data"
		action="upload">
		
 
		<br />
		<label for="title">Title: </label>
		<input type="text" name="title" id="title" size="100" placeholder="Title of page"/><br />
		 
		<label for="altText">Alt Text: </label>
		<input type="text" name="altText" id="altText" size="100" placeholder="Alt Text"/><br /> 
		
		<label for="link">Link: </label>
		<input type="text" name="link" id="link" size="100" value="http://" placeholder="Link"/><br />
		 
		<label for="email" >Email (optional): </label>
		<input type="text" name="email" id="email" size="100" placeholder="Email"/><br /> <br />
		 
		<label for="file">Upload image: </label>
		<input type="file" name="file" id="file"> <br /> <br />
		 
		<input type="submit"
			value="Upload">
	</form>
	</body>
</html>