<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comente Sobre</title>
	</head>
	<body>
		<div id="subject">
			<label for="subject-input">Assunto que deseja pesquisar</label>
			<input id="subject-input" type="text" name="topic.subject"/>
		</div>
		<div>
			<input type="button" value="Enviar" onclick="searchTopic();"></input>
		</div>
	</body>

	<script type="text/javascript">
		function searchTopic() {
			var subject = document.getElementById('topic-input').value;
			window.location = '${pageContext.request.contextPath}/' + subject;
		};
	</script>
</html>
