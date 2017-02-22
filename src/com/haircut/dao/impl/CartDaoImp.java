package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.CartDao;
import com.haircut.enity.Cart;
import com.haircut.enity.Collect;
import com.haircut.enity.Shop;
import com.haircut.servlet.CartServlet;
import com.haircut.servlet.UserServlet;

public class CartDaoImp extends BaseDao implements CartDao{
	
	private static String userNumber;
	
	@Override
	public List<Cart> getCart() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Cart> carts= null;
		
		System.out.println("userNumber = "+userNumber);
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select*from cart,goods where cart.goods_id = goods.goods_id AND cart.user_tel='"+userNumber+"';");
			carts = new ArrayList<Cart>();
			
			while (resultSet.next()) {
				Cart cart = new Cart();
				cart.setCart_id(resultSet.getString("cart_id"));
				cart.setGoods_id(resultSet.getString("goods_id"));
				cart.setUser_tel(resultSet.getString("user_tel"));
				cart.setGname(resultSet.getString("gname"));
				cart.setGprice(resultSet.getString("gprice"));
				cart.setGimage(resultSet.getString("gimage"));
				cart.setShop_name(resultSet.getString("shop_name"));
				
				carts.add(cart);
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
		
		return carts;
	}

	

	@Override
	public Cart put(String goods_id, String user_tel) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from cart where goods_id = '"+goods_id+"' AND user_tel = '"+user_tel+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				return null;
			}
			
			String sqlForInsert = "insert into cart (goods_id,user_tel) values"
					+ "( '"+goods_id+"', '"+user_tel+"')";
			statement.execute(sqlForInsert);
			
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				Cart cart = new Cart();
				cart.setCart_id(resultSet.getString("cart_id"));
				cart.setGoods_id(resultSet.getString("goods_id"));
				cart.setUser_tel(resultSet.getString("user_tel"));
				return cart;
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
	public Cart get(String user_tel) {
		userNumber = user_tel;
		System.out.println("userName==="+userNumber);
		return null;
	}

}
