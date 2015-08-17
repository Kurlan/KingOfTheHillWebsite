<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title><c:out value="${grafiti.title}" /></title>
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/styles.css" />">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" defer></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
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
    </div>

    <h5>
        Free queue size: <span id="freeQueueSize"><c:out value="${freeQueueSize}" /></span>
        Paid queue size: <span id="paidQueueSize"><c:out value="${paidQueueSize}" /></span>
    </h5>
    <hr />
    <h3>Replace this image</h3>
    
     <c:url value="/kingofthehill/upload/upload" var="uploadURL" />
    
    <div class="container-fluid">    
        <form method="POST" enctype="multipart/form-data"
            action="<c:out value="${uploadURL}"/>" class="form-inline">
                    <div class="form-group">  
                        <label for="title">Title: </label>            
                        <input type="text" class="form-control" name="title" id="title" placeholder="Title of page"/><br />
                     </div>
                     <div class="form-group">
                        <label for="altText">Alt Text: </label>                  
                        <input type="text" class="form-control" name="altText" id="altText" placeholder="Alt Text"/><br /> 
                     </div>
          
                    <div class="form-group">                     
                        <label for="link" >Link: </label>
                       <input type="text" class="form-control" name="link" id="link" value="http://" placeholder="Link"/><br />
                    </div>
                    <div class="form-group">                      
                        <label for="email" >Email (optional): </label>
                        <input type="text" class="form-control" name="email" id="email" placeholder="Email"/><br /> <br />
                    </div>
                    <div class="form-group">                      
                        <label for="file" >Upload image: </label>
                        <input type="file" class="form-control" name="file" id="file" class="btn btn-default"> <br /> <br />
                    </div>
                    <div class="form-group">                      
                    <input type="submit"
                        value="Upload" class="btn btn-default">
                    </div>
         </form>
    </div>
    
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