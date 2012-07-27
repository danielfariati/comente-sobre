package com.danielfariati.comente_sobre.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.danielfariati.comente_sobre.model.User;
import com.danielfariati.comente_sobre.repository.UserRepository;
import com.danielfariati.comente_sobre.session.UserSession;

@Resource
public class UserController {

	private final Result result;
	private final UserRepository repository;
	private final UserSession session;

	public UserController(Result result, UserRepository repository, UserSession session) {
		this.result = result;
		this.repository = repository;
		this.session = session;
	}

	@Post("/user/new")
	public void save(User newUser) {
		newUser = repository.save(newUser);

		session.setUser(newUser);

		result.redirectTo(IndexController.class).index();
	}

	@Get("/user/login")
	public void loginPage(User user, User newUser) {
		result
		.include("user", user)
		.include("newUser", newUser);
	}

	@Post("/user/login")
	public void login(User user) {
		repository.login(user);

		result.redirectTo(IndexController.class).index();
	}

	@Get("/user/logout")
	public void logout() {
		repository.logout();

		result.redirectTo(IndexController.class).index();
	}
}
