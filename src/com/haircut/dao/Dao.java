package com.haircut.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public interface Dao {
	//��ȡ����
	Connection getConn() throws Exception;
	//�ر���Դ
	void close(ResultSet res,Statement stm,Connection conn) throws Exception;
}
