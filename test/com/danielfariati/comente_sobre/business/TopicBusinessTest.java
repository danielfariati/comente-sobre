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
import com.danielfariati.comente_sobre.repository.TopicRepository;

public class TopicBusinessTest {

	private JSR303MockValidator validator;

	private EntityManager manager;

	private TopicRepository repository;

	@BeforeClass
	public static void beforeClass() {
		JPAHelper.entityManagerFactory("default");
	}

	@Before
	public void setup() throws SQLException {
		new DBUnitHelper().cleanInsert("/dataset.xml");

		manager = JPAHelper.currentEntityManager();
		validator = Mockito.spy(new JSR303MockValidator());

		repository = new TopicBusiness(manager, validator);
	}

	@After
	public void tearDown() {
		JPAHelper.close();
		new DBUnitHelper().deleteAll("/dataset.xml");
	}

	@Test
	public void shouldThrowExceptionIfSubjectNull() {
		Topic topic = new Topic();
		topic.setSubject(null);
		topic.setSubjectURL("subject-url");

		try {
			repository.save(topic);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo assunto deve ser preenchido!", "topic.subject");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfSubjectEmpty() {
		Topic topic = new Topic();
		topic.setSubject("");
		topic.setSubjectURL("subject-url");

		try {
			repository.save(topic);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo assunto deve ser preenchido!", "topic.subject");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfSubjectURLNull() {
		Topic topic = new Topic();
		topic.setSubject("subject");
		topic.setSubjectURL(null);

		try {
			repository.save(topic);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo URL deve ser preenchido!", "topic.subjectURL");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldThrowExceptionIfSubjectURLEmpty() {
		Topic topic = new Topic();
		topic.setSubject("subject");
		topic.setSubjectURL("");

		try {
			repository.save(topic);
			fail("exception expected but not throwed");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo URL deve ser preenchido!", "topic.subjectURL");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldSaveIfNoValidationErrors() {
		Topic expected = new Topic();
		expected.setSubject("subject");
		expected.setSubjectURL("subject-url");

		expected = repository.save(expected);

		Topic actual = repository.loadById(expected.getId());

		Collection<Topic> topicList = repository.loadAll();

		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getSubject(), actual.getSubject());
		assertEquals(expected.getSubjectURL(), actual.getSubjectURL());
		assertEquals(4, topicList.size());
	}

	@Test
	public void shouldLoadCommentList() {
		Topic topic = repository.loadById(1l);

		Collection<Comment> commentList = topic.getCommentList();

		assertNotNull(topic);
		assertNotNull(commentList);
		assertEquals(3, commentList.size());
	}

	@Test
	public void shouldLoadBySubjectURL() {
		String url = "subjectURL-1";

		Topic topic = repository.loadBySubjectURL(url);

		assertNotNull(topic);
		assertEquals(1l, topic.getId().intValue());
		assertEquals("subject-1", topic.getSubject());
		assertEquals(url, topic.getSubjectURL());
		assertEquals(3, topic.getCommentList().size());
	}

	@Test
	public void shouldReturnNullWhenNotFoundInLoadBySubjectURL() {
		String url = "subjectURL-not-existent";

		Topic topic = repository.loadBySubjectURL(url);

		assertNull(topic);
	}

	@Test
	public void shouldNotSaveIfExistingSubject() {
		try {
			Topic topic = new Topic();
			topic.setSubject("subject-1");
			topic.setSubjectURL("subjectURL-not-existent");

			topic = repository.save(topic);
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O assunto informado já existe!", "topic.subject");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldNotSaveIfExistingSubjectURL() {
		try {
			Topic topic = new Topic();
			topic.setSubject("subject-not-existent");
			topic.setSubjectURL("subjectURL-1");

			topic = repository.save(topic);
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("A URL informada já existe!", "topic.subjectURL");

			assertEquals("should have throw only 1 error", 1, e.getErrors().size());
			assertEquals("error messages should be equals", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("error categories should be equals", expected.getCategory(), e.getErrors().get(0).getCategory());

			verify(validator).onErrorForwardTo(TopicController.class);
		}
	}

	@Test
	public void shouldRemoveTopicByReferenceId() {
		Topic actual = new Topic();
		actual.setId(1l);

		repository.remove(actual);

		Collection<Topic> topicList = repository.loadAll();

		Topic expected = repository.loadById(1l);

		assertNull("should not have comment with id 1", expected);
		assertEquals("should have 2 topics", 2, topicList.size());
	}

}
