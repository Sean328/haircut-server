package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Goods;

public interface GoodsDao {
	public List<Goods> getGoods();
	
	public List<Goods> getGoodsItem(String goods_id);
}
