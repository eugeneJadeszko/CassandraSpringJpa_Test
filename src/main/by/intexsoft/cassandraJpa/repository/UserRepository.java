package main.by.intexsoft.cassandraJpa.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import main.by.intexsoft.cassandraJpa.model.User;

@Repository
public interface UserRepository extends CassandraRepository<User> {
	/**
	 * This method returns users where column first_name = first_name
	 * 
	 * @param first_name
	 *            - users first name
	 * @return List<User>
	 */
	List<User> findByFirstname(String first_name);

	/**
	 * This method returns users where column last_name = last_name. Warning: this
	 * column in database must have index
	 * 
	 * @param last_name
	 *            - users last name
	 * @return List<User>
	 */
	List<User> findByLastname(String last_name);
}
