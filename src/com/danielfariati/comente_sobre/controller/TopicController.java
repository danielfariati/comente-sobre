package com.danielfariati.comente_sobre.controller;

import java.util.Collection;

import javax.annotation.Resource;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.TopicRepository;

@Resource
public class TopicController {

	private final Result result;
	private final TopicRepository repository;

	public TopicController(Result result, TopicRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	// TODO verify if needed (index do the same)
	@Get("/topic")
	public void list() {
		Collection<Topic> topicList = repository.loadAll();

		result.include("topicList", topicList);
	}

	@Get("/topic/{topic.subjectURL}")
	public void search(Topic topic) {
		topic = repository.loadBySubjectURL(topic.getSubjectURL());

		result.include("topic", topic);
	}

	@Get("/topic/new")
	public void add(Topic topic) {
		result.include("topic", topic);
	}

	@Post({"/topic", "/topic/new"})
	public void save(Topic topic) {
		topic = repository.save(topic);

		result.redirectTo(TopicController.class).search(topic);
	}

}
