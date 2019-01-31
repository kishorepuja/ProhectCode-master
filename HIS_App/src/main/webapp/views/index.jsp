<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="js/app.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="js/app.js"></script>

<style>
form label {
	display: inline-block;
	width: 100px;
}

form div {
	margin-bottom: 10px;
}

.error {
	color: red;
	margin-left: 5px;
}

label.error {
	display: inline;
}
</style>
</head>
<body>

	<h3>Login Here</h3>

	${error}

	<form:form action="login" method="POST" modelAttribute="um" name="loginForm">
		<table>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><form:password path="pwd" /></td>
			</tr>
			<tr>
				<td><input type="Submit" value="Sign In" class="button button2" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>