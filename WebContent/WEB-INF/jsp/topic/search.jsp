<c:choose>
	<c:when test="${empty topic}">
		T&oacute;pico n&atilde;o encontrado!
	</c:when>
	<c:otherwise>
		<div id="topic">
			<h1>${topic.subject}</h1>
			<div id="comments">
				<div id="comment-list-header">
					Coment&aacute;rios
					<input type="button" value="Adicionar novo" onclick="newComment();" class="btn"/>
				</div>

				<div id="comment-list">
					<c:choose>
						<c:when test="${empty topic.commentList}">
							Nenhum coment&aacute;rio encontrado!
						</c:when>
						<c:otherwise>
							<c:forEach items="${topic.commentList}" var="item">
								<div class="comment-wrapper well">
									<div class="email-wrapper">
										<span class="title">E-mail</span>
										<span class="email">${item.email}</span>
									</div>
									<div class="message-wrapper">
										<span class="title">Message</span>
										<span class="message">${item.message}</span>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	function newComment() {
		var url = '${pageContext.request.contextPath}/comment/${topic.id}';
		$(location).attr('href', url);
	};
</script>
