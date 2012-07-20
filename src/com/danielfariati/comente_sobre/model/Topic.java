package com.danielfariati.comente_sobre.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.danielfariati.comente_sobre.model.common.GenericEntity;
import com.sun.istack.internal.NotNull;

@Entity
public class Topic extends GenericEntity {

	private static final long serialVersionUID = 178312327352747377L;

	@NotNull
	@Column(unique = true)
	private String subject;

	@NotNull
	@Column(unique = true)
	private String subjectURL;

	// TODO Verify if EAGER is needed
	@OneToMany(mappedBy="topic", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<Comment> commentList;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Collection<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(Collection<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getSubjectURL() {
		return subjectURL;
	}

	public void setSubjectURL(String subjectURL) {
		this.subjectURL = subjectURL;
	}

}
