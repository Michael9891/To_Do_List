<%@ page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="org.apache.commons.httpclient.*"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
   <title>ERROR</title>
<!-- ----------------------- STYLES----------------------------------- -->
 <jsp:include page="styles.jsp"></jsp:include>
<!-------------------------------------------------------------------- --->
<!--------------------------------HEAD------------------------------- -->
			 <jsp:include page="head.jsp"></jsp:include>
 <br><br>
 <!-- ------------------------------------------------------------- -->
 
</head>
<!-- Error message -->
<body>
	<h1 style="color:black;" align="center"><%=response.getStatus() %><br><%=HttpStatus.getStatusText(response.getStatus())%></h1>
	<h2 style="color:black;" align="center">Something went wrong</h2>
</body>
</html>