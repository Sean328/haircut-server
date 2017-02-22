package com.haircut.dao;

import java.util.List;

import com.haircut.enity.ShopCollect;

public interface ShopCollectDao {
	
	ShopCollect submit(String user_tel,String shop_id);
	
	public List<ShopCollect> getShopCollect();
	
	ShopCollect get(String user_tel);

}
