package com.danielfariati.comente_sobre.business;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.annotation.MustBeLogged;
import com.danielfariati.comente_sobre.business.common.GenericBusiness;
import com.danielfariati.comente_sobre.controller.TopicController;
import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.CommentRepository;
import com.danielfariati.comente_sobre.session.UserSession;

@Component
public class CommentBusiness extends GenericBusiness<Comment> implements CommentRepository {

	private UserSession userSession;

	public CommentBusiness(EntityManager manager, Validator validator, UserSession userSession) {
		super(manager, validator);

		this.userSession = userSession;
	}

	@Override
	@MustBeLogged
	public Comment save(Comment comment) {
		comment.setUser(userSession.getUser());

		validateComment(comment);

		return super.save(comment);
	}

	public Collection<Comment> loadByTopic(Topic topic) {
		Query query = manager.createQuery("from " + Comment.class.getName() + " c where c.topic.subject = :subject");
		query.setParameter("subject", topic.getSubject());

		@SuppressWarnings("unchecked")
		Collection<Comment> commentList = query.getResultList();

		return commentList;
	}

	private void validateComment(Comment comment) {
		if (comment.getMessage() == null || comment.getMessage().isEmpty()) {
			validator.add(new ValidationMessage("O campo mensagem deve ser preenchido!", "comment.message"));
		}

		if (comment.getTopic() == null || comment.getTopic().getId() == null) {
			validator.add(new ValidationMessage("O campo tópico deve ser preenchido!", "comment.topic.id"));
		}

		if (comment.getUser() == null || comment.getUser().getId() == null) {
			validator.add(new ValidationMessage("Você precisa estar logado para realizar esta ação!", "comment.user.id"));
		}

		validator.onErrorForwardTo(TopicController.class).search(comment.getTopic(), comment);
	}

}
