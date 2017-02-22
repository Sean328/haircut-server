package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.ShopDao;
import com.haircut.enity.Shop;

public class ShopDaoImpl extends BaseDao implements ShopDao{

	@Override
	public List<Shop> getShop() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Shop>shops= null;
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select * from shop order by shop_id");
			shops = new ArrayList<Shop>();
			
			//将结果集里的数据循环放到列表里面
			while (resultSet.next()) {
				Shop shop = new Shop();
				shop.setId(resultSet.getString("shop_id"));
				shop.setName(resultSet.getString("shop_name"));
				shop.setAddress(resultSet.getString("shop_address"));
				shop.setShop_img(resultSet.getString("shop_img"));
				shop.setShop_intro(resultSet.getString("shop_intro"));
				shop.setShop_area(resultSet.getString("shop_area"));
				shop.setShop_distance(resultSet.getString("shop_distance"));
				shop.setShop_level(resultSet.getString("shop_level"));
				shop.setShop_tel(resultSet.getString("shop_tel"));
				shop.setShop_open_time(resultSet.getString("shop_open_time"));
				shop.setShop_fastcut(resultSet.getString("shop_fastcut"));
				shops.add(shop);
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
		
		return shops;
	}

}
