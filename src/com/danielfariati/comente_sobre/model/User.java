package com.danielfariati.comente_sobre.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.danielfariati.comente_sobre.model.common.GenericEntity;
import com.danielfariati.comente_sobre.utils.Utils;

@Entity
public class User extends GenericEntity {

	private static final long serialVersionUID = -3567549998639455961L;

	@NotNull
	@NotEmpty
	@Column
	private String name;

	@Email
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotNull
	@NotEmpty
	@Column
	private String password;

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private Collection<Comment> commentList;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.isEmpty()) {
			this.password = password;
		} else {
			this.password = Utils.encrypt(password + "comente-sobre");
		}
	}

	public Collection<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(Collection<Comment> commentList) {
		this.commentList = commentList;
	}

}
