<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Discussion Page</title>
<script type="text/javascript" src="/scripts/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/common.js"></script>
<link href="/styles/main.css" rel="stylesheet" />
</head>
<body>
	<input type="hidden" id="secure" value="${_csrf.token}"></input>
	
	<div class="dispView">
	<br/>
			<div class="commentSucc">${successMessage}</div>
		<div class="authView">
			<p>Last Updated:</p>
			<p>By: ${discussionDto.createdBy}</p>
			<p>On: ${discussionDto.createdDate}</p>
		</div>
		<div class="dispView1">
			<input type="hidden" value="${discussionDto.discussionId}"
				id="discussionsId">
			<h2>Topic:</h2>
			<span>${discussionDto.topic}</span>
			<div style="color: #4CAF50;">${updateSuccess}</div>
			<div style="color: red;">${updateFailed}</div>
			<br />
			<div>
				<h2>Description:</h2>
				<span> ${discussionDto.description} </span>
			</div>
			<br />
			<h3>All Attachments:</h3>
			<br />
			<c:forEach items="${discussionDto.fileLocation}" var="fileLocation1"
				varStatus="loop">
				<c:forEach items="${discussionDto.fileType}" var="docType">
				<c:choose>
					<c:when
						test="${fn:contains(docType,'video')}">
						<video  class="width70" src="${baseUrl}/resources/${fileLocation1}" />
						<br />
					</c:when>
					<c:when
						test="${fn:contains(docType,'image')}">
						<img  class="width70" src="${baseUrl}/resources/${fileLocation1}"
							alt="no-image" />
						<br />
					</c:when>
					<c:otherwise>
						<a href="${baseUrl}/resources/${fileLocation1}">${fileLocation1}</a>
						<br />
					</c:otherwise>
				</c:choose>
				<br />
				<br />
				</c:forEach>
			</c:forEach>
			<hr />
			<div class="commentsDiv">
				<jsp:include page="commentsView.jsp"></jsp:include>
			</div>
		</div>
		<div class="dispViewEdit"></div>

		<!-- Comment :  -->
		<div class="commentsEnter">
			<textarea id="commentEntered" rows="4" cols="50"></textarea>
			<input type="submit" class="postComments" value="Post Comment"
				id="commentPushed" class="button" />
		</div>
		<div class="buttonDiv">
			<button class="editBlog">Edit Topic</button>
			<button class="delBlog">Delete Topic</button>
			<p id="viewAllTopicId" class="viewTopic">View all Topic</p>
		</div>
		<br/><br/>
	<button class="homeBlog">Back to Home.!</button>
	</div>
</body>
</html>