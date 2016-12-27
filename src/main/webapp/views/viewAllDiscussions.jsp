<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/scripts/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/common.js"></script>
<link href="/styles/main.css" rel="stylesheet" />
</head>
<body>
	<div class="viewAllDiscussion">
		<h1 class="header1">Chatbox View All Discussions</h1>
		<div class="bdyBiv">
			<c:if test="${not empty discussions}">

				<c:forEach var="discuss" varStatus="status" items="${discussions}">
					<div class="allDiscussions">
						<h2>Topic</h2>
						<span> <c:out value="${discuss.topic}" />
						</span>
						<p class="authView">
							Last Updated: <br />By: ${discuss.createdBy} <br /> On:
							${discuss.createdDate}
						</p>
						<h3>Description</h3>
						<span> <c:out value="${discuss.description}" />
						</span>
						<h3>comments</h3>
						<span> <c:forEach var="cmt" varStatus="status"
								items="${discuss.comments}">
								<c:out value="${cmt.description}" />
							</c:forEach>
						</span>
					</div>
					<h3>All Attachments:</h3>
					<br />
					<c:forEach items="${discuss.fileLocation}"
						var="fileLocation1" varStatus="loop">
						<c:forEach items="${discuss.fileType}" var="docType">
							<c:choose>
								<c:when test="${fn:contains(docType,'video')}">
									<video class="width70"
										src="${baseUrl}/resources/${fileLocation1}" />
									<br />
								</c:when>
								<c:when test="${fn:contains(docType,'image')}">
									<img class="width70"
										src="${baseUrl}/resources/${fileLocation1}" alt="no-image" />
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
				</c:forEach>
			</c:if>
		</div>
	</div>
	<br />
	<br />
	<button class="homeBlog">Back to Home.!</button>
</body>
</html>
