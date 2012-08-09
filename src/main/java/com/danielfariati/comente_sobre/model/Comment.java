package com.danielfariati.comente_sobre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.danielfariati.comente_sobre.model.common.GenericEntity;

@Entity
public class Comment extends GenericEntity {

	private static final long serialVersionUID = -951123461336762520L;

	public static final int MESSAGE_MAX_SIZE = 2000;

	@Email
	@NotNull
	@NotEmpty
	@Column
	private String email;

	@NotNull
	@NotEmpty
	@Column(length = MESSAGE_MAX_SIZE)
	private String message;

	@ManyToOne
	@JoinColumn(name = "topic")
	private Topic topic;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

}
