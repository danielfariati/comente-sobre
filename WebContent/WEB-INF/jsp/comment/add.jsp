<form id="form" action="${pageContext.request.contextPath}/comment" method="post">
	<input type="hidden" name="comment.topic.id" value="${comment.topic.id}" />
	<input type="hidden" type="text" name="comment.topic.subject" value="${comment.topic.subject}" />
	<input type="hidden" type="text" name="comment.topic.subjectURL" value="${comment.topic.subjectURL}" />

	<div id="topic">
		<h1>${comment.topic.subject}</h1>
	</div>
	<div id="comment-header">
		<h2>Novo Coment&aacute;rio</h2>
	</div>
	<div id="comment">
		<div id="email">
			<label for="email-input">E-mail</label>
			<input id="email-input" type="text" name="comment.email" value="${comment.email }"/>
		</div>
		<div id="message">
			<label for="message-area">Mensagem</label>
			<textarea id="message-area" name="comment.message" rows="5" cols="30" value="${comment.message}"></textarea>
		</div>
	</div>
	<div id="submit"><input type="submit" value="Enviar" class="btn btn-primary"/></div>
</form>

<script type="text/javascript">
	$(function() {
		$('#email-input').focus();
	});
</script>
