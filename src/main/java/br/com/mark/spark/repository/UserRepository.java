package br.com.mark.spark.repository;

import java.util.List;

import br.com.mark.spark.model.User;

public interface UserRepository {

	User findById(Long id);

	List<User> findAll();

	void save(User user);

	void delete(User user);
}
