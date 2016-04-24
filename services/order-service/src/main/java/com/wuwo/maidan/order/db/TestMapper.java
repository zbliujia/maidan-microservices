package com.wuwo.maidan.order.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jiedaibao on 4/19/16.
 */
//@Mapper
public interface TestMapper {

	@Select("SELECT * FROM test WHERE id= #{id}")
	TestDBObject findById(@Param("id") long id);

	@Select("SELECT * FROM test")
	List<TestDBObject> findAll();



}
