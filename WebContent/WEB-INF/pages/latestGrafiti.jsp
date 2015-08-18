<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" defer></script>
</head>
<body>
    <div class="container-fluid">
        <div id="grafitiContainerDiv">
        <a href="http://<c:out value="${cdnURL}" />/<c:out value="${freeQueue.lastGrafiti.s3Key}" />" id="mainImageLink"> 
            <img
                src="http://<c:out value="${cdnURL}" />/<c:out value="${freeQueue.lastGrafiti.s3Key}" />"
                alt="<c:out value="${freeQueue.lastGrafiti.altText}" />" 
                title="<c:out value="${freeQueue.lastGrafiti.altText}"/>"
                id="mainImage" 
            >
        </a>
        </div>
    </div>

    <div class="container-fluid">
    
    <c:forEach var="oldGrafiti" items="${freeQueue.lastSeveralGrafiti}" varStatus="loop">
        <span class="grafitiHistory">
            <a href="http://<c:out value="${cdnURL}" />/<c:out value="${oldGrafiti.s3Key}" />"> 
                <img
                    src="http://<c:out value="${cdnURL}" />/<c:out value="${oldGrafiti.s3Key}" />"
                    alt="<c:out value="${oldGrafiti.altText}" />" 
                    title="<c:out value="${oldGrafiti.altText}"/>"
                     id="grafitiHistory<c:out value="${loop.index + 1}" />"
                >
            </a>
        </span>
    </c:forEach>
    <c:set var="numberSet" scope="page" value="${fn:length(freeQueue.lastSeveralGrafiti) + 1}"/>
    
    <c:forEach begin="${ numberSet }" end="3" varStatus="loop">
        <span class="grafitiHistory">
                <img
                    src=""
                     id="grafitiHistory<c:out value="${loop.index}" />"
                >
        </span>
    </c:forEach>
    
    </div>


    <hr />
     <c:url value="/kingofthehill/upload/upload" var="uploadURL" />
    
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    Add an image!
    </button>
    
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Upload an image</h4>
          </div>
          <div class="modal-body">
            <!--  Modal Content Start -->
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
                            <!--                  
                            <div class="form-group">                     
                                <label for="link" >Link: </label>
                               <input type="text" class="form-control" name="link" id="link" value="http://" placeholder="Link"/><br />
                            </div>
                            -->
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
            <!--  Modal Content End -->            
            
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
    
    <h5>
        Free queue size: <span id="freeQueueSize"><c:out value="${freeQueue.length}" /></span>
    </h5>
    
    <c:url value="/kingofthehill/queue/FREE" var="freeQueueEndpointURL" />
    <c:url value="/kingofthehill/queue/PAID" var="paidQueueEndpointURL" />
    <script>
        
        function processGrafiti(grafiti) {
        	var cdnUrl = 'http://<c:out value="${cdnURL}" />/' + grafiti.s3Key;
            
            if ($("#mainImage").attr("src") == cdnUrl) {
            	return;
            }
            
            $("#grafitiHistory3").attr("src",  $("#grafitiHistory2").attr("src"));
            $("#grafitiHistory3").attr("alt", $("#grafitiHistory2").attr("alt"));
            $("#grafitiHistory3").attr("title", $("#grafitiHistory2").attr("title"));
            $("#grafitiHistory3").attr("href", $("#grafitiHistory2").attr("href"));
            
            $("#grafitiHistory2").attr("src",  $("#grafitiHistory1").attr("src"));
            $("#grafitiHistory2").attr("alt", $("#grafitiHistory1").attr("alt"));
            $("#grafitiHistory2").attr("title", $("#grafitiHistory1").attr("title"));
            $("#grafitiHistory2").attr("href", $("#grafitiHistory1").attr("href"));
            
            $("#grafitiHistory1").attr("src",  $("#mainImage").attr("src"));
            $("#grafitiHistory1").attr("alt", $("#mainImage").attr("alt"));
            $("#grafitiHistory1").attr("title", $("#mainImage").attr("title"));
            $("#grafitiHistory1").attr("href", $("#mainImage").attr("href"));
            
            
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