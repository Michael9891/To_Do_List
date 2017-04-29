<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
 <%@ page errorPage="/error.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<!-- Header -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" >To Do List    - Accomplish more, every day.</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"></div>
    </div>
</nav>
<title>Login</title>
<!-- ----------------------- STYLES----------------------------------- -->
 <jsp:include page="styles.jsp"></jsp:include>
<!-------------------------------------------------------------------- --->
</head>
<body>	
<p align="left">TO</p><p>DO</p><p>List</p>	 
<div class="jumbotron">
  <div class="container" >
    <span class="glyphicon glyphicon-list-alt"></span>
    <h3 style="color:#f96145;">Sign in</h3>
    <h1  style="text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;font-size: 400%; ">T<span style="color: yellow;">o </span>D<span style="color: blue;">o</span> List</h1>
			<p style="color:blue;">${cookie.userCookie.value},Hello from cookies!</p>
    <div class="box">
    <!-- Login form sent username and password to login servlet ----------------------  -->
    	<form action="login" method="POST" >	
	        <input type="text" placeholder="username" name="username" >
		    <input type="password" placeholder="password" name="password" >
		    <button class="btn btn-default full-width" type="submit" name="login" ><span class="glyphicon glyphicon-ok" ></span>Login</button>
	    </form>
	    <!-- Button to registration page ----------------------  -->
	     <button class="btn btn-default full-width" name="register" onclick="location.href='registp'" ><span class="glyphicon  glyphicon-plus-sign" ></span> Registration</button>
    </div>
  </div>
</div>
</body>
</html>