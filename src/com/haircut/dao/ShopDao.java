package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Goods;
import com.haircut.enity.Shop;

public interface ShopDao {
	
	//获取店铺列表
	public List<Shop> getShop();

}
