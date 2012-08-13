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
					<div id="char-count">
						Caracteres restantes: <span id="char-left"></span>
					</div>

					<form id="form" action="${pageContext.request.contextPath}/comment" method="post">
						<input type="hidden" name="comment.topic.id" value="${topic.id}"/>

						<div id="new-comment">
							<div id="email">
								<label for="email-input">E-mail</label>
								<input id="email-input" type="text" name="comment.email" value="${comment.email}"/>
							</div>
							<div id="message">
								<label for="message-area">Mensagem</label>
								<textarea id="message-area" name="comment.message">${comment.message}</textarea>
							</div>
						</div>

						<div><input id="submit" type="submit" value="Enviar" class="btn btn-primary"/></div>
					</form>
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
										<span class="title">E-mail:</span>
										<span class="email">${item.email}</span>
									</div>
									<div class="message-wrapper">
										<span class="title">Message:</span>
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

	function updateCharCount() {
		var maxCharCount = '${messageSize}',
			message = $('#message-area').val();
			actualCharCount = message.length,
			leftCharCount = maxCharCount - actualCharCount;

		if (leftCharCount <= 10) {
			$('#char-left').css('color', 'red');
		} else {
			$('#char-left').css('color', 'black');
		}

		$('#char-left').html(leftCharCount);
	};

	function checkRequiredFields() {
		var message = $('#message-area').val(),
			email = $('#email-input').val();

		if (message.trim().length == 0 || message.trim().length > '${messageSize}' || email.trim().length == 0) {
			$('#submit').attr('disabled', 'disabled').removeClass('btn-primary');
		} else {
			$('#submit').removeAttr('disabled').addClass('btn-primary');
		}
	};

	$(function() {
		if (!'${errors}') {
			$('#new-comment-wrapper').hide();
		}

		updateCharCount();
		checkRequiredFields();

		$('#message-area').bind('keydown keypress keyup', function() {
			updateCharCount();
			checkRequiredFields();
		});

		$('#email-input').bind('keydown keypress keyup', checkRequiredFields);

		$('#add-comment-btn').focus();
	});
</script>
