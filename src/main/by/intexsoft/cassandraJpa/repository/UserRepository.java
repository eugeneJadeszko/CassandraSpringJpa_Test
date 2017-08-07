package main.by.intexsoft.cassandraJpa.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import main.by.intexsoft.cassandraJpa.model.User;

@Repository
public interface UserRepository extends CassandraRepository<User> {
	List<User> findByFirstname(String first_name);

	List<User> findByLastname(String last_name);
}
