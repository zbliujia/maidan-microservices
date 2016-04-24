package com.wuwo.maidan.order.db;

import java.io.Serializable;

/**
 * Created by jiedaibao on 4/19/16.
 */
public class TestDBObject implements Serializable {
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
