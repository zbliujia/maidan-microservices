package com.wuwo.maidan.order.controller;

import com.wuwo.maidan.order.db.TestDBObject;
import com.wuwo.maidan.order.db.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiedaibao on 4/16/16.
 */
@RestController
@RequestMapping("/order")
public class Example {

//	@Autowired
//	TestMapper testMapper;

	@Autowired
	@Qualifier("writeSqlSessionFactory")
	SqlSessionFactory writeSqlSessionFactory;

	@Autowired
	@Qualifier("readSqlSessionFactory")
	SqlSessionFactory readSqlSessionFactory;

	@RequestMapping("/test")
    String test(HttpServletRequest req, HttpServletResponse res) {
		return "i'm order test session is "+req.getSession().getAttribute("testSession");
    }

//    @RequestMapping("/test-object")
//    TestObject testObject() {
//        TestObject testObject = new TestObject();
//	    return testObject;
//    }

//	@RequestMapping("/test-db-one")
//	TestDBObject testDbOne() {
//		return testMapper.findById(1);
//	}
//
//	@RequestMapping("/test-db-all")
//	List<TestDBObject> testDbAll() {
//		return testMapper.findAll();
//	}

	@RequestMapping("/test-db-write")
	List<TestDBObject> testDbWrite() {

		SqlSession session = writeSqlSessionFactory.openSession();
		try {
			TestMapper mapper = session.getMapper(TestMapper.class);
			return mapper.findAll();
		} finally {
			session.close();
		}

	}

	@RequestMapping("/test-db-read")
	List<TestDBObject> testDbRead() {
		SqlSession session = readSqlSessionFactory.openSession();
		try {
			TestMapper mapper = session.getMapper(TestMapper.class);
			return mapper.findAll();
		} finally {
			session.close();
		}
	}

}
