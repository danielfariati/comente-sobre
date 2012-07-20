package com.danielfariati.comente_sobre.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
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

	@Get({"/comment/{topic.id}", "/comment/new/{topic.id}"})
	public void add(Topic topic) {
		topic = topicRepository.loadById(topic.getId());

		result.include("topic", topic);
	}

	@Post
	@Path("/comment")
	public void save(Comment comment) {
		repository.save(comment);

		result.forwardTo(TopicController.class).search(comment.getTopic());
	}

}
