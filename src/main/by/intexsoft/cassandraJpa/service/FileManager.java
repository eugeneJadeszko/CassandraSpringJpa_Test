package main.by.intexsoft.cassandraJpa.service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.by.intexsoft.cassandraJpa.model.User;
import main.by.intexsoft.cassandraJpa.utility.Utility;
import main.by.intexsoft.cassandraJpa.utility.impl.FileUtilityImpl;

@Service
@PropertySource(value = "classpath:application.properties")
public class FileManager {
	@Value("${directory.incoming}")
	private String incomingDir;

	@Value("${directory.read}")
	private String readDir;

	@Value("${directory.invalid}")
	private String invalidDir;

	@Autowired
	private CassandraService service;
	private Utility utility = new FileUtilityImpl();
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * This method saves objects from files to the Cassandra database and moves
	 * files to another directory
	 */
	public void SaveAndMove() {
		for (File item : getFiles(incomingDir)) {
			try {
				if (utility.isValidJSON(item)) {
					service.addUser(mapper.readValue(item, User.class));
					utility.move(item.getParent(), item.getName(), readDir);
				} else {
					utility.move(item.getParent(), item.getName(), invalidDir);
				}
			} catch (IOException e) {
				System.out.println("i/o exception");
			}
		}
	}

	/**
	 * This method return a collection of source files from the directory
	 * 
	 * @return Set<User>
	 */
	public Set<User> getSourceSet() throws IOException, JsonMappingException {
		Set<User> sourceSet = new HashSet<>();
		for (File item : getReadFiles()) {
			User user = mapper.readValue(item, User.class);
			sourceSet.add(user);
		}
		return sourceSet;
	}

	/**
	 * @return array of files read messages
	 */
	public File[] getReadFiles() {
		return getFiles(readDir);
	}

	/**
	 * @return array of invalid files
	 */
	public File[] getInvalidFiles() {
		return getFiles(invalidDir);
	}

	/**
	 * @param dirPath
	 *            - path to source directory
	 * @return array of files
	 */
	private File[] getFiles(String dirPath) {
		return new File(dirPath).listFiles();
	}
}
