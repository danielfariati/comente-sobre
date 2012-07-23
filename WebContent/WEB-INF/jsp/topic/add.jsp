<form id="form-add-topic" action="${pageContext.request.contextPath}/topic/new" method="post">
	<div id = "inputs">
		<div id="subject" class="input-wrapper">
			<label for="subject-input">Assunto</label>
			<input id="subject-input" type="text" name="topic.subject"></input>
		</div>
		<div id="subject-url" class="input-wrapper">
			<label for="subject-url-input">URL</label>
			<span id="url-prefix">${prefixURL}/</span>
			<input id="subject-url-input" type="text" name="topic.subjectURL"></input>
		</div>
	</div>
	<div id="buttons">
		<input type="submit" value="Criar" class="btn btn-primary" />
		<input type="button" value="Cancelar" onclick="cancel();" class="btn" />
	</div>
</form>

<script type="text/javascript">
	function cancel() {
		var url = '${pageContext.request.contextPath}';
		$(location).attr('href', url);
	};

	function suggestURL(subject) {
		var url = subject.toLowerCase();

		url = url.replace(new RegExp('[àáâãäå]', 'g'), 'a');
		url = url.replace(new RegExp('[èéêë]', 'g'), 'e');
		url = url.replace(new RegExp('[ìíîï]', 'g'), 'i');
		url = url.replace(new RegExp('[òóôõö]', 'g'), 'o');
		url = url.replace(new RegExp('[ùúûü]', 'g'), 'u');
		url = url.replace(new RegExp('[ýÿ]', 'g'), 'y');
		url = url.replace(new RegExp('æ', 'g'), 'ae');
		url = url.replace(new RegExp('ç', 'g'), 'c');
		url = url.replace(new RegExp('ñ', 'g'), 'n');
		url = url.replace(new RegExp('œ', 'g'), 'oe');
		url = url.replace(new RegExp(' ', 'g'), '-');
		url = url.replace(new RegExp('[\.+]', 'g'), '-');
		url = url.replace(new RegExp('\\W^\-', 'g'), '');

		return url;
	};

	$(function() {
		$('#subject-input').keyup(function() {
			var subject = $(this).val(),
				url = suggestURL(subject);

			$('#subject-url-input').val(url);
		});
	});
</script>
