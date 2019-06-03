package com.github.chuangkel.mongodb_learn;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-06-03 13:46
 **/
public class MongodbConnection {

    public static void main(String[] args) {

    }

    public static MongoClient getConnection(){
        ServerAddress serverAddress = new ServerAddress("144.34.135.183",27017);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);

        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        //MongoCredential credential = MongoCredential.createScramSha1Credential("", "databaseName", "".toCharArray());
        //List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        //credentials.add(credential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(addrs);

        return mongoClient;
    }
}
