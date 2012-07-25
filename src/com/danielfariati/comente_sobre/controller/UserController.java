package com.danielfariati.comente_sobre.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.danielfariati.comente_sobre.model.User;
import com.danielfariati.comente_sobre.repository.UserRepository;

@Resource
public class UserController {

	private final Result result;
	private final UserRepository repository;

	public UserController(Result result, UserRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	@Post("/user/new")
	public void save(User newUser) {
		repository.save(newUser);

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
