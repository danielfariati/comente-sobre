<c:choose>
	<c:when test="${empty topicList}">
		Nenhum t&oacute;pico encontrado!
	</c:when>
	<c:otherwise>
		<table class="table table-striped">
			<thead>
				<tr>
					<td>Subject</td>
					<td>URL</td>
					<td>Comments <i class="icon-comment"></i></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${topicList}" var="topic">
					<tr>
						<td>${topic.subject}</td>
						<td>${topic.subjectURL}</td>
						<td>
							<a href="${pageContext.request.contextPath}/${topic.subjectURL}">Ver</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>

<div id="add-topic">
	<form action="${pageContext.request.contextPath}/topic/new" method="get">
		<input type="button" value="Criar Novo T&oacute;pico" class="btn"></input>
	</form>
</div>