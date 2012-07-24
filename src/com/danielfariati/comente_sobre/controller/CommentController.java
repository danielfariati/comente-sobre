package com.danielfariati.comente_sobre.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.CommentRepository;
import com.danielfariati.comente_sobre.repository.TopicRepository;

@Resource
public class CommentController {

	private final Result result;
	private final CommentRepository repository;
	private final TopicRepository topicRepository;

	public CommentController(Result result, CommentRepository repository, TopicRepository topicRepository) {
		this.result = result;
		this.repository = repository;
		this.topicRepository = topicRepository;
	}

	@Get("/comment/{topic.id}")
	public void add(Comment comment, Topic topic) {
		// TODO try/search for new aproach
		topic = topicRepository.loadById(topic.getId());
		comment.setTopic(topic);

		result.include("comment", comment);
	}

	@Post("/comment")
	public void save(Comment comment) {
		repository.save(comment);

		result.redirectTo(TopicController.class).search(comment.getTopic());
	}

}
