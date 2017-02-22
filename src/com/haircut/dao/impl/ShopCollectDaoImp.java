package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.ShopCollectDao;
import com.haircut.enity.Cart;
import com.haircut.enity.Collect;
import com.haircut.enity.ShopCollect;

public class ShopCollectDaoImp extends BaseDao implements ShopCollectDao{
	
	private static String userNumber;

	@Override
	public ShopCollect submit(String user_tel, String shop_id) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from shopcollect where shop_id = '"+shop_id+"' AND user_tel = '"+user_tel+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			
			if(resultSet.next()){
				return null;
			}
			
			String sqlForInsert = "insert into shopcollect (user_tel,shop_id) values"
					+ "( '"+user_tel+"', '"+shop_id+"')";
			statement.execute(sqlForInsert);
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				ShopCollect shopcollect = new ShopCollect();
				shopcollect.setShopcollect_id(resultSet.getString("shopcollect_id"));
				shopcollect.setUser_tel(resultSet.getString("user_tel"));
				shopcollect.setShop_id(resultSet.getString("shop_id"));
				return shopcollect;
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
	public List<ShopCollect> getShopCollect() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<ShopCollect> shopCollects= null;
		
		try {
			connection =getConn();
			
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("SELECT * FROM shopcollect,shop WHERE shopcollect.shop_id = shop.shop_id AND shopcollect.user_tel = '"+userNumber+"';");
			shopCollects = new ArrayList<ShopCollect>();
			
			while (resultSet.next()) {
				ShopCollect shopCollect = new ShopCollect();
				shopCollect.setShopcollect_id(resultSet.getString("shopcollect_id"));
				shopCollect.setShop_id(resultSet.getString("shop_id"));
				shopCollect.setUser_tel(resultSet.getString("user_tel"));
				
				shopCollect.setShopname(resultSet.getString("shop_name"));
				shopCollect.setShop_img(resultSet.getString("shop_img"));
				shopCollect.setShop_area(resultSet.getString("shop_area"));
				shopCollect.setShop_level(resultSet.getString("shop_level"));
				shopCollect.setShop_distance(resultSet.getString("shop_distance"));
				
				
				shopCollects.add(shopCollect);
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
		
		
		return shopCollects;
	}

	@Override
	public ShopCollect get(String user_tel) {
		
		userNumber = user_tel;
		System.out.println("userName==="+userNumber);
		return null;
	}

}
