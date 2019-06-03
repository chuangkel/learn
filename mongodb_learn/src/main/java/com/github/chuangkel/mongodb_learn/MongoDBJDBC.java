package com.github.chuangkel.mongodb_learn;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBC{
    public static void main(String[] args){
        try {

            ServerAddress serverAddress = new ServerAddress("144.34.135.183",27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            //MongoCredential credential = MongoCredential.createScramSha1Credential("", "databaseName", "".toCharArray());
            //List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            //credentials.add(credential);

            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs);

            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("souyunku");
            System.out.println("Connect to database successfully");
            /**
            mongoDatabase.createCollection("test");
            System.out.println("集合创建成功");
             */

            /** 查询 */
            MongoCollection<Document> collection = mongoDatabase.getCollection("collection");
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println("===========");
                System.out.println(mongoCursor.next());
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}