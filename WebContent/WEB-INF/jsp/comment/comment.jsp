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

		<form action="${pageContext.request.contextPath}/${subject.name}/save" method="post">
			<div id="subject">
				${subject.name}
			</div>
			<div id="comment">
				<div id="email">
					<label for="email-input">E-mail</label>
					<input id="email-input" type="email" name="comment.email" required="required"/>
				</div>
				<div id="message">
					<label for="message-area">Mensagem</label>
					<textarea id="message-area" name="comment.message" required="required" rows="5" cols="30" style="resize: none;"></textarea>
				</div>
			</div>
			<div id="submit"><input type="submit" value="Enviar" /></div>
		</form>
	</body>
</html>