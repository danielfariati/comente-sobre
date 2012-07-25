package com.danielfariati.comente_sobre.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.danielfariati.comente_sobre.model.User;

@Component
@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 341123310877235121L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogged() {
		if(user == null){
			return false;
		}

        return true;
	}
}
