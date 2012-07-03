package com.danielfariati.comente_sobre.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class CommentController {

	private final Result result;

	public CommentController(Result result) {
		this.result = result;
	}

	@Path("/{subject}")
	public void comment(String subject) {
		result.include("subject", subject);
	}

}
