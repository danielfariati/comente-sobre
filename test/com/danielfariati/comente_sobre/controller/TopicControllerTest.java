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

import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.TopicRepository;

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
	public void shouldIncludePrefixURLOnAddPage() {
		String prefixURL = Topic.prefixURL;

		controller.add(null);

		verify(result).include("prefixURL", prefixURL);
	}

	@Test
	public void shouldRedirectToTopicAfterSaving() {
		Topic topic = new Topic();

		controller.save(topic);

		verify(repository).save(topic);
		verify(result).redirectTo(TopicController.class);
	}

}
