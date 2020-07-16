package org.example;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MongoMain {
    static String dbName = "mongodb";

    public static void main(String[] args) {
        //  insertData();
        //  getData();
        // aggregateData(MyMongoDb.getMongoDb("mongodb"));
        //  createCollection();

        //  createDocument();

        // listAllCollection();

        //   dropCollection();

        //  insertOneData();

        //   updateDocument();

        updateDocument();
       // getData();

    }

    static void insertOneData() {
        MongoDatabase database = MyMongoDb.getMongoDb("mongodb");
        MongoCollection collection = database.getCollection("collection1");
        Document document = new Document("name", "Mahhi");
        document.append("sex", "baby");
        document.append("Age", 2);
        document.append("Race", "Asian");
        collection.insertOne(document);
    }

    static void getData() {
        MongoDatabase database = MyMongoDb.getMongoDb(dbName);
        MongoCollection collection = database.getCollection("collection1");
        //Document foundDoc = (Document) collection.find(new Document("name", "masi")).first();
        Document foundDoc = new Document("name", "masi");
        // if (foundDoc != null) {
        System.out.println("Found document");

        Bson updatedDoc = new Document("name", "UPUP");
        Bson updateOperation = new Document("$set", updatedDoc);
        collection.updateOne(foundDoc, updateOperation);
        System.out.println("Updated done!");
        //}
    }

    static void aggregateData(MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection("collection1");

        System.out.println("Database Connected");

        Block<Document> printBlock = document -> System.out.println(document.toJson());

        collection.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("name", "masi")),
                        Aggregates.group("$Name", Accumulators.sum("count", 1)
                        ))
        ).forEach(printBlock);

        System.out.println("Collection Aggregated.");
    }

    static void createCollection() {
        MongoDatabase database = MyMongoDb.getMongoDb(dbName);
        database.createCollection("FromJava");
    }

    static void createDocument() {
        MongoDatabase database = MyMongoDb.getMongoDb(dbName);
        // if collection not exist, it will make new collection
        MongoCollection<Document> collection = database.getCollection("xyz");
        List<Document> docs = new ArrayList<>();

        Document document = new Document();
        document.append("id", 1);
        document.append("FName: ", "Masum");
        document.append("LName:", "Islam");
        document.append("sex", "male");
        docs.add(document);

        Document document2 = new Document();
        document2.append("id", 2);
        document2.append("FName: ", "Sabrina");
        document2.append("LName:", "Islam");
        document2.append("sex", "female");
        docs.add(document2);

        Document document3 = new Document();
        document3.append("id", 3);
        document3.append("FName: ", "Mahi");
        document3.append("LName:", "Islam");
        document3.append("sex", "girl");
        docs.add(document3);

        Document document4 = new Document();
        document4.append("id", 4);
        document4.append("FName: ", "Masi2");
        document4.append("LName:", "Boo2");
        document4.append("sex", "male2");
        docs.add(document4);

        collection.insertMany(docs);
        System.out.println("collection done");
    }

    static void listAllCollection() {
        MongoDatabase database = MyMongoDb.getMongoDb(dbName);
        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }
    }

    static void updateDocument() {
        MongoDatabase database = MyMongoDb.getMongoDb(dbName);
        MongoCollection<Document> collection = database.getCollection("collection1");
        try (MongoCursor<Document> cur = collection.find().iterator()) {
            System.out.println("inital collection");
            while (cur.hasNext()) {
                System.out.println(cur.next().values());
                System.out.println("Next");
            }
        }
        Document foundDoc = new Document("name", "CCC");
        System.out.println("Found document");

        Bson updatedDoc = new Document("Age", 50);
        Bson updateOperation = new Document("$set", updatedDoc);
        collection.updateOne(foundDoc, updateOperation);

        try (MongoCursor<Document> cur = collection.find().iterator()) {
            System.out.println("After updated collection");
            while (cur.hasNext()) {
                System.out.println(cur.next().values());
                System.out.println("Next");
            }
        }
    }

    private static void dropCollection() {
        MongoDatabase database = MyMongoDb.getMongoDb("mongodb");
        database.createCollection("Temp");
        MongoCollection<Document> found = database.getCollection("Temp");
        found.drop();
    }

}
