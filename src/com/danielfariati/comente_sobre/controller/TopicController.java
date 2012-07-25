package com.danielfariati.comente_sobre.controller;


import java.util.Collection;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.danielfariati.comente_sobre.annotation.MustBeLogged;
import com.danielfariati.comente_sobre.model.Comment;
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

	@Get("/topic/list")
	public void list() {
		Collection<Topic> topicList = repository.loadAll();

		result.include("topicList", topicList);
	}

	@Get("/{topic.subjectURL}")
	public void search(Topic topic, Comment comment) {
		topic = repository.loadBySubjectURL(topic.getSubjectURL());

		result
		.include("comment", comment)
		.include("topic", topic);
	}

	@MustBeLogged
	@Get("/topic/new")
	public void add(Topic topic) {
		result
		.include("topic", topic)
		.include("prefixURL", Topic.prefixURL);
	}

	@MustBeLogged
	@Post({"/topic", "/topic/new"})
	public void save(Topic topic) {
		topic = repository.save(topic);

		result.redirectTo(TopicController.class).search(topic, null);
	}

}
