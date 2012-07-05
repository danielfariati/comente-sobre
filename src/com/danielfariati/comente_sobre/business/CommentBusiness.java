package com.danielfariati.comente_sobre.business;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.controller.CommentController;
import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Subject;
import com.danielfariati.comente_sobre.repository.CommentRepository;

@Component
public class CommentBusiness implements CommentRepository {

    private Validator validator;

	protected final EntityManager manager;

	public CommentBusiness(EntityManager manager, Validator validator) {
		this.manager = manager;
		this.validator = validator;
	}

	public void save(Comment comment) {
		validateComment(comment);

		manager.merge(comment);
	}

	public Collection<Comment> loadBySubject(Subject subject) {
		try {
			Query query = manager.createQuery("from " + Comment.class.getName() + " c where c.subject.name = :subject");
			query.setParameter("subject", subject.getName());

			@SuppressWarnings("unchecked")
			Collection<Comment> commentList = query.getResultList();

			return commentList;
		} catch(NoResultException e) {
			return null;
		}
	}

	private void validateComment(Comment comment) {
		if (comment.getEmail() == null || comment.getEmail().isEmpty()) {
			validator.add(new ValidationMessage("O campo e-mail deve ser preenchido!", "comment.email"));
		}

		if (comment.getMessage() == null || comment.getMessage().isEmpty()) {
			validator.add(new ValidationMessage("O campo mensagem deve ser preenchido!", "comment.message"));
		}

		validator.onErrorForwardTo(CommentController.class).comment(comment);
	}

}
