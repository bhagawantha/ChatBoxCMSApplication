<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 class="commentCol">Comments</h2>
<c:choose>
	<c:when test="${empty comments}">
		<span class="commentGrey">There are no comments. Be the first to review.</span>
	</c:when>
	<c:otherwise>
		<c:forEach items="${comments}" var="comment" varStatus="loop">
			<span>${loop.index + 1}:</span>
			<span> <c:out escapeXml="true" value="${comment.description}" /></span>
			<span> Added : By <c:out escapeXml="true"
					value="${comment.createdBy}" /> On <c:out escapeXml="true"
					value="${comment.createdDate}" />
			</span>
			<hr />
		</c:forEach>
	</c:otherwise>
</c:choose>