package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Goods;
import com.haircut.enity.Shop;

public interface ShopDao2 {
	
	//��ȡ�����б�
	public List<Shop> getShop();
	//��ȡָ��������Ϣ
	public List<Shop> getShopItem(String shop_id);

}
