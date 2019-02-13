package com.ecommerce.bakerymanagement;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbConnectivity {

	public static void main(String[] args) throws UnknownHostException {

		System.setProperty("java.net.preferIPv4Stack", "true");
		MongoClient mongoClient = new MongoClient(new MongoClientURI(
				"mongodb://master:master_dev001@ds125574.mlab.com:25574/bakery_master?replicaSet=rs-ds012345"));

		List<String> databases = mongoClient.getDatabaseNames();

		for (String dbName : databases) {
			System.out.println("- Database: " + dbName);

			DB db = mongoClient.getDB(dbName);

			Set<String> collections = db.getCollectionNames();
			for (String colName : collections) {
				System.out.println("\t + Collection: " + colName);
			}
		}

		mongoClient.close();

	}
}
