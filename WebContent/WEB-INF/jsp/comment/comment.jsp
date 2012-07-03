<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comente Sobre</title>
	</head>
	<body>
		<form id="formulario" action="http://www.google.com" method="get">
			<div id="assunto">${subject}</div>
			<div id="comentario">
				<div id="email">
					<label for="email-input">E-mail</label>
					<input id="email-input" type="email" required="required"/>
				</div>
				<div id="mensagem">
					<label for="mensagem-area">Mensagem</label>
					<textarea id="mensagem-area" required="required" rows="5" cols="30" style="resize: none;"></textarea>
				</div>
			</div>
			<div id="enviar"><input type="submit" value="Enviar" /></div>
		</form>
	</body>
</html>