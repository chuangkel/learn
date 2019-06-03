package com.github.chuangkel.mongodb_learn;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: learn
 * @description: mongodb插入操作
 * @author: chuangkel
 * @create: 2019-06-03 13:45
 **/
public class MongodbInsert {

    public static void main(String[] args) {

        MongoClient mongoClient = MongodbConnection.getConnection();

        MongoDatabase mongoDatabase = mongoClient.getDatabase("souyunku");
        MongoCollection<Document> collection = mongoDatabase.getCollection("collection");

        System.out.println("连接到数据库成功");
        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("文档插入成功");
    }
}
