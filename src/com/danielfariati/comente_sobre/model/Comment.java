package com.danielfariati.comente_sobre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.danielfariati.comente_sobre.model.common.GenericEntity;

@Entity
public class Comment extends GenericEntity {

	private static final long serialVersionUID = -951123461336762520L;

	@NotNull
	@NotEmpty
	@Column
	private String message;

	@ManyToOne
	@JoinColumn(name = "topic")
	private Topic topic;

	@ManyToOne
	@JoinColumn(name = "commenter")
	private User user;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
