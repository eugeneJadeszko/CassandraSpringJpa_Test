package main.by.intexsoft.cassandraJpa.model;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.datastax.driver.core.DataType.Name;

@Table(value = "user")
public class User {
	@PrimaryKey("id")
	private Long id;
	@Column("first_name")
	private String firstname;
	@Column(value = "last_name")
	private String lastname;
	@Column("addr")
	@CassandraType(userTypeName = "address", type = Name.UDT)
	private Address address;

	public User() {
	}

	public User(Long id, String firstname, String lastname, Address address) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
