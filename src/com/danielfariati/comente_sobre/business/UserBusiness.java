package com.danielfariati.comente_sobre.business;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.business.common.GenericBusiness;
import com.danielfariati.comente_sobre.controller.UserController;
import com.danielfariati.comente_sobre.model.User;
import com.danielfariati.comente_sobre.repository.UserRepository;
import com.danielfariati.comente_sobre.session.UserSession;
import com.danielfariati.comente_sobre.utils.Utils;

@Component
public class UserBusiness extends GenericBusiness<User> implements UserRepository {

	private UserSession session;

	protected UserBusiness(EntityManager manager, Validator validator, UserSession session) {
		super(manager, validator);

		this.session = session;
	}

	@Override
	public User save(User user) {
		validateUser(user);

		return super.save(user);
	}

	public void login(User user) {
		Query query = manager.createQuery("from " + User.class.getName() + " e where e.email = :email and e.password = :password");
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());

		try {
			User loggedUser = (User) query.getSingleResult();
			session.setUser(loggedUser);
		} catch (NoResultException e) {
			validator.add(new ValidationMessage("E-mail e/ou Senha inválidos!", "user"));
			validator.onErrorForwardTo(UserController.class).loginPage(user, null);
		}
	}

	public void logout() {
		session.setUser(null);
	}

	private void validateUser(User user) {
		if (user.getName() == null || user.getName().isEmpty()) {
			validator.add(new ValidationMessage("O campo nome deve ser preenchido!", "user.name"));
		}

		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			validator.add(new ValidationMessage("O campo e-mail deve ser preenchido!", "user.email"));
		} else if (!Utils.validateEmail(user.getEmail())) {
			validator.add(new ValidationMessage("Por favor, informe um e-mail válido.", "user.email"));
		}

		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			validator.add(new ValidationMessage("O campo senha deve ser preenchido!", "user.password"));
		}

		validator.onErrorForwardTo(UserController.class).loginPage(null, user);
	}

}
