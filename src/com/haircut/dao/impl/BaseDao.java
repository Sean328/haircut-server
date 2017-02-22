package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


import com.haircut.dao.Dao;

public class BaseDao {
//	private static String driver = "com.mysql.jdbc.Driver";
//	private static String url = "jdbc:mysql://localhos:3306/haircut/city";
//	private static String user = "root";
//	private static String password = "root";
	
	private static String url = null;
	private static String user = null;
	private static String password = null;
	
	
	static{
		try {
			//获取资源配置文件并且转换成resourceBundle
			ResourceBundle bundle = ResourceBundle.getBundle("config");
			String driver = bundle.getString("driver");
			//获取配置文件中的参数
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			//加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取数据库连接的方法
	protected Connection getConn() throws Exception{
//		Class.forName(driver);
		return new Inner().getConn();
	}
	
	//关闭当前的连接对象
	protected void close(ResultSet res,Statement stm,Connection conn) throws Exception {
		new Inner().close(res, stm, conn);
	}
	
	
	private class Inner implements Dao{

		@Override
		public Connection getConn() throws Exception {
			return DriverManager.getConnection(url,user,password);
		}

		@Override
		public void close(ResultSet res, Statement stm, Connection conn)
				throws Exception {
			if(res != null){
				try {
					res.close();
					res = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(stm != null){
				try {
					stm.close();
					stm = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		
		}
		
}
