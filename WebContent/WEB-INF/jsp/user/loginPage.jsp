<div class="row center">
	<div class="span6 well">
		<form id="form-login" action="${pageContext.request.contextPath}/user/login" method="post">
			<div id="title-login" class="title">
				<h1>Usu&aacute;rio existente</h1>
			</div>
			<div id="inputs-login">
				<div id="email-login" class="input-wrapper">
					<label for="email-login-input">E-mail</label>
					<input id="email-login-input" type="text" name="user.email" value="${user.email}"></input>
				</div>
				<div id="password-login" class="input-wrapper">
					<label for="password-login-input">Senha</label>
					<input id="password-login-input" type="password" name="user.password"></input>
				</div>
			</div>
			<div id="buttons">
				<input type="submit" value="Login" class="btn btn btn-success" />
			</div>
		</form>
	</div>

	<div class="span4 well">
		<form id="form-add-user" action="${pageContext.request.contextPath}/user/new" method="post">
			<div id="title-add-user" class="title">
				<h1>Novo usu&aacute;rio</h1>
			</div>
			<div id="inputs-add">
				<div id="name-add" class="input-wrapper">
					<label for="name-add-input">Nome</label>
					<input id="name-add-input" type="text" name="newUser.name" value="${newUser.name}"></input>
				</div>
				<div id="email-add" class="input-wrapper">
					<label for="email-add-input">E-mail</label>
					<input id="email-add-input" type="text" name="newUser.email" value="${newUser.email}"></input>
				</div>
				<div id="password-add" class="input-wrapper">
					<label for="password-add-input">Senha</label>
					<input id="password-add-input" type="password" name="newUser.password"></input>
				</div>
			</div>
			<div id="buttons">
				<input type="submit" value="Criar" class="btn btn-primary" />
			</div>
		</form>
	</div>
</div>
