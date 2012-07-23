package com.danielfariati.comente_sobre.controller;

import java.util.Collection;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.TopicRepository;

@Resource
public class IndexController {

	private Result result;
	private TopicRepository repository;

	public IndexController(Result result, TopicRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	@Path("/")
	public void index() {
		Collection<Topic> topicList = repository.loadAll();

		result.include("topicList", topicList);
	}

}
