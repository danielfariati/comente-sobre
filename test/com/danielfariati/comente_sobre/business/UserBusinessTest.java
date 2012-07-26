package com.danielfariati.comente_sobre.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.controller.UserController;
import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.model.User;
import com.danielfariati.comente_sobre.repository.CommentRepository;
import com.danielfariati.comente_sobre.repository.UserRepository;
import com.danielfariati.comente_sobre.session.UserSession;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class UserBusinessTest {

	private EntityManager manager;

	private JSR303MockValidator validator;

	private UserRepository repository;

	private CommentRepository commentRepository;

	private UserSession userSession;

	@BeforeClass
	public static void beforeClass() {
		JPAHelper.entityManagerFactory("default");
	}

	@Before
	public void setup() throws SQLException {
		new JIntegrity().cleanAndInsert();

		manager = JPAHelper.currentEntityManager();
		validator = Mockito.spy(new JSR303MockValidator());
		userSession = Mockito.spy(new UserSession());

		repository = new UserBusiness(manager, validator, userSession);
		commentRepository = new CommentBusiness(manager, validator, userSession);
	}

	@After
	public void tearDown() {
		JPAHelper.close();
		new JIntegrity().clean();
	}

	@Test
	public void shouldThrowExceptionIfNameNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setName(null);
		user.setEmail("mail@mail.com");
		user.setPassword("password");

		try {
			repository.save(user);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo nome deve ser preenchido!", "user.name");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfNameEmpty() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setName("");
		user.setEmail("mail@mail.com");
		user.setPassword("password");

		try {
			repository.save(user);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo nome deve ser preenchido!", "user.name");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfEmailNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setName("name");
		user.setEmail(null);
		user.setPassword("password");

		try {
			repository.save(user);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo e-mail deve ser preenchido!", "user.email");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfEmailEmpty() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setName("name");
		user.setEmail("");
		user.setPassword("password");

		try {
			repository.save(user);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo e-mail deve ser preenchido!", "user.email");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfEmailInvalid() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setName("name");
		user.setEmail("mail");
		user.setPassword("password");

		try {
			repository.save(user);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("Por favor, informe um e-mail válido.", "user.email");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfPasswordNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setName("name");
		user.setEmail("mail@mail.com");
		user.setPassword(null);

		try {
			repository.save(user);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo senha deve ser preenchido!", "user.password");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfPasswordEmpty() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setName("name");
		user.setEmail("mail@mail.com");
		user.setPassword("");

		try {
			repository.save(user);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo senha deve ser preenchido!", "user.password");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldMergeIfNoValidationErrors() {
		User expected = new User();
		expected.setName("name");
		expected.setEmail("mail@mail.com");
		expected.setPassword("password");
		expected = repository.save(expected);

		User actual = repository.loadById(expected.getId());

		Collection<User> userList = repository.loadAll();

		assertNotNull(actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getPassword(), actual.getPassword());
		assertEquals(4, userList.size());
	}

	@Test
	public void shouldLogin() {
		User user = new User();
		user.setEmail("email-1@mail.com");
		user.setPassword("password-1");

		repository.login(user);

		assertNotNull(userSession.getUser());
		assertTrue(userSession.isLogged());
	}

	@Test
	public void shouldNotLoginIfInvalidEmail() {
		User user = new User();
		user.setEmail("email-not-existent@mail.com");
		user.setPassword("password-1");

		try {
			repository.login(user);
			fail("exception expected but not throwed");
		} catch(ValidationException e) {
			ValidationMessage expected = new ValidationMessage("E-mail e/ou Senha inválidos!", "user");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldNotLoginIfInvalidPassword() {
		User user = new User();
		user.setEmail("email-1@mail.com");
		user.setPassword("password-not-existent");

		try {
			repository.login(user);
			fail("exception expected but not throwed");
		} catch(ValidationException e) {
			ValidationMessage expected = new ValidationMessage("E-mail e/ou Senha inválidos!", "user");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(UserController.class);
		}
	}

	@Test
	public void shouldLogout() {
		User user = new User();
		user.setEmail("email-1@mail.com");
		user.setPassword("password-1");

		userSession.setUser(user);

		repository.logout();

		assertNull(userSession.getUser());
		assertFalse(userSession.isLogged());
	}

	@Test
	public void shouldCascadeComments() {
		Collection<Comment> commentListBefore = commentRepository.loadAll();

		User user = repository.loadById(1l);
		repository.remove(user);

		Collection<Comment> commentListAfter = commentRepository.loadAll();

		assertEquals(9, commentListBefore.size());
		assertEquals(6, commentListAfter.size());
	}

}
