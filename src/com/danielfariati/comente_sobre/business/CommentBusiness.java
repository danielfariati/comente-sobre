package com.danielfariati.comente_sobre.business;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.business.common.GenericBusiness;
import com.danielfariati.comente_sobre.controller.CommentController;
import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.CommentRepository;
import com.danielfariati.comente_sobre.utils.Utils;

@Component
public class CommentBusiness extends GenericBusiness<Comment> implements CommentRepository {

	public CommentBusiness(EntityManager manager, Validator validator) {
		super(manager, validator);
	}

	@Override
	public Comment save(Comment comment) {
		validateComment(comment);

		return manager.merge(comment);
	}

	public Collection<Comment> loadByTopic(Topic topic) {
		Query query = manager.createQuery("from " + Comment.class.getName() + " c where c.topic.subject = :subject");
		query.setParameter("subject", topic.getSubject());

		@SuppressWarnings("unchecked")
		Collection<Comment> commentList = query.getResultList();

		return commentList;
	}

	private void validateComment(Comment comment) {
		if (comment.getEmail() == null || comment.getEmail().isEmpty()) {
			validator.add(new ValidationMessage("O campo e-mail deve ser preenchido!", "comment.email"));
		} else if (!Utils.validateEmail(comment.getEmail())) {
			validator.add(new ValidationMessage("Por favor, informe um e-mail válido.", "comment.email"));
		}

		if (comment.getMessage() == null || comment.getMessage().isEmpty()) {
			validator.add(new ValidationMessage("O campo mensagem deve ser preenchido!", "comment.message"));
		}

		if (comment.getTopic() == null || comment.getTopic().getId() == null) {
			validator.add(new ValidationMessage("O campo tópico deve ser preenchido!", "comment.topic.id"));
		}

		validator.onErrorForwardTo(CommentController.class).add(comment);
	}

}
