package com.danielfariati.comente_sobre.interceptor;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.annotation.MustBeLogged;
import com.danielfariati.comente_sobre.controller.UserController;
import com.danielfariati.comente_sobre.session.UserSession;

@Intercepts
public class PermissionInterceptor implements Interceptor {

	private final Result result;
	private final Validator validator;
	private final UserSession session;
	
	public PermissionInterceptor(Result result, Validator validator, UserSession userSession) {
		this.result = result;
		this.validator = validator;
		this.session = userSession;
	}

	public boolean accepts(ResourceMethod method) {
		return method.getMethod().isAnnotationPresent(MustBeLogged.class) || method.getResource().getType().isAnnotationPresent(MustBeLogged.class);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resource) {
		if (!session.isLogged()) {
			validator.add(new ValidationMessage("Você precisa estar logado para realizar esta ação!", "user"));

			result
			.include("errors", validator.getErrors())
			.redirectTo(UserController.class).loginPage(null, null);
		} else {
			stack.next(method, resource);
		}
	}

}
