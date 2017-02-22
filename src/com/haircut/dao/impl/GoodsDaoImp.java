package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.GoodsDao;
import com.haircut.enity.Goods;

public class GoodsDaoImp extends BaseDao implements GoodsDao{
	
	private static String goods_ID;

	@Override
	public List<Goods> getGoods() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Goods> goods= null;
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select * from goods order by goods_id");
			goods = new ArrayList<Goods>();
			
			//将结果集里的数据循环放到列表里面
			while (resultSet.next()) {
				Goods good = new Goods();
				good.setGood_id(resultSet.getString("goods_id"));
				good.setShop_id(resultSet.getString("shop_id"));
				good.setGood_sort(resultSet.getString("goods_sort"));
				good.setGname(resultSet.getString("gname"));
				good.setGprice(resultSet.getString("gprice"));
				good.setShop_name(resultSet.getString("shop_name"));
				good.setGimage(resultSet.getString("gimage"));
				
				goods.add(good);
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
		
		return goods;
	}

	@Override
	public List<Goods> getGoodsItem(String mGoods_id) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Goods> goods= null;
		
		goods_ID = mGoods_id;
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select * from goods where goods_id ='"+goods_ID+"';");
			goods = new ArrayList<Goods>();
			
			//将结果集里的数据循环放到列表里面
			while (resultSet.next()) {
				Goods good1 = new Goods();
				good1.setGood_id(resultSet.getString("goods_id"));
				good1.setShop_id(resultSet.getString("shop_id"));
				good1.setGood_sort(resultSet.getString("goods_sort"));
				good1.setGname(resultSet.getString("gname"));
				good1.setGprice(resultSet.getString("gprice"));
				good1.setShop_name(resultSet.getString("shop_name"));
				good1.setGimage(resultSet.getString("gimage"));
				
				goods.add(good1);
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
		
		return goods;
	}


}
