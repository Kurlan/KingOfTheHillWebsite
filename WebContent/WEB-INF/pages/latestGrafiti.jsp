<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${grafiti.title}" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/styles.css" />">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" defer></script>
</head>
<body>
    <div id="grafitiContainerDiv">
    <a href="<c:out value="${grafiti.urlLink}" />" id="mainImageLink"> 
        <img
            src="http://<c:out value="${cdnURL}" />/<c:out value="${grafiti.s3Key}" />"
            alt="<c:out value="${grafiti.altText}" />" 
            title="<c:out value="${grafiti.altText}"/>"
            id="mainImage" 
        >
    </a>
    </div>

    <h4>Free queue size: <span id="freeQueueSize"><c:out value="${freeQueueSize}" /></span></h4>
    <h4>Paid queue size: <span id="paidQueueSize"><c:out value="${paidQueueSize}" /></span></h4>
    <hr />

    <h2>Replace this image</h2>
        <c:url value="/kingofthehill/upload/upload" var="uploadURL" />
        <form method="POST" enctype="multipart/form-data"
            action="<c:out value="${uploadURL}"/>">
        
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
    
    
    <c:url value="/kingofthehill/queue/FREE" var="freeQueueEndpointURL" />
    <c:url value="/kingofthehill/queue/PAID" var="paidQueueEndpointURL" />
    <script>
        
        function processGrafiti(grafiti) {
            var cdnUrl = 'http://<c:out value="${cdnURL}" />/' + grafiti.s3Key;
             $("#mainImage").attr("src", cdnUrl);
             $("#mainImage").attr("alt", grafiti.altText);
             $("#mainImage").attr("title", grafiti.altText);
             $("#mainImageLink").attr("href", grafiti.urlLink);
             $(document).prop('title', grafiti.title);
        }
        
        function updateQueues() {
            $.ajax({
                url: '<c:out value="${freeQueueEndpointURL}"/>',
                dataType: "json",
                success: function(response) {
                    processQueue(response);
                  },
            });
        }
        
        function processQueue(queue) {
        	processGrafiti(queue.lastGrafiti);
        	$("#freeQueueSize").html(queue.length);
        }
        
        setInterval(function () {
        	updateQueues();
        }, 1000);
        
    </script>
</body>
</html>