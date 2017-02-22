package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Order;

public interface OrderDao {
	Order submit(String goods_id,String user_tel,String orders_time,String orders_price);
	public List<Order> getOrder();
	Order get(String user_tel);
}
