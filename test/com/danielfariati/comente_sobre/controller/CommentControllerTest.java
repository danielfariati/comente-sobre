package com.danielfariati.comente_sobre.controller;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

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
	public void shouldIncludeTopicInResult() {
		Topic topic = Mockito.mock(Topic.class);

		Mockito.when(topicRepository.loadById(topic.getId())).thenReturn(topic);

		controller.add(topic);

		verify(result).include("topic", topic);
	}

	@Test
	public void shouldCallMethodSave() {
		Comment comment = Mockito.mock(Comment.class);

		controller.save(comment);

		verify(repository).save(comment);
	}

	@Test
	public void shouldForwardToTopicListAfterSave() {
		Comment comment = Mockito.mock(Comment.class);

		controller.save(comment);

		verify(result).forwardTo(TopicController.class);
	}

}
