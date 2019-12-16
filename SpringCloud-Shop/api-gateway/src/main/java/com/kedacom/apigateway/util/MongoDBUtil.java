package com.kedacom.apigateway.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Component
public class MongoDBUtil {
	// 连接MongoDB数据库
	MongoClient mongoClient = null;
	DB db = null;
	@Value("mongodb.ip")
	private String ip;
	@Value("mongodb.port")
	private int port;
	@Value("mongodb.databasename")
	private String dataBaseName;

	@SuppressWarnings("deprecation")
	public void init() {
		mongoClient = new MongoClient(ip, port);
		db = mongoClient.getDB(dataBaseName);
	}

	// 获取数据表
	public DBCollection getTable(String tableName) {
		if (null == db) {
			init();
		}
		return db.getCollection(tableName);

	}

	public void save(BasicDBObject document, DBCollection table) {

		table.insert(document);
	}

	public Object find(BasicDBObject search, DBCollection table) {
		DBCursor dbCursor = table.find(search);
		List<Object> data = new ArrayList<Object>();
		if (dbCursor.hasNext()) {
			DBObject object = dbCursor.next();
			data.add(object);
		}
		return data;
	}

	public void delete(BasicDBObject delete, DBCollection table) {

		table.remove(delete);
	}
}
