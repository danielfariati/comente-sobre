package com.danielfariati.comente_sobre.controller;


import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
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

	@Get("/{topic.subjectURL}")
	public void search(Topic topic) {
		topic = repository.loadBySubjectURL(topic.getSubjectURL());

		result.include("topic", topic);
	}

	@Get("/topic/new")
	public void add(Topic topic) {
		result
		.include("topic", topic)
		.include("prefixURL", Topic.prefixURL);
	}

	@Post({"/topic", "/topic/new"})
	public void save(Topic topic) {
		topic = repository.save(topic);

		result.redirectTo(TopicController.class).search(topic);
	}

}
