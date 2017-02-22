package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Goods;
import com.haircut.enity.Shop;

public interface ShopDao2 {
	
	//获取店铺列表
	public List<Shop> getShop();
	//获取指定店铺信息
	public List<Shop> getShopItem(String shop_id);

}
