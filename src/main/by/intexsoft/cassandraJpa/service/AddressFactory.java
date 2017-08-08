package main.by.intexsoft.cassandraJpa.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import main.by.intexsoft.cassandraJpa.model.Address;

@Service
public class AddressFactory {
	public Map<String, Address> addressCreate(int number) {
		Map<String, Address> addresses = new HashMap<>();
		for (int counter = 0; counter < number; counter++) {
			addresses.put("address" + counter, new Address("E. Broadway", "Tucson", counter));
		}
		return addresses;
	}
	
	public Address oneAddressCreate(int counter) {
		return new Address("E. Broadway", "Tucson", counter);
	}
}
