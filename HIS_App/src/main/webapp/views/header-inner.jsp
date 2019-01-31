<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}
</style>
</head>
<body>
	<div class="dropdown">
		<button class="dropbtn">Application Registration</button>
		<div class="dropdown-content">
			<a href="#">Register Application</a> <a href="#">Search
				Applications</a>
		</div>
	</div>

	<div class="dropdown">
		<button class="dropbtn">Data Collection</button>
		<div class="dropdown-content">
			<a href="#">Collect Data</a> <a href="#">Search Cases</a>
		</div>
	</div>

	<div class="dropdown">
		<button class="dropbtn">Determine Eligibility</button>
		<div class="dropdown-content">
			<a href="#">Eligibility Results</a>
		</div>
	</div>

	<div class="dropdown">
		<button class="dropbtn">Correspondence</button>
		<div class="dropdown-content">
			<a href="#">View Pending Notice(s)</a> <a href="#">View History
				Notices</a>
		</div>
	</div>

	<div class="dropdown">
		<button class="dropbtn">Reports</button>
		<div class="dropdown-content">
			<a href="#">Generate Report</a>
		</div>
	</div>

	<div class="dropdown">
		<button class="dropbtn">Profile</button>
		<div class="dropdown-content">
			<a href="#">View Profile</a> <a href="#">Edit Profile</a>
		</div>
	</div>

	<div class="dropdown">
		<button class="dropbtn">Admin</button>
		<div class="dropdown-content">
			<a href="regUserForm">Create Case Worker</a> <a
				href="viewCaseWorkers?cpn=1">View Case Workers</a>
		</div>
	</div>
</body>
</html>
