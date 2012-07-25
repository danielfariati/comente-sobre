package com.danielfariati.comente_sobre.business;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.business.common.GenericBusiness;
import com.danielfariati.comente_sobre.controller.TopicController;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.TopicRepository;
import com.danielfariati.comente_sobre.utils.Utils;

@Component
public class TopicBusiness extends GenericBusiness<Topic> implements TopicRepository {

	public TopicBusiness(EntityManager manager, Validator validator) {
		super(manager, validator);
	}

	public Topic loadBySubjectURL(String subjectURL) {
		try {
			Query query = manager.createQuery("from " + Topic.class.getName() + " e where e.subjectURL = :subjectURL");
			query.setParameter("subjectURL", subjectURL);

			return (Topic) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public Topic save(Topic topic) {
		validateTopic(topic);

		return super.save(topic);
	}

	private void validateTopic(Topic topic) {
		if (topic.getSubject() == null || topic.getSubject().isEmpty()) {
			validator.add(new ValidationMessage("O campo assunto deve ser preenchido!", "topic.subject"));
		} else if (topic.getId() == null) {
			checkSubjectUniqueness(topic.getSubject());			
		}

		if (topic.getSubjectURL() == null || topic.getSubjectURL().isEmpty()) {
			validator.add(new ValidationMessage("O campo URL deve ser preenchido!", "topic.subjectURL"));
		} else if (Utils.validateUrl(topic.getSubjectURL())) {
			if (topic.getId() == null) {
				checkSubjectURLUniqueness(topic.getSubjectURL());			
			}
		} else {
			validator.add(new ValidationMessage("A URL informada não é válida!", "topic.subjectURL"));
		}

		validator.onErrorForwardTo(TopicController.class).add(topic);		
	}

	private void checkSubjectUniqueness(String subject) {
		Query query = manager.createQuery("from " + Topic.class.getName() + " e where e.subject = :subject");
		query.setParameter("subject", subject);

		try { 
			query.getSingleResult();
			validator.add(new ValidationMessage("O assunto informado já existe!", "topic.subject"));
		} catch(NoResultException e) {
			// do nothing
		}
	}

	private void checkSubjectURLUniqueness(String subjectURL) {
		Query query = manager.createQuery("from " + Topic.class.getName() + " e where e.subjectURL = :subjectURL");
		query.setParameter("subjectURL", subjectURL);

		try { 
			query.getSingleResult();
			validator.add(new ValidationMessage("A URL informada já existe!", "topic.subjectURL"));
		} catch(NoResultException e) {
			// do nothing
		}
	}

}
