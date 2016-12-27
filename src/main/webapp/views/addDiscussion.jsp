<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Content Page</title>
<script type="text/javascript" src="/scripts/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/common.js"></script>
<link href="/styles/main.css" rel="stylesheet" />
</head>
<body>

	<div class="uploadDiv">
		<h2>${discussionDto.mode} Discussion with below details</h2>
			<br/>
			<div class="commentSucc">${successMessage}</div>
		<div class="uploadFrame">
			<form:form method="POST"
				action="/admin/storeDiscussion?${_csrf.parameterName}=${_csrf.token}"
				modelAttribute="discussionDto" enctype="multipart/form-data">
				<form:hidden  path="discussionId"/>
				<form:hidden  path="mode"/>
				<p>Enter the Topic :</p>
				<form:input path="topic" maxlength="100"/>
				<br />
				<p>Enter the Description :</p>
				<form:textarea type="text" path="description" cols="70" rows="3" maxlength="150" ></form:textarea>
				<br />
				<div class="attach1">
				<p>Add Attachment 1 :</p>
				<input type="file" name=attachments[0]>
				<br />
				<p>Add Attachment 2 :</p>
				<input type="file" name="attachments[1]">
				<br /><br />
				</div>
				<input class="uploadTopic" type="submit" value="Post Blog"
					class="button">&nbsp;&nbsp;&nbsp;
			</form:form>
			<button id="viewAllBlog" class="viewBlog">View All</button>
		</div>
		<br/><br/>
		<button class="homeBlog">Back to Home.!</button>
	</div>
</body>
</html>