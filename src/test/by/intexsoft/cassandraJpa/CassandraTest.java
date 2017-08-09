package test.by.intexsoft.cassandraJpa;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonMappingException;

import main.by.intexsoft.cassandraJpa.model.User;
import main.by.intexsoft.cassandraJpa.service.CassandraService;
import main.by.intexsoft.cassandraJpa.service.FileManager;
import main.by.intexsoft.cassandraJpa.service.UserFactory;

public class CassandraTest {
	AnnotationConfigApplicationContext context;
	private Set<User> sourceSet;
	private Set<User> cassandraSet;

	/**
	 * This method compares objects from Cassandra with source objects from
	 * directory and checks directory with invalid files
	 */
	@Test
	public void Test() {
		Assert.assertNotEquals(sourceSet, cassandraSet);
		assertTrue(context.getBean(FileManager.class).getInvalidFiles().length == 0);
	}

	@BeforeClass
	public void Config() throws JsonMappingException, BeansException, IOException {
		context = new AnnotationConfigApplicationContext("main.by.intexsoft.cassandraJpa.config");
		context.getBean(UserFactory.class).userCreate(10L);
		context.getBean(FileManager.class).SaveAndMove();
		cassandraSet = context.getBean(CassandraService.class).getSetFromCassandra();
		sourceSet = context.getBean(FileManager.class).getSourceSet();
	}

	/**
	 * This method clears the database and directory after the test
	 */
	@AfterMethod
	private void clear() {
		context.getBean(CassandraService.class).deleteAll();
		for (File item : context.getBean(FileManager.class).getReadFiles()) {
			item.delete();
		}
		context.close();
	}
}
