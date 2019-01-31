<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>

<script>
	$(function() {
		
		$('form[id="regUserForm"]').validate({
			rules : {
				firstName : 'required',
				lastName : 'required',
				email : {
					required : true,
					email : true,
				},
				pwd : {
					required : true,
					minlength : 5,
				},
				dob:'required',
				userRole:'required',
				phno:'required'
			},
			messages : {
				firstName : 'Please enter first name',
				lastName : 'please enter last name',
				email : 'Please enter a valid email',
				pwd : {
					required :'Please enter password',
					minlength : 'Password must be at least 5 characters long'
				},
				dob:'Please select dob',
				userRole:'Please select a role',
				phno:'Please enter Phno'
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
		

		$("#email").blur(function() {
			var enteredEmail = $("#email").val();
			$.ajax({
				url : window.location + "/checkEmail",
				data : "email=" + enteredEmail,
				success : function(result) {

					if (result == 'DUPLICATE') {
						$("#emailMsg").html("Email already registered.!!");
						$("#email").focus();
					} else {
						$("#emailMsg").html("");
					}

				}
			});

		});

		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd/mm/yy',
			maxDate : new Date()
		});
	});
</script>

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

<title>Insert title here</title>
</head>
<body>

	<%@include file="header-inner.jsp"%>


	<h2>Create Case Worker Profile</h2>

	<font color='green'>${SUCCESS}</font>
	<font color='red'>${ERROR}</font>

	<form:form action="regUser" method="POST" modelAttribute="um"
		name="regUserForm" id="regUserForm">
		<table>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" /></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><form:input path="email" id="email" /> <font color='red'><span
						id="emailMsg"></span></font></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><form:password path="pwd" /></td>
			</tr>

			<tr>
				<td>DOB:</td>
				<td><form:input path="dob" id="datepicker" readonly="true" /></td>
			</tr>

			<tr>
				<td>Phno:</td>
				<td><form:input path="phno" /></td>
			</tr>

			<tr>
				<td>Select Role:</td>
				<td><form:select items="${rolesList}" path="userRole" /></td>
			</tr>


			<tr>
				<td><input type="reset" value="Reset" class="reset" /></td>
				<td><input type="Submit" value="Submit" class="button button2" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>