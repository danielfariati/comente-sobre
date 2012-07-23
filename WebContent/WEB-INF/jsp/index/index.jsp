<div id="subject">
	<label for="subject-select">Assunto que deseja pesquisar</label>
	<select id="subject-select">
		<option value="">--selecione--</option>
		<c:forEach items="${topicList}" var="item">
			<option value="${item.subjectURL}">${item.subject}</option>
		</c:forEach>
	</select>
</div>

<div id="buttons">
	<input type="button" value="Buscar" onclick="searchTopic();" class="btn btn-primary"></input>
	<input type="button" value="Criar Novo T&oacute;pico" onclick="createTopic();" class="btn"></input>
</div>

<script type="text/javascript">
	function searchTopic() {
		var subject = $('#subject-select').val(),
			url = '${pageContext.request.contextPath}/' + subject;

		$(location).attr('href', url);
	};

	function createTopic() {
		var url = '${pageContext.request.contextPath}/topic/new';
		$(location).attr('href', url);
	};
</script>
