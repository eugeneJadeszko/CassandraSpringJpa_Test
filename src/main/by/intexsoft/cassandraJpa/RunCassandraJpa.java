package main.by.intexsoft.cassandraJpa;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import main.by.intexsoft.cassandraJpa.model.User;
import main.by.intexsoft.cassandraJpa.service.CassandraService;
import main.by.intexsoft.cassandraJpa.service.FileManager;
import main.by.intexsoft.cassandraJpa.service.UserFactory;

public class RunCassandraJpa {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				"main.by.intexsoft.cassandraJpa.config");
		CassandraService service = context.getBean(CassandraService.class);
		context.getBean(UserFactory.class).userCreate(5);
		context.getBean(FileManager.class).SaveAndMove();
		service.findAll()
				.forEach((User entity) -> System.out.println(entity.getFirst_name() + " " + entity.getLast_name()));
		List<User> users = service.findByLastname("last_name1");
		users.forEach((User user) -> System.out.println("\n" + user.getFirst_name() + ", " + user.getLast_name()));
		context.close();
	}
}
