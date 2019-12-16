package com.kedacom.apigateway.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedacom.apigateway.util.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

@Service
public class LogService {
	@Autowired
	private MongoDBUtil mongodbUtil;

	public void saveLogs(BasicDBObject logInformations) {
		DBCollection logTable = mongodbUtil.getTable("t_log");
		logTable.save(logInformations);
	}

}
