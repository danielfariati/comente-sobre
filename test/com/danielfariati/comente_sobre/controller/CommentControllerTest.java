package com.danielfariati.comente_sobre.controller;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Subject;
import com.danielfariati.comente_sobre.repository.CommentRepository;

public class CommentControllerTest {

	private CommentController controller;

	@Spy private Result result = new MockResult();

	@Mock private  CommentRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new CommentController(result, repository);
	}

	@Test
	public void shouldIncludeSubjectInResult() {
		Comment comment = Mockito.mock(Comment.class);

		controller.comment(comment);

		verify(result).include("subject", comment.getSubject());
	}

	@Test
	public void shouldCallMethodSave() {
		Comment comment = Mockito.mock(Comment.class);

		controller.save(comment);

		verify(repository).save(comment);
	}

	@Test
	public void shouldCallMethodListBySubject() {
		Subject subject = Mockito.mock(Subject.class);

		controller.list(subject);

		verify(repository).loadBySubject(subject);
	}

	@Test
	public void shouldIncludeSubjectOnResultWhenListingComments() {
		Subject subject = Mockito.mock(Subject.class);

		controller.list(subject);

		verify(result).include("subject", subject);
	}

	@Test
	public void shouldIncludeCommentListOnResultWhenListingComments() {
		Subject subject = Mockito.mock(Subject.class);

		Collection<Comment> commentList = new ArrayList<Comment>();

		Mockito.when(repository.loadBySubject(subject)).thenReturn(commentList);

		controller.list(subject);

		verify(result).include("commentList", commentList);
	}

	@Test
	public void shouldForwardAfterSave() {
		Comment comment = Mockito.mock(Comment.class);

		controller.save(comment);

		verify(result).forwardTo(CommentController.class);
	}

}
