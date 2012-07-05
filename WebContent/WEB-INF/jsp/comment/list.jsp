<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comente Sobre</title>
	</head>
	<body>
		<div id="subject">
			${subject.name}
		</div>
		<div id="comments">
			<c:choose>
				<c:when test="${empty commentList}">
					<div class="comment-wrapper">Nenhum comentário foi encontrado!</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="comment" items="${commentList}">
						<div class="comment-wrapper">
							<div class="email-wrapper">
								<div class="email-title">Email:</div>
								<div class="email-value">${comment.email}</div>
							</div>
							<div class="mensagem-wrapper">
								<div class="mensagem-title">Mensagem:</div>
								<div class="mensagem-value">${comment.message}</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>