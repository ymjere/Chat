<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ChatRoom</title>
</head>
<body>
	<form method="POST" action="sendMessage">
		<textarea name="message" rows="4" cols="50"></textarea>
		<input type="submit">
	</form>
	<form method="POST" action="changePseudo">
		Pseudo<input name="pseudo" type="text">
		<input type="submit">
	</form>
	<a href="deconnect">Deconnexion</a>
</body>
</html>