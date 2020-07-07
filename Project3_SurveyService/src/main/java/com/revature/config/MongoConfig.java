package com.revature.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Main configuration class for connecting to MongoDB
 * 
 * @author Anastasia Miagkii, Andres Toledo, Jose Canela, Monica Datta, Wei Wu, Zachary Reagin
 */

@Configuration
@PropertySource("classpath:application.yml")
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabase;
	
	@Autowired
	private MongoConverter mongoConverter;

	@Bean
	GridFsTemplate gridFsTemplate(MongoDbFactory dbFactory) {
		
		return new GridFsTemplate(dbFactory, mongoConverter);
	}

	@Bean
	MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	@Override
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(mongoUri);
		return new MongoClient(uri);
	}

	@Override
	protected String getDatabaseName() {
		String dataBase = mongoDatabase;
		return dataBase;
	}

}