<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<p>Username: ${account.username} </p>
	<p>Email: ${account.email} </p>
	<p>Password: ${account.password} </p>
	
	<form action="profile/logout" method="POST">
		<button type="submit">Logout</button>
	</form>
	
	<form action="profile/delete" method="POST">
		<button type="submit">Delete profile</button>
	</form>

</body>
</html>