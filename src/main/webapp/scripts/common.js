$(document).ready(
		function() {
			$('.createBlog').click(function() {
				console.log("createBlog start");
			});

			$('.viewBlog').click(
					function() {
						var discussionId = $(this).attr('id');
						var secureId = $('#secure').val();
						console.log("fetchAllBlogs start :: discussionId == "
								+ discussionId);
						var url = "/home/viewDiscussion/" + discussionId;
						window.location.href = url;
					});

			$('.delBlog').click(function() {
				var discussionsId = $('#discussionsId').val();
				var secureId = $('#secure').val();
				$.ajax({
					url : "/admin/deleteDiscussion?_csrf=" + secureId,
					type : "POST",
					data : {
						discussionId : discussionsId,
					},
					success : function(data, textStatus, jqXHR) {
						window.location.replace("http://localhost:8080/home/");
					},
					error : function(jqXHR, textStatus, errorThrown) {

					}
				});
			});
			$('#commentPushed').click(
					function() {
						var discussionsId = $('#discussionsId').val();
						var comment = $('#commentEntered').val();
						var secureId = $('#secure').val();
						console.log("commentPushed start :: discussionsId == "
								+ discussionsId);
						$.ajax({
							url : "/home/postComment?_csrf=" + secureId,
							type : "POST",
							data : {
								"discussionId" : discussionsId,
								"comment" : comment
							},
							success : function(data, textStatus, jqXHR) {
								$('.commentsDiv').html(data);
								$('#commentEntered').val('');
							},
							error : function(jqXHR, textStatus, errorThrown) {

							}
						});
					});

			$('.editBlog').click(
					function() {
						var discussionId1 = $('#discussionsId').val();
						console.log("editBlog :: discussionId == "
								+ discussionId1);
						var secureId = $('#secure').val();

						$.ajax({
							url : "/admin/editDiscussion?_csrf=" + secureId,
							data:{
								discussionId:discussionId1
							},
							type : "POST",
							success : function(data, textStatus, jqXHR) {
								$('.dispView').html(data);
								$('.attach1').hide();
							},
							error : function(jqXHR, textStatus, errorThrown) {

							}
						});
					});

			$('.homeBlog').click(function() {
				window.location.replace("http://localhost:8080/home/");
			});
			$('#uploadTopic').click(function() {
				window.location.href = "/admin/addDiscussion";
			});
			$('#viewAllTopicId').click(function() {
				window.location.href = "/home/viewAllDiscussion";
			});
			$('.loginBlog').click(function() {
				window.location.replace("http://localhost:8080/chatlogin/");
			});
		});
