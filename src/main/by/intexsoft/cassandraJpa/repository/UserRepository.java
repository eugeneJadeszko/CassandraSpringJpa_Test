package main.by.intexsoft.cassandraJpa.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import main.by.intexsoft.cassandraJpa.model.User;

@Repository
public interface UserRepository extends CassandraRepository<User> {

}
