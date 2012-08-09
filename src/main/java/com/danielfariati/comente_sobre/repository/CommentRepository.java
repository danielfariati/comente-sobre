package com.danielfariati.comente_sobre.repository;

import java.util.Collection;

import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.common.GenericRepository;

public interface CommentRepository extends GenericRepository<Comment>{

	Collection<Comment> loadByTopic(Topic topic);

}
