package com.danielfariati.comente_sobre;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

import com.danielfariati.comente_sobre.controller.CommentController;

public class CommentControllerTest {

	private CommentController controller;

	@Spy private Result result = new MockResult();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new CommentController(result);
	}

	@Test
	public void shouldIncludeSubjectInResult() {
		String subject = "subject-test";

		controller.comment(subject);

		verify(result).include("subject", subject);
	}
}
