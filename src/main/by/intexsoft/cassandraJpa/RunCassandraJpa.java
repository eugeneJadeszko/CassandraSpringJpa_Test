package main.by.intexsoft.cassandraJpa;

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
		context.getBean(UserFactory.class).userCreate(3L);
		context.getBean(FileManager.class).SaveAndMove();
		service.findAll().forEach((User entity) -> showUserInfo(entity));
		context.close();
	}

	private static void showUserInfo(User user) {
		System.out.println("id: " + user.getId() + ", first name: " + user.getFirstname() + ", last name: "
				+ user.getLastname() + ", address:");
		System.out.println("\t" + user.getAddress());
		System.out.println();
	}
}
