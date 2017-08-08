package main.by.intexsoft.cassandraJpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "main.by.intexsoft.cassandraJpa.repository")
@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = "main.by.intexsoft.cassandraJpa.service")
public class CassandraConfig extends AbstractCassandraConfiguration {
	@Value("${ipAddres}")
	private String host;

	@Value("${port}")
	private int port;

	@Value("${keyspace}")
	private String keyspace;

	@Override
	protected String getKeyspaceName() {
		return keyspace;
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(host);
		cluster.setPort(port);
		return cluster;
	}

/*	@Bean
	public CassandraMappingContext mappingContext() throws ClassNotFoundException {
		BasicCassandraMappingContext mappingContext = new BasicCassandraMappingContext();
		mappingContext.setInitialEntitySet(CassandraEntityClassScanner.scan("by.intexsoft.cassandraJpa.model"));
		mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), getKeyspaceName()));
		return mappingContext;
	}

	@Bean
	public CassandraConverter converter() throws ClassNotFoundException {
		return new MappingCassandraConverter(mappingContext());
	}

	@Bean
	public CassandraSessionFactoryBean session() throws ClassNotFoundException {
		CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
		session.setCluster(cluster().getObject());
		session.setKeyspaceName(getKeyspaceName());
		session.setConverter(converter());
		session.setSchemaAction(SchemaAction.RECREATE);
		return session;
	}*/
}
