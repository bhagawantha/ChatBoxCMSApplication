<div>
	<h3>Update Discussion</h3>
		<h2>Enter New Topic:</h2><input type="text" name="topic"
			value="${discussionDto.topic}" /> <br /> <h2>Description:&nbsp;&nbsp;</h2>
		<input type="text" name="newDescription"
			value="${discussionDto.description}" /> <input type="hidden"
			value="${discussionDto.discussionId}" name="discussionId"> <br />
		<input type="submit" value="update" class="button" />
</div>