package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.CollectDao;
import com.haircut.dao.OrderDao;
import com.haircut.enity.Cart;
import com.haircut.enity.Collect;
import com.haircut.enity.Order;
import com.haircut.enity.User;

public class OrderDaoImp extends BaseDao implements OrderDao{
	
	private static String userNumber;

	@Override
	public Order submit(String goods_id, String user_tel,String orders_time, String orders_price) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from orders where user_tel = '"+user_tel+"' AND orders_time = '"+orders_time+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				return null;
			}
			
			String sqlForInsert = "insert into orders (goods_id,user_tel,orders_time,orders_price) values"
					+ "( '"+goods_id+"', '"+user_tel+"','"+orders_time+"','"+orders_price+"')";
			statement.execute(sqlForInsert);
			
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				Order order = new Order();
				order.setOrders_id(resultSet.getString("orders_id"));
				order.setGoods_id(resultSet.getString("goods_id"));
				order.setUser_tel(resultSet.getString("user_tel"));
				order.setOrders_time(resultSet.getString("orders_time"));
				order.setOrders_price(resultSet.getString("orders_price"));
				return order;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
	public List<Order> getOrder() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Order> orders= null;
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select*from orders,goods where orders.goods_id = goods.goods_id AND orders.user_tel='"+userNumber+"';");
			orders = new ArrayList<Order>();
			
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrders_id(resultSet.getString("orders_id"));
				order.setGoods_id(resultSet.getString("goods_id"));
				order.setUser_tel(resultSet.getString("user_tel"));
				order.setOrders_price(resultSet.getString("orders_price"));
				order.setOrders_time(resultSet.getString("orders_time"));
				order.setGname(resultSet.getString("gname"));
				order.setGprice(resultSet.getString("gprice"));
				order.setGimage(resultSet.getString("gimage"));
				order.setShop_name(resultSet.getString("shop_name"));
				
				orders.add(order);
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet, statement, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return orders;
	}

	@Override
	public Order get(String user_tel) {
		
		userNumber = user_tel;
		System.out.println("userName==="+userNumber);
		return null;

	}

	
}
