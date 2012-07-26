package com.danielfariati.comente_sobre.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.danielfariati.comente_sobre.controller.TopicController;
import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.model.User;
import com.danielfariati.comente_sobre.repository.CommentRepository;
import com.danielfariati.comente_sobre.session.UserSession;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class CommentBusinessTest {

	private EntityManager manager;

	private JSR303MockValidator validator;

	private CommentRepository repository;

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

		repository = new CommentBusiness(manager, validator, userSession);
	}

	@After
	public void tearDown() {
		JPAHelper.close();
		new JIntegrity().clean();
	}

	@Test
	public void shouldThrowExceptionIfUserNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		userSession.setUser(null);

		Comment comment = new Comment();
		comment.setMessage("message");
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("Você precisa estar logado para realizar esta ação!", "comment.user.id");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfUserIdNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setId(null);

		userSession.setUser(user);

		Comment comment = new Comment();
		comment.setMessage("message");
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("Você precisa estar logado para realizar esta ação!", "comment.user.id");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldSetCommentUserToUserInTheSession() {
		Topic topic = new Topic();
		topic.setId(1l);

		User expectedUser = new User();
		expectedUser.setId(1l);

		userSession.setUser(expectedUser);

		Comment comment = new Comment();
		comment.setMessage("message");
		comment.setTopic(topic);

		comment = repository.save(comment);

		Comment actualComment = repository.loadById(comment.getId());
		User actualUser = actualComment.getUser();

		assertEquals(expectedUser.getId(), actualUser.getId());
	}

	@Test
	public void shouldThrowExceptionIfMessageNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setId(1l);

		userSession.setUser(user);

		Comment comment = new Comment();
		comment.setMessage(null);
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo mensagem deve ser preenchido!", "comment.message");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfMessageEmpty() {
		Topic topic = new Topic();
		topic.setId(1l);

		User user = new User();
		user.setId(1l);

		userSession.setUser(user);

		Comment comment = new Comment();
		comment.setMessage("");
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo mensagem deve ser preenchido!", "comment.message");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfTopicNull() {
		User user = new User();
		user.setId(1l);

		userSession.setUser(user);

		Comment comment = new Comment();
		comment.setMessage("message");
		comment.setTopic(null);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo tópico deve ser preenchido!", "comment.topic.id");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfTopicIdNull() {
		User user = new User();
		user.setId(1l);

		userSession.setUser(user);

		Topic topic = new Topic();
		topic.setId(null);

		Comment comment = new Comment();
		comment.setMessage("message");
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo tópico deve ser preenchido!", "comment.topic.id");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldMergeIfNoValidationErrors() {
		Topic topic = new Topic();
		topic.setId(1l);
		topic.setSubject("subject-1");
		topic.setSubjectURL("subjectURL-1");

		User user = new User();
		user.setId(1l);
		user.setName("name-1");
		user.setEmail("email-1@mail.com");
		user.setPassword("password-1");

		userSession.setUser(user);

		Comment expected = new Comment();
		expected.setMessage("message");
		expected.setTopic(topic);

		expected = repository.save(expected);

		Comment actual = repository.loadById(expected.getId());

		Collection<Comment> commentList = repository.loadAll();

		assertNotNull(expected.getId());

		assertEquals(expected.getMessage(), actual.getMessage());
		assertEquals(topic.getId(), actual.getTopic().getId());
		assertEquals(10, commentList.size());
	}

	@Test
	public void shouldLoadByTopicSubject() {
		Topic topic = new Topic();
		topic.setSubject("subject-1");

		Collection<Comment> commentList = repository.loadByTopic(topic);

		assertEquals("should have 3 comments", 3, commentList.size());
	}

	@Test
	public void shouldReturnEmptyWhenNotFoundInSearchByTopicSubject() {
		Topic topic = new Topic();
		topic.setSubject("subject-not-existent");

		Collection<Comment> commentList = repository.loadByTopic(topic);

		assertEquals("should have 0 comments", 0, commentList.size());
	}

	@Test
	public void shouldRemoveCommentByReferenceId() {
		Comment actual = new Comment();
		actual.setId(1l);

		repository.remove(actual);

		Comment expected = repository.loadById(1l);

		assertNull("should not have comment with id 1", expected);
	}

}
