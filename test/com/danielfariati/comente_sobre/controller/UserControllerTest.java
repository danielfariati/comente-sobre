package com.danielfariati.comente_sobre.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

import com.danielfariati.comente_sobre.model.User;
import com.danielfariati.comente_sobre.repository.UserRepository;

public class UserControllerTest {

	private UserController controller;

	@Spy private Result result = new MockResult();

	@Mock private UserRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UserController(result, repository);
	}

	@Test
	public void shouldCallMethodSave() {
		User user = Mockito.mock(User.class);

		controller.save(user);

		verify(repository, times(1)).save(user);
	}

	@Test
	public void shouldRedirectToIndexAfterSave() {
		User user = Mockito.mock(User.class);

		controller.save(user);

		verify(result).redirectTo(IndexController.class);
	}

	@Test
	public void shouldIncludeUserInLoginPage() {
		User user = Mockito.mock(User.class);
		User newUser = Mockito.mock(User.class);

		controller.loginPage(user, newUser);

		verify(result).include("user", user);
	}

	@Test
	public void shouldIncludeNewUserInLoginPage() {
		User user = Mockito.mock(User.class);
		User newUser = Mockito.mock(User.class);

		controller.loginPage(user, newUser);

		verify(result).include("newUser", newUser);
	}

	@Test
	public void shouldCallMethodLogin() {
		User user = Mockito.mock(User.class);

		controller.login(user);

		verify(repository, times(1)).login(user);
	}

	@Test
	public void shouldRedirectToIndexAfterLogin() {
		User user = Mockito.mock(User.class);

		controller.login(user);

		verify(result).redirectTo(IndexController.class);
	}

	@Test
	public void shouldCallMethodLogout() {
		controller.logout();

		verify(repository, times(1)).logout();
	}

	@Test
	public void shouldRedirectToIndexAfterLogout() {
		controller.logout();

		verify(result).redirectTo(IndexController.class);
	}

}
