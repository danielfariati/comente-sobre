package com.danielfariati.comente_sobre.controller;

import java.util.Collection;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Subject;
import com.danielfariati.comente_sobre.repository.CommentRepository;

@Resource
public class CommentController {

	private final Result result;
	private final CommentRepository repository;

	public CommentController(Result result, CommentRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	@Path("/{comment.subject.name}")
	@Get
	public void comment(Comment comment) {
		result.include("subject", comment.getSubject());
	}

	@Path("/{comment.subject.name}/save")
	@Post
	public void save(Comment comment) {
		repository.save(comment);

		result.forwardTo(this).list(comment.getSubject());
	}

	@Path("/{subject.name}/list")
	@Get
	public void list(Subject subject) {
		Collection<Comment> commentList = repository.loadBySubject(subject);
		result
		.include("subject", subject)
		.include("commentList", commentList);
	}

}
