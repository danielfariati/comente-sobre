package com.danielfariati.comente_sobre.repository;

import java.util.Collection;

import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Subject;

public interface CommentRepository {

	public void save(Comment comment);

	public Collection<Comment> loadBySubject(Subject subject);

}
