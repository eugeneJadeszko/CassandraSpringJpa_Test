package main.by.intexsoft.cassandraJpa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.by.intexsoft.cassandraJpa.model.User;
import main.by.intexsoft.cassandraJpa.utility.Utility;
import main.by.intexsoft.cassandraJpa.utility.impl.FileUtilityImpl;

@Service
@PropertySource(value = "classpath:application.properties")
public class UserFactory {
	@Value("${directory.incoming}")
	private String incomingDir;

	private Utility utility = new FileUtilityImpl();
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * This method creates objects of type User
	 * 
	 * @param quantity
	 *            - Number of objects to create
	 */
	public void userCreate(int quantity) {
		for (int counter = 0; counter < quantity; counter++) {
			saveUserInFile(new User("first_name" + counter, "last_name" + counter), counter);
		}
	}

	/**
	 * This method saves object of type User into json file
	 * 
	 * @param user
	 *            - object of type User
	 * @param counter
	 *            - file number
	 */
	private void saveUserInFile(User user, int counter) {
		try {
			utility.write(incomingDir, "user" + counter + ".json", mapper.writeValueAsString(user), false);
		} catch (JsonProcessingException e) {
			System.out.println("Write error");
		}
	}
}
