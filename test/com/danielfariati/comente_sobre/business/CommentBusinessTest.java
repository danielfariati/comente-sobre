package com.danielfariati.comente_sobre.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.jstryker.database.DBUnitHelper;
import org.jstryker.database.JPAHelper;
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
import com.danielfariati.comente_sobre.repository.CommentRepository;

public class CommentBusinessTest {

	private EntityManager manager;

	private JSR303MockValidator validator;

	private CommentRepository repository;

	@BeforeClass
	public static void beforeClass() {
		JPAHelper.entityManagerFactory("default");
	}

	@Before
	public void setup() throws SQLException {
		new DBUnitHelper().cleanInsert("/dataset.xml");

		manager = JPAHelper.currentEntityManager();
		validator = Mockito.spy(new JSR303MockValidator());

		repository = new CommentBusiness(manager, validator);
	}

	@After
	public void tearDown() {
		JPAHelper.close();
		new DBUnitHelper().deleteAll("/dataset.xml");
	}

	@Test
	public void shouldThrowExceptionIfEmailNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		Comment comment = new Comment();
		comment.setEmail(null);
		comment.setMessage("mensagem");
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo e-mail deve ser preenchido!", "comment.email");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfEmailEmpty() {
		Topic topic = new Topic();
		topic.setId(1l);

		Comment comment = new Comment();
		comment.setEmail("");
		comment.setMessage("mensagem");
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo e-mail deve ser preenchido!", "comment.email");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfEmailInvalid() {
		Topic topic = new Topic();
		topic.setId(1l);

		Comment comment = new Comment();
		comment.setEmail("mail");
		comment.setMessage("mensagem");
		comment.setTopic(topic);

		try {
			repository.save(comment);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("Por favor, informe um e-mail válido.", "comment.email");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfMessageNull() {
		Topic topic = new Topic();
		topic.setId(1l);

		Comment comment = new Comment();
		comment.setEmail("email@email.com");
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

		Comment comment = new Comment();
		comment.setEmail("email@email.com");
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
		Comment comment = new Comment();
		comment.setEmail("email@email.com");
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
		Topic topic = new Topic();
		topic.setId(null);

		Comment comment = new Comment();
		comment.setEmail("email@email.com");
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

		Comment expected = new Comment();
		expected.setEmail("email@email.com");
		expected.setMessage("message");
		expected.setTopic(topic);

		expected = repository.save(expected);

		Comment actual = repository.loadById(expected.getId());

		Collection<Comment> commentList = repository.loadAll();

		assertNotNull(expected.getId());

		assertEquals(expected.getEmail(), actual.getEmail());
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
