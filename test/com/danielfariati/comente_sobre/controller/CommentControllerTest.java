package com.danielfariati.comente_sobre.controller;

import static br.com.caelum.vraptor.view.Results.http;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

import com.danielfariati.comente_sobre.annotation.MustBeLogged;
import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.CommentRepository;
import com.danielfariati.comente_sobre.repository.TopicRepository;

public class CommentControllerTest {

	private CommentController controller;

	@Spy private Result result = new MockResult();

	@Mock private CommentRepository repository;

	@Mock private TopicRepository topicRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new CommentController(result, repository, topicRepository);
	}

	@Test
	public void shouldCallMethodSave() {
		Topic topic = Mockito.mock(Topic.class);

		Comment comment = new Comment();
		comment.setTopic(topic);

		Mockito.when(topicRepository.loadById(comment.getTopic().getId())).thenReturn(topic);

		controller.save(comment);

		verify(repository, times(1)).save(comment);
	}

	@Test
	public void shouldRedirectToTopicListAfterSave() {
		Topic topic = Mockito.mock(Topic.class);

		Comment comment = new Comment();
		comment.setTopic(topic);

		Mockito.when(topicRepository.loadById(comment.getTopic().getId())).thenReturn(topic);

		controller.save(comment);

		verify(result).redirectTo(TopicController.class);
	}

	@Test
	public void shouldHaveMustBeLoggedAnnotationInMethodSave() throws NoSuchMethodException, SecurityException {
		Class<? extends CommentController> clazz = controller.getClass();
		Method method = clazz.getMethod("save", Comment.class);

		MustBeLogged annotation = method.getAnnotation(MustBeLogged.class);

		assertNotNull(annotation);
		assertTrue(method.isAnnotationPresent(MustBeLogged.class));		
	}

	@Test
	public void shouldHaveMustBeLoggedAnnotationInMethodRemove() throws NoSuchMethodException, SecurityException {
		Class<? extends CommentController> clazz = controller.getClass();
		Method method = clazz.getMethod("remove", Comment.class);

		MustBeLogged annotation = method.getAnnotation(MustBeLogged.class);

		assertNotNull(annotation);
		assertTrue(method.isAnnotationPresent(MustBeLogged.class));		
	}

	@Test
	public void shouldRemoveComment() {
		Comment comment = Mockito.mock(Comment.class);

		controller.remove(comment);

		verify(repository, times(1)).remove(comment);
		verify(result, times(1)).use(http());
	}

}
