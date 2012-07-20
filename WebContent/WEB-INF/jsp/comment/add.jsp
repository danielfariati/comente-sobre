<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comente Sobre</title>
	</head>
	<body>
		<c:forEach var="error" items="${errors}">
			${error.message}<br />
		</c:forEach>

		<form id="form" action="${pageContext.request.contextPath}/comment" method="post">
			<div id="topic">${topic.subject}</div>
			<div id="comment">
				<div id="email">
					<label for="email-input">E-mail</label>
					<input id="email-input" type="email" name="comment.email" required="required" value="${comment.email }"/>
				</div>
				<div id="message">
					<label for="message-area">Mensagem</label>
					<textarea id="message-area" name="comment.message" required="required" rows="5" cols="30" value="${comment.message}" style="resize: none;"></textarea>
				</div>
			</div>
			<div id="submit"><input type="submit" value="Enviar" onclick="checkFields()" /></div>
		</form>
	</body>

	<script type="text/javascript">
		function checkFields() {
			var email = document.getElementById('email-input'),
				message = document.getElementById('message-area');

			if (email.validity.typeMismatch || email.validity.valueMissing){
				email.setCustomValidity("Por favor, informe um e-mail válido!");
			} else {
				email.setCustomValidity("");
			}

			if (message.validity.valueMissing){
				message.setCustomValidity("Por favor, informe uma mensagem!");
			} else {
				message.setCustomValidity("");
			}
		};
	</script>

</html>
