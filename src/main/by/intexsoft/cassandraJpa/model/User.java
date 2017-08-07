package main.by.intexsoft.cassandraJpa.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "user")
public class User {
	// @PrimaryKeyColumn(name = "first_name", type = PrimaryKeyType.PARTITIONED)
	@PrimaryKey("first_name")
	private String firstname;
	@Column(value = "last_name")
	private String lastname;

	public User() {
	}

	public User(String first_name, String last_name) {
		this.firstname = first_name;
		this.lastname = last_name;
	}

	public String getFirst_name() {
		return firstname;
	}

	public void setFirst_name(String first_name) {
		this.firstname = first_name;
	}

	public String getLast_name() {
		return lastname;
	}

	public void setLast_name(String last_name) {
		this.lastname = last_name;
	}
}
