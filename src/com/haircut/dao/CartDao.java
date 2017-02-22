package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Cart;

public interface CartDao {

	public List<Cart> getCart();
	
	Cart get(String user_tel);
	Cart put(String goods_id,String user_tel);
	
}
