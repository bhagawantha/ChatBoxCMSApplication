<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>Home</title>
<script type="text/javascript" src="/scripts/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/common.js"></script>
<link href="/styles/main.css" rel="stylesheet" />
</head>
<body>

	<h1 class="header1">Hi.....!! Welcome to Chatbox</h1>
	<h3 class="pad4">-- A Content Management Solution</h3>
	<input type="hidden" id="secure" value="${_csrf.token}"></input>
	<c:if test="${not empty discussions}">
		<div class="bdyBiv">
			<h3>List Of Topics</h3>
			<c:forEach var="discuss" varStatus="status" items="${discussions}">
				<div class="allDiscussions">

					<span class="commentCountDiv"> 
					<span class="commNum"> <c:out
								escapeXml="false" value="${discuss.commentsCount}" /></span> <span class="commTxt">Comments</span>
					<span class="thirdDiv"> <c:out escapeXml="false"
							value="${discuss.topic}" />
					</span> <span class="fourthDiv"> <span id="${discuss.discussionId}"
						title="View Topic" class="viewBlog">View Topic</span>
					</span> <br />
					<p class="authView">
						Last Updated: <br />By: ${discuss.createdBy} <br /> On:
						${discuss.createdDate}
					</p>
					<br />
					<hr />
				</div>
			</c:forEach>
		</div>
	</c:if>
	<p id="uploadTopic" class="uploadTopic">Upload Topic</p>
	<p id="viewAllTopicId" class="viewTopic">View all Topic</p>
	
	<button id="
" class="loginBlog">Login</button>

</body>
</html>
