package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MyMongoDb {
    static final String uri = "mongodb+srv://admin:admin@cluster0.y0cnr.mongodb.net/mongodb?retryWrites=true&w=majority";
    private MongoClientURI mongoClientURI;
    private String databaseName;
    private MongoClient mongoClient;
    private static MongoDatabase database;

    MyMongoDb(String databaseName){
        mongoClientURI = new MongoClientURI(uri);
        mongoClient = new MongoClient(mongoClientURI);
        this.databaseName = databaseName;
        database = mongoClient.getDatabase(databaseName);
    }

    static MongoDatabase getMongoDb(String databaseName){
        if(database == null){
            MyMongoDb myMongoDb = new MyMongoDb(databaseName);
        }
        return database;
    }
}
