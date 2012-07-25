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
										<input id="email-input" type="text" name="comment.email" value="${comment.email }"/>
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
		$('#new-comment-wrapper').toggle();
	};

	$(function() {
		if (!'${errors}') {
			$('#new-comment-wrapper').hide();
		}

		$('#add-comment-btn').focus();
	});
</script>
