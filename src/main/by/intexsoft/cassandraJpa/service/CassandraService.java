package main.by.intexsoft.cassandraJpa.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.by.intexsoft.cassandraJpa.model.User;
import main.by.intexsoft.cassandraJpa.repository.UserRepository;

@Service
public class CassandraService {
	@Autowired
	private UserRepository repository;

	/**
	 * Returns all instances of the type User.
	 * 
	 * @return List<User>
	 */
	public List<User> findAll() {
		return (List<User>) repository.findAll();
	}

	/**
	 * Saves a given entity into Cassandra database
	 * 
	 * @param entity
	 */
	public void addUser(User entity) {
		repository.save(entity);
	}

	/**
	 * Deletes a given entity from Cassandra database
	 * 
	 * @param entity
	 */
	public void deleteUser(User entity) {
		repository.delete(entity);
	}

	/**
	 * Deletes all entities managed by the Cassandra database
	 */
	public void deleteAll() {
		repository.deleteAll();
	}

	/**
	 * This method returns users where column first_name = first_name
	 * 
	 * @param first_name
	 *            - users first name
	 * @return List<User>
	 */
	public List<User> findByFirstname(String first_name) {
		return repository.findByFirstname(first_name);
	}

	/**
	 * This method returns users where column last_name = last_name. Warning: this
	 * column in database must have index
	 * 
	 * @param last_name
	 *            - users last name
	 * @return List<User>
	 */
	public List<User> findByLastname(String last_name) {
		return repository.findByLastname(last_name);
	}

	/**
	 * This method return set objects from cassandra database.
	 * 
	 * @return Set<User>
	 */
	public Set<User> getSetFromCassandra() {
		Set<User> cassandraSet = new HashSet<>();
		cassandraSet.addAll(findAll());
		return cassandraSet;
	}
}
