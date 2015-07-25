package org.mendole.platiniumauto.persistence.repository;

import java.util.Date;
import java.util.List;

import org.mendole.platiniumauto.persistence.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * This method is not implemented and its working code will be automagically
	 * generated from its signature by Spring Data JPA.
	 *
	 * @param email
	 *            the user email.
	 * @return the user having the passed email or null if no user is found.
	 */
	public User findByEmail(String email);

	public User findByName(String name);


} // class UserDao