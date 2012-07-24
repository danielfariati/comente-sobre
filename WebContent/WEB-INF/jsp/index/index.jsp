<div id="subject">
	<label for="subject-select">T&oacute;pico que deseja comentar</label>
	<select id="subject-select">
		<option value="">--selecione--</option>
		<c:forEach items="${topicList}" var="topic">
			<option value="${topic.subjectURL}">${topic.subject}</option>
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

	$(function() {
		$('#subject-select').focus().keypress(function(evt) {
			if (isEnter(evt)) {
				searchTopic();
			}
		});
	});
</script>
