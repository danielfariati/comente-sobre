package com.danielfariati.comente_sobre.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.danielfariati.comente_sobre.model.common.GenericEntity;

@Entity
public class Topic extends GenericEntity {

	private static final long serialVersionUID = 178312327352747377L;

	public static final String prefixURL = "http://www.comentesobre.com";

	@NotNull
	@Column(unique = true)
	private String subject;

	@NotNull
	@Column(unique = true)
	private String subjectURL;

	@OneToMany(mappedBy="topic", cascade=CascadeType.ALL)
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
