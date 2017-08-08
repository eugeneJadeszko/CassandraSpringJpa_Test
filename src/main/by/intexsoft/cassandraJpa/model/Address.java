package main.by.intexsoft.cassandraJpa.model;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import com.datastax.driver.core.DataType.Name;

@UserDefinedType("address")
public class Address {
	@CassandraType(type = Name.TEXT)
	private String street;
	@CassandraType(type = Name.TEXT)
	private String city;
	@CassandraType(type = Name.INT)
	private int home_number;

	public Address() {
	}

	public Address(String street, String city, int homeNumber) {
		this.street = street;
		this.city = city;
		this.home_number = homeNumber;
	}

	public String toString() {
		return "city: " + this.getCity() + ", street; " + this.getStreet() + ", home number: " + this.getHomeNumber();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getHomeNumber() {
		return home_number;
	}

	public void setHomeNumber(int homeNumber) {
		this.home_number = homeNumber;
	}
}