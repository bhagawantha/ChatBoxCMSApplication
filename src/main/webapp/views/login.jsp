<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ChatBox Login Page</title>
<title>Home</title>
<script type="text/javascript" src="/scripts/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/common.js"></script>
<link href="/styles/main.css" rel="stylesheet" />
</head>
<body>

	<h1 class="header1">Hi.....!! Welcome to Chatbox</h1>
	<h3 class="pad4">-- A Content Management Solution</h3>
<div class="loginDiv">
	<form:form id="loginForm" method="post" action="login"
		modelAttribute="loginBean">

		<form:label path="username">Enter User Name  </form:label>
		<form:input id="username" name="username" path="username" />
		<br/><br/><br/>
		<form:label path="username">Enter Password    </form:label>
		<form:password id="password" name="password" path="password" />
		<br/><br/><br/>
		<input type="submit" value="Submit" />
	</form:form>
</div>
</body>
</html>