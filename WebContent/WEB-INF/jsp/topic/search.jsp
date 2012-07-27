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
					<input id="add-comment-btn" type="button" value="Adicionar novo" onclick="newComment();" class="btn btn-small"/>
				</div>

				<div id="new-comment-wrapper" class="well">
					<c:choose>
						<c:when test="${empty userSession.user}">
							<div class="alert alert-error">
        						Voc&ecirc; precisa estar logado para realizar esta a&ccedil;&atilde;o!
    						</div>
						</c:when>
						<c:otherwise>
							<form id="form" action="${pageContext.request.contextPath}/comment" method="post">
								<input type="hidden" name="comment.topic.id" value="${topic.id}"/>

								<div id="new-comment">
									<div id="email">
										<label for="email-input">E-mail</label>
										<input id="email-input" type="text" name="comment.email" value="${userSession.user.email }" disabled="disabled"/>
									</div>
									<div id="message">
										<label for="message-area">Mensagem</label>
										<textarea id="message-area" name="comment.message" value="${comment.message}"></textarea>
									</div>
								</div>
	
								<div id="submit"><input type="submit" value="Enviar" class="btn btn-primary"/></div>
							</form>
						</c:otherwise>
					</c:choose>
				</div>

				<div id="comment-list">
					<c:choose>
						<c:when test="${empty topic.commentList}">
							Nenhum coment&aacute;rio encontrado!
						</c:when>
						<c:otherwise>
							<c:forEach items="${topic.commentList}" var="comment">
								<div class="comment-wrapper well">
									<c:if test="${userSession.user.id == comment.user.id}">
										<div class="delete-comment-wrapper float-right">
											<i class="icon-remove" onclick="removeComment(${comment.id}, this);"></i>
										</div>
									</c:if>
									<div class="email-wrapper">
										<span class="title">E-mail</span>
										<span class="email">${comment.user.email}</span>
									</div>
									<div class="message-wrapper">
										<span class="title">Message</span>
										<span class="message">${comment.message}</span>
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
	function removeComment(id, btn) {
		$.ajax({
		    url: '${pageContext.request.contextPath}/comment/' + id,
		    type: 'DELETE',
		}).done(function() {
			$(btn).parent('div').parent('div').remove();
		}).fail(function(xhr) {
			if (xhr.status == 403) {
				showError('Voc&ecirc; n&atilde;o tem permiss&atilde;o para realizar esta a&ccedil;&atilde;o');
			}
		});
	};

	function newComment() {
		$('#new-comment-wrapper').toggle();
	};

	$(function() {
		if (!'${errors}') {
			$('#new-comment-wrapper').hide();
		}

		$('#add-comment-btn').focus();
	});
</script>
