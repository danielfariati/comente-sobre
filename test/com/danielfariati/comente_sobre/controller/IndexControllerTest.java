package com.danielfariati.comente_sobre.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.TopicRepository;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

import static org.mockito.Mockito.verify;

public class IndexControllerTest {

	@Spy private Result result = new MockResult();

	@Mock private TopicRepository repository;

	private IndexController controller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(result, repository);
	}

	@Test
	public void shouldListExistingTopics() {
		Collection<Topic> topicList = new ArrayList<Topic>();

		Mockito.when(repository.loadAll()).thenReturn(topicList);

		controller.index();

		verify(repository).loadAll();
		verify(result).include("topicList", topicList);
	}

}
