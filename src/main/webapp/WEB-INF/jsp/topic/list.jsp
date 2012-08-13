<c:choose>
	<c:when test="${empty topicList}">
		Nenhum t&oacute;pico encontrado!
	</c:when>
	<c:otherwise>
		<table class="table table-striped">
			<thead>
				<tr>
					<td>Assunto</td>
					<td>URL</td>
					<td>Coment&aacute;rios <i class="icon-comment"></i></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${topicList}" var="topic">
					<tr>
						<td class="subject">${topic.subject}</td>
						<td class="url">${topic.subjectURL}</td>
						<td class="link">
							<a class="link-inner" href="${pageContext.request.contextPath}/${topic.subjectURL}">Ver</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>

<div id="add-topic">
	<form name="add-form" action="${pageContext.request.contextPath}/topic/new" method="get">
		<input id="submit" type="submit" value="Criar Novo T&oacute;pico" class="btn"></input>
	</form>
</div>