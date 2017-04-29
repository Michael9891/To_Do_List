<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title>Registration</title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<!-- ----------------------- STYLES----------------------------------- -->
<jsp:include page="styles.jsp"></jsp:include>
<!-------------------------------------------------------------------- --->
<!--------------------------------HEAD------------------------------- -->
<jsp:include page="head.jsp"></jsp:include>
<br>
<br>
</head>
<!-- ------------------------------------------------------------- -->
<body>
<!-- Registration form sent parameters to registration servlet------------------------------------------------------------- -->
	<form action="registnew" method="POST">
		<div class="jumbotron">
			<div class="container">
				<span class="glyphicon glyphicon-list-alt"></span>
				<h3 style="color:#f96145;">Sign up</h3>
				<h1  style="text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;font-size: 400%; ">T<span style="color: yellow;">o </span>D<span style="color: blue;">o</span> List</h1>
				<div class="box">
					<input type="text" placeholder="username" name="name" required>
					<input type="password" placeholder="password" name="password"
						required> <input type="password"
						placeholder="password again" name="password1" required>
					<button type="submit" class="btn btn-default full-width"
						name="login">
						<span class="glyphicon glyphicon-ok"></span>Create
					</button>
					<p id="error" style="color:red;">${error}</p>
					<a href="loginp" style="color:blue;" >Back to login</a>
				</div>
			</div>
		</div>
	</form>

</body>
</html>