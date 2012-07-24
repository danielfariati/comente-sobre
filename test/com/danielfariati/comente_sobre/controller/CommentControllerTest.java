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
	public void shouldIncludeCommentInResult() {
		Comment comment = Mockito.mock(Comment.class);
		Topic topic = Mockito.mock(Topic.class);

		controller.add(comment, topic);

		verify(topicRepository, times(1)).loadById(topic.getId());
		verify(result).include("comment", comment);
	}

	@Test
	public void shouldCallMethodSave() {
		Comment comment = Mockito.mock(Comment.class);

		controller.save(comment);

		verify(repository, times(1)).save(comment);
	}

	@Test
	public void shouldRedirectToTopicListAfterSave() {
		Comment comment = Mockito.mock(Comment.class);

		controller.save(comment);

		verify(result).redirectTo(TopicController.class);
	}

}
