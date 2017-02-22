package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.CollectDao;
import com.haircut.enity.Collect;
import com.haircut.enity.Comment;
import com.haircut.enity.Order;
import com.haircut.enity.User;
import com.haircut.servlet.CollectServlet;

public class CollectDaoImp extends BaseDao implements CollectDao{

	private static String userNumber;
	
	@Override
	public Collect register(String goods_id, String user_tel) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from collect where goods_id = '"+goods_id+"' AND user_tel = '"+user_tel+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				return null;
			}
			
			String sqlForInsert = "insert into collect (goods_id,user_tel) values"
					+ "( '"+goods_id+"', '"+user_tel+"')";
			statement.execute(sqlForInsert);
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				Collect collect = new Collect();
				collect.setCollect_id(resultSet.getString("collect_id"));
				collect.setGoods_id(resultSet.getString("goods_id"));
				collect.setUser_tel(resultSet.getString("user_tel"));
				return collect;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				close(resultSet, statement, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public List<Collect> getCollect() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Collect> collects= null;
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select*from collect,goods where collect.goods_id = goods.goods_id AND collect.user_tel='"+userNumber+"';");
			collects = new ArrayList<Collect>();
			
			while (resultSet.next()) {
				Collect collect = new Collect();
				collect.setCollect_id(resultSet.getString("collect_id"));
				collect.setGoods_id(resultSet.getString("goods_id"));
				collect.setUser_tel(resultSet.getString("user_tel"));
				collect.setGname(resultSet.getString("gname"));
				collect.setGprice(resultSet.getString("gprice"));
				collect.setGimage(resultSet.getString("gimage"));
				collect.setShop_name(resultSet.getString("shop_name"));
				
				collects.add(collect);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return collects;
	}

	@Override
	public Collect get(String user_tel) {
		
		userNumber = user_tel;
		System.out.println("userName==="+userNumber);
		return null;
	}

}
