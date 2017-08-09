package main.by.intexsoft.cassandraJpa.service;

import org.springframework.stereotype.Service;

import main.by.intexsoft.cassandraJpa.model.Address;

@Service
public class AddressFactory {

	/**
	 * This method creates object of type Address
	 * 
	 * @param home_number
	 *            - home number
	 * @return object of type Address
	 */
	public Address oneAddressCreate(int home_number) {
		return new Address("E. Broadway", "Tucson", home_number);
	}
}
