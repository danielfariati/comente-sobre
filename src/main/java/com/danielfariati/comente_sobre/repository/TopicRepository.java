package com.danielfariati.comente_sobre.repository;

import com.danielfariati.comente_sobre.model.Topic;
import com.danielfariati.comente_sobre.repository.common.GenericRepository;

public interface TopicRepository extends GenericRepository<Topic> {

	Topic loadBySubjectURL(String subjectURL);

}
