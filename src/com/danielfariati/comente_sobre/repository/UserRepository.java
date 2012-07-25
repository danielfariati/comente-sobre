package com.danielfariati.comente_sobre.repository;

import com.danielfariati.comente_sobre.model.User;
import com.danielfariati.comente_sobre.repository.common.GenericRepository;

public interface UserRepository extends GenericRepository<User> {

	void login(User user);

	void logout();

}
