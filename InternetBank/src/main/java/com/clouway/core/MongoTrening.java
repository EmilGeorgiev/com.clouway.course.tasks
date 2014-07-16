package com.clouway.core;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by clouway on 7/15/14.
 */
public class MongoTrening {
  public static void main(String[] args) throws UnknownHostException {

    MongoClient client = new MongoClient();

    DB db = client.getDB("Bank");
    DBCollection collection = db.getCollection("user");

    BasicDBObject basicDBObject = new BasicDBObject();
    basicDBObject.put("name", "ivan");

    System.out.println(basicDBObject);

    DBObject ivan = collection.findOne(basicDBObject);

    System.out.println(ivan);

    System.out.println(collection.findOne());
  }
}
