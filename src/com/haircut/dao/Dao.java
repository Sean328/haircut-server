package com.haircut.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public interface Dao {
	//获取连接
	Connection getConn() throws Exception;
	//关闭资源
	void close(ResultSet res,Statement stm,Connection conn) throws Exception;
}
