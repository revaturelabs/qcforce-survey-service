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
 * Main configuration class for connecting to MongoDB database.
 * and enabling multi-document Transactions.
 * 
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin 
 */
@Configuration
@PropertySource("classpath:application.yml")
public class MongoConfig extends AbstractMongoConfiguration {
	
	/**
	 * The URI associated with the MongoDB database being used.
	 */
	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;
	/**
	 * The MongoDB database name
	 */
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabase;
	
	/**
	 * An instance of a {@link MongoConverter}
	 */
	@Autowired
	private MongoConverter mongoConverter;

	/**
	 * Instantiates a {@link GridFsTemplate} bean that stores content into a 
	 * MongoDB {@link GridFS}.
	 * @param dbFactory a {@link MongoDbFactory} instance
	 * @return A {@link GridFsTemplate}
	 */
	@Bean
	GridFsTemplate gridFsTemplate(MongoDbFactory dbFactory) {
		
		return new GridFsTemplate(dbFactory, mongoConverter);
	}

	/**
	 * Creates a {@link MongoTransactionManager} bean.
	 * @param dbFactory a {@link MongoDbFactory} instance
	 * @return dbFactory an instance of MongoDbFactory
	 */
	@Bean
	MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	/**
	 *Creates an internal connection pooling.
	 *@return uri a MongoClient instance described by a URI
	 */
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(mongoUri);
		return new MongoClient(uri);
	}

	/**
	 *Gets the MongoDB database name
	 *return database the name of the MongoDB database to connect to
	 */
	protected String getDatabaseName() {
		String dataBase = mongoDatabase;
		return dataBase;
	}

}