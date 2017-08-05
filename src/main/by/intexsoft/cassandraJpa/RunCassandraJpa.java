package main.by.intexsoft.cassandraJpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import main.by.intexsoft.cassandraJpa.model.User;
import main.by.intexsoft.cassandraJpa.service.CassandraService;

public class RunCassandraJpa {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				"main.by.intexsoft.cassandraJpa.config");
		CassandraService service = context.getBean(CassandraService.class);
		service.findAll()
				.forEach((User entity) -> System.out.println(entity.getFirst_name() + " " + entity.getLast_name()));
		context.close();
	}

}
