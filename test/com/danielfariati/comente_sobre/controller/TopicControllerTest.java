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

import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.TopicRepository;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class TopicControllerTest {

	@Spy private Result result = new MockResult();

	@Mock private TopicRepository repository;

	private TopicController controller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		controller = new TopicController(result, repository);
	}

	@Test
	public void shouldListExistingTopics() {
		Collection<Topic> topicList = new ArrayList<Topic>();

		Mockito.when(repository.loadAll()).thenReturn(topicList);

		controller.list();

		verify(repository).loadAll();
		verify(result).include("topicList", topicList);
	}

	@Test
	public void shouldIncludeTopicOnSearchBySubjectURL() {
		Topic topic = new Topic();
		topic.setSubjectURL("url");

		Mockito.when(repository.loadBySubjectURL(topic.getSubjectURL())).thenReturn(topic);

		controller.search(topic);

		verify(repository).loadBySubjectURL(topic.getSubjectURL());
		verify(result).include("topic", topic);
	}

	@Test
	public void shouldIncludeTopicOnAddPage() {
		Topic topic = new Topic();

		controller.add(topic);

		verify(result).include("topic", topic);
	}

	@Test
	public void shouldRedirectToTopicAfterSaving() {
		Topic topic = new Topic();

		controller.save(topic);

		verify(repository).save(topic);
		verify(result).redirectTo(TopicController.class);
	}

}
